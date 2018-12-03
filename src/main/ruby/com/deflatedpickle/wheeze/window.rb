require 'glimmer'

# rubocop:disable LineLength
# rubocop:disable MethodLength

# Creates the window
class Window
  include_package 'org.eclipse.swt'
  include_package 'org.eclipse.swt.widgets'
  include_package 'org.eclipse.swt.layout'
  include_package 'org.eclipse.swt.events'
  include_package 'org.eclipse.swt.graphics'

  # java_import 'com.deflatedpickle.wheeze.widgets.PaintableCanvas'
  # include_package 'com.deflatedpickle.wheeze.widgets'
  include_package 'com.deflatedpickle.wheeze.util'

  include Glimmer

  def initialize
    @do_draw = false
    @cursor_location = Point.new(0, 0)
    @paint_listener = CanvasElements.get_instance.paintListener

    @shell = shell do
      text 'Wheeze'
      minimum_size 400, 400
      layout GridLayout.new

      # paintable_canvas(:border) do
      #   layout_data GridData.new(:fill.swt_constant, :fill.swt_constant, true, true)
      # end

      @canvas = canvas(:border, :double_buffered, :no_redraw_resize) do
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
          set_cursor
        end
      end

      @canvas.widget.add_paint_listener(@paint_listener)
    end

    @shell.open
  end

  def set_cursor()
    @location_temp = Display.get_default.cursor_location
    @cursor_location = Display.get_default.focus_control.to_control(@location_temp.x, @location_temp.y)
  end
end

Window.new

# rubocop:enable LineLength
# rubocop:enable MethodLength
