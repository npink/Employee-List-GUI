/* 
    Could not figure out how to get windows to update consistently
    Tried redraw(), draw(), validate(), doLayout()
    Resizing the component always forces a redraw so I encapsulated it in this class
    
    Author: Nick Pink
*/

import java.awt.Component;

class Painter {
    private static int _repaint_hack = 1;
    
    public static void repaint(Component c) {
        c.setSize(
            (int)c.getSize().getWidth() + _repaint_hack, 
            (int)c.getSize().getHeight() + _repaint_hack
        );
        if (_repaint_hack == 1) {
            _repaint_hack = -1;
        }
        else {
            _repaint_hack = 1;
        }
    }
}