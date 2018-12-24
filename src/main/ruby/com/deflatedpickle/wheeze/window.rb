require 'glimmer'

# rubocop:disable LineLength
# rubocop:disable MethodLength

# Creates the window
class Window
  include Glimmer

  include_package 'org.eclipse.swt'
  include_package 'org.eclipse.swt.widgets'
  include_package 'org.eclipse.swt.layout'
  include_package 'org.eclipse.swt.events'
  include_package 'org.eclipse.swt.graphics'

  # java_import 'com.deflatedpickle.wheeze.widgets.PaintableCanvas'
  # include_package 'com.deflatedpickle.wheeze.widgets'
  include_package 'com.deflatedpickle.wheeze.util'
  include_package 'com.deflatedpickle.wheeze.widgets'

  def initialize
    @first_focus = true
    @do_draw = false
    @do_background = true
    @cursor_location = Point.new(0, 0)
    @paint_listener = CanvasElements.get_instance.paintListener

    @shell = shell do
      text 'Wheeze'
      minimum_size 500, 400
      layout GridLayout.new(3, false)

      tool_bar(:vertical, :wrap, :shadow_out) do
        grid_data = GridData.new(:center.swt_constant, :fill.swt_constant, false, true)
        layout_data grid_data

        @button_brush = tool_item(:radio) do
          text 'Brush'
          selection true

          on_widget_selected  do
            CanvasElements.get_instance.set_active_tool_type(ToolType::BRUSH)
          end
        end

        @button_eraser = tool_item(:radio) do
          text 'Eraser'

          on_widget_selected  do
            CanvasElements.get_instance.set_active_tool_type(ToolType::ERASER)
          end
        end
      end

      @brush_list_placeholder = label(:none)

      # TODO: Find a better way to do custom widgets
      on_focus_gained do
        if @first_focus
          @first_focus = false

          CompatibilityUtil.get_instance.shell = @shell.widget

          brush_list = BrushList.new(CompatibilityUtil.get_instance.shell, SWT::BORDER)
          brush_list.move_above @brush_list_placeholder.widget
          @brush_list_placeholder.widget.dispose
          @shell.widget.layout

          @canvas.widget.set_focus
        end
      end

      @canvas = canvas(:border, :double_buffered, :no_redraw_resize, :no_background) do
        grid_data = GridData.new(:center.swt_constant, :center.swt_constant, true, true)
        grid_data.widthHint = 340
        grid_data.heightHint = 340
        layout_data grid_data

        on_mouse_down do
          @do_draw = true

          @canvas.widget.set_focus

          if @canvas.widget.display.get_focus_control != null
            CanvasElements.get_instance.doPaint(@canvas.widget, @cursor_location)
          end
        end

        on_mouse_up do
          @do_draw = false
        end

        on_mouse_move do
          set_cursor
          CanvasElements.get_instance.doPaint(@canvas.widget, @cursor_location) if @do_draw
        end

        on_focus_gained do
          CanvasElements.get_instance.draw_background(@canvas.widget) if @do_background
          @do_background = false
          set_cursor
        end
      end

      @canvas.widget.add_paint_listener(@paint_listener)
    end

    CanvasElements.get_instance.prepare_graphics(@canvas.widget)

    @shell.open
  end

  def set_cursor
    @location_temp = Display.get_default.cursor_location

    if !Display.get_default.focus_control.nil?
      @cursor_location = Display.get_default.focus_control.to_control(@location_temp.x, @location_temp.y)
    else
      @cursor_location = Point.new(0, 0)
    end
  end
end

Window.new

# rubocop:enable LineLength
# rubocop:enable MethodLength
