import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

class KeyHandlerClass extends KeyAdapter {


    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.isShiftDown()){

        }else{
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                System.exit(0);
            else if (e.getKeyCode() == KeyEvent.VK_T) 
                MyWindow.trace = !MyWindow.trace;
            else if (e.getKeyCode() == KeyEvent.VK_C) 
                createCurl();
            else if (e.getKeyCode() == KeyEvent.VK_R) 
                restParticles();
            else if (e.getKeyCode() == KeyEvent.VK_P) 
                RectCurl();
            else if(e.getKeyCode() == KeyEvent.VK_S)
                MyWindow.toSave = true;
            else if (e.getKeyCode() == KeyEvent.VK_F1) 
                showHelpWindow();
        }
        

    }

    

    public void createCurl(){
        for (Particle p : Main.particles) {
            p.oldX = p.x;
            p.oldY = p.y;

            float dx = MyWindow.scaledMousePosition.x - p.x;
            float dy = -MyWindow.scaledMousePosition.y + p.y;
            float r = (float) Math.sqrt(dx * dx + dy * dy);

            p.assinnedVx = dy * (float) Math.pow(MyWindow.sensitivity, 1f / 2f) / (float) Math.pow(r, 1f / 2f);
            p.assignedVy = dx * (float) Math.pow(MyWindow.sensitivity, 1f / 2f) / (float) Math.pow(r, 1f / 2f);

        }
    }

    public void restParticles(){
        for (Particle p : Main.particles) {
            p.x = (int) (Math.random() * (float) MyWindow.bounds.getWidth());
            p.y = (int) (Math.random() * (float) MyWindow.bounds.getHeight());

            p.oldX = p.x;
            p.oldY = p.y;
        }
    }

    public void RectCurl(){
        for (Particle p : Main.particles) {
            float newX = (float) Math.cos(Math.random() * 2f * 3.1415f) * 200 + MyWindow.scaledMousePosition.x;
            float newY = (float) Math.sin(Math.random() * 2f * 3.1415f) * 200 + MyWindow.scaledMousePosition.y;
            p.x = newX;
            p.y = newY;
        }

        createCurl();
    }

    public void showHelpWindow(){
        String message = "Help\n" +
                    "Scroll: Change Senstivity\n" +
                    "Scroll+Shift: Change Zoom\n" +
                    "---\n" +
                    "'R': Reset Particles \n" +
                    "'C': Add Curl \n" +
                    "'P': Create Rectangular Curl \n" +
                    "'Esc': Close";

            JOptionPane.showMessageDialog(null, message);
    }

}