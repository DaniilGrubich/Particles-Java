//Created May 2020 by Daniil Grubich

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;

public class MyWindow extends JFrame {
    Timer timer = new Timer();
    
    static float scale = 1;
    static float hColor = 0;
    static Dimension bounds = new Dimension();
    static boolean trace = false;
    
    static float sensitivity = 1;

    static Canvas c;
    static Graphics2D g;
    static BufferStrategy bs;
    static boolean toSave = false;

    static Point scaledMousePosition = new Point(0, 0);
    public MyWindow(int w, int h) {
        super("Particles 2"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(w, h); 
        if (Main.fullscreen) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }
        setVisible(true);

        c = new Canvas(); add(c);
        c.createBufferStrategy(2);
        bs = c.getBufferStrategy();

        c.addMouseWheelListener(new MouseWheelHandlerClass());
        c.addMouseMotionListener(new MouseMotionHandlerClass());
        c.addKeyListener(new KeyHandlerClass());

        

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                g = (Graphics2D) bs.getDrawGraphics();
                g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                updateBackgroud(); 
                addParametersLabel();
                

                g.scale(scale, scale);
                bounds = new Dimension((int)((float)getWidth()/scale), (int)((float)getHeight()/scale));

                updatePartcles();
                displayParticles();

                bs.show();

                if(toSave){
                    saveScreen();
                    toSave = false;
                }
    


            }
        }, 0, 1000/60);

    }

    public void addParametersLabel(){
        String label = String.format("Number of particles: %5d |" +
                        "Sensitivity: %5f |" +
                        "Scale: %5f |"+
                        "X: %5d |"+
                        "Y: %5d | F1 - Help|", Main.particles.size(), sensitivity, scale, scaledMousePosition.x, scaledMousePosition.y);
        
        g.setFont(new Font("courier new", Font.PLAIN, (int) (15)));
        g.setColor(new Color(0, 255, 0) );
        g.drawString(label, 0, 15f);


    }

    private void displayParticles() {
        if(hColor>1) hColor=0.f; else hColor+=.0005f;
        g.setColor(Color.getHSBColor(hColor, 1.f, 1.f));
        for (Particle p : Main.particles) {
            g.drawLine((int)p.x, (int)p.y,(int)p.x, (int)p.y);
        }

    }

    private void updateBackgroud() {
        g.setColor(new Color(0, 0, 0, 100));
        if(trace)
            g.fillRect(0, 0, getWidth(), 20);
        else
            g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void updatePartcles() {

        for (Particle p : Main.particles) {
            p.attract((float)scaledMousePosition.getX(), (float)scaledMousePosition.getY(), sensitivity, bounds);
            p.assinnedVx=0;
            p.assignedVy=0;
        }
    }

    public void saveScreen() {
        try {
            Robot robot = new Robot();
    
            Point contentLocation = this.getContentPane().getLocationOnScreen();
            Dimension contentSize = this.getContentPane().getSize();
    
            Rectangle captureRect = new Rectangle(contentLocation.x, contentLocation.y, contentSize.width, contentSize.height);
            BufferedImage capturedImage = robot.createScreenCapture(captureRect);
            
            //get current time and format it to string
            Calendar cal = Calendar.getInstance();
            String time = String.format("%1$tY-%1$tm-%1$td %1$tH-%1$tM-%1$tS", cal);
            

            ImageIO.write(capturedImage, "png", new File(time + ".png"));
        } catch (Exception e) {
            System.out.println("Error saving screenshot: " + e.getMessage());
        }
    }
}

