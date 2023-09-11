import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelHandlerClass implements MouseWheelListener{

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int wVal = e.getWheelRotation();

        if(e.isShiftDown())
            changeScale(wVal);
        else
        
            changeSensitivity(wVal);
        
        
    }

    public void changeScale(int val){
        if(val<0) 
            MyWindow.scale *=1.1; 
        else 
            MyWindow.scale *=.9;
    }

    public void changeSensitivity(int val){
        if(val>0) 
            MyWindow.sensitivity *=.9; 
        else 
            MyWindow.sensitivity *=1.1;
    }

   

    
}
