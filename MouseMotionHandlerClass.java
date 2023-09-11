import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class MouseMotionHandlerClass extends MouseMotionAdapter{
    @Override
    public void mouseMoved(MouseEvent e) {
        MyWindow.scaledMousePosition.x = (int) (e.getX()/MyWindow.scale);
        MyWindow.scaledMousePosition.y = (int) (e.getY()/MyWindow.scale);
    }
}
