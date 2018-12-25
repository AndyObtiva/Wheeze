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

  include_package 'com.deflatedpickle.wheeze.util'
  include_package 'com.deflatedpickle.wheeze.widgets'

  def initialize
    @first_focus = true

    @shell = shell do
      text 'Wheeze'
      minimum_size 800, 600
      layout GridLayout.new(3, false)

      tool_bar(:vertical, :wrap, :shadow_out) do
        grid_data = GridData.new(:fill.swt_constant, :fill.swt_constant, false, true)
        layout_data grid_data

        @button_brush = tool_item(:radio) do
          text 'Brush'
          selection true

          on_widget_selected  do
            @paint_canvas.set_active_tool_type(ToolType::BRUSH)
          end
        end

        @button_eraser = tool_item(:radio) do
          text 'Eraser'

          on_widget_selected  do
            @paint_canvas.set_active_tool_type(ToolType::ERASER)
          end
        end
      end

      @brush_panel = composite(:border) do
        layout GridLayout.new

        grid_data = GridData.new(:fill.swt_constant, :fill.swt_constant, false, true)
        grid_data.widthHint = 180
        layout_data grid_data
      end

      # TODO: Find a better way to do custom widgets
      on_focus_gained do
        if @first_focus
          @first_focus = false

          CompatibilityUtil.get_instance.shell = @shell.widget

          @brush_configure = BrushConfigure.new(@brush_panel.widget, SWT::NONE)
          CompatibilityUtil.get_instance.brushConfigure = @brush_configure
          BrushList.new(@brush_panel.widget, SWT::NONE)

          @paint_canvas = PaintableCanvas.new(@shell.widget, SWT::BORDER | SWT::DOUBLE_BUFFERED | SWT::NO_REDRAW_RESIZE | SWT::NO_BACKGROUND)
          CompatibilityUtil.get_instance.paintableCanvas = @paint_canvas
        end
      end
    end

    @shell.open
  end
end

Window.new

# rubocop:enable LineLength
# rubocop:enable MethodLength
