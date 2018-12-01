require 'glimmer'

# Creates the window
class Window
  include_package 'org.eclipse.swt'
  include_package 'org.eclipse.swt.widgets'
  include_package 'org.eclipse.swt.layout'

  include Glimmer

  @do_draw = false

  def initialize
    @shell = shell do
      text 'Wheeze'
      minimum_size 400, 400
      layout GridLayout.new

      @canvas = canvas(:border) do
        on_mouse_down do
          @do_draw = true
        end

        on_mouse_up do
          @do_draw = false
        end
      end
    end

    @shell.open
  end
end

Window.new
