require 'glimmer'

# Creates the window
class Main
  include_package 'org.eclipse.swt'
  include_package 'org.eclipse.swt.widgets'
  include_package 'org.eclipse.swt.layout'

  include Glimmer

  def initialize
    @shell = shell do
      text 'Wheeze'
      minimum_size 400, 400
      layout GridLayout.new
    end

    @shell.open
  end
end

Main.new
