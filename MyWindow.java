import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.BufferStrategy;
import java.util.Timer;

public class MyWindow extends JFrame {
    Timer timer = new Timer();
    float sensitivity = 1;
    float colorAngle = 0;
    float scale = 1;
    int numberOfParticles;

    Graphics2D g;
    String data;

    Point scaledMousePosition = new Point(0, 0);
    public MyWindow(int w, int h) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setSize(w, h); setVisible(true);

        numberOfParticles = Main.particles.size();

        Canvas c = new Canvas(); add(c);
        c.createBufferStrategy(2);
        BufferStrategy bs = c.getBufferStrategy();

        c.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(e.getWheelRotation()>0) sensitivity *=.9; else sensitivity *=1.1;
            }
        });

        c.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                scaledMousePosition.x = (int) (e.getX()/scale);
                scaledMousePosition.y = (int) (e.getY()/scale);
            }
        });

        c.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_C){
                    for (Particle p: Main.particles) {
                        p.oldX = p.x;
                        p.oldY = p.y;

                        float dx = scaledMousePosition.x-p.x;
                        float dy = -scaledMousePosition.y+p.y;
                        float r = (float) Math.sqrt(dx*dx+dy*dy);

                        p.assinnedVx = dy * (float) Math.pow(sensitivity,1f/2f) / (float) Math.pow(r, 1f/2f);
                        p.assignedVy = dx * (float) Math.pow(sensitivity,1f/2f) / (float) Math.pow(r, 1f/2f);

                    }
                }else if(e.getKeyCode() == KeyEvent.VK_R) {
                    for (Particle p : Main.particles) {
                        p.x = (int) (Math.random() * (float) getWidth()/scale);
                        p.y = (int) (Math.random() * (float) getHeight()/scale);

                        p.oldX = p.x;
                        p.oldY = p.y;
                    }
                }else if(e.getKeyCode() == KeyEvent.VK_MINUS) {
                    scale /= 2;
                    g.scale(scale, scale);
                }else if(e.getKeyCode() == KeyEvent.VK_EQUALS){
                    scale *= 2;
                    g.scale(scale, scale);
                }else if(e.getKeyCode() == KeyEvent.VK_P) {
                    for (Particle p : Main.particles) {
                        float newX = (float) Math.cos(Math.random() * 2f * 3.1415f) * 200 + scaledMousePosition.x;
                        float newY = (float) Math.sin(Math.random() * 2f * 3.1415f) * 200 + scaledMousePosition.y;
                        p.x = newX;
                        p.y = newY;
                        p.oldX = newX;
                        p.oldY = newY;


                        float dx = scaledMousePosition.x - p.x;
                        float dy = -scaledMousePosition.y + p.y;
                        float r = (float) Math.sqrt(dx * dx + dy * dy);

                        //todo add sensitivity and radius power to the setting window
                        p.assinnedVx = dy * (float) Math.pow(sensitivity,1f/2f) / (float) Math.pow(r, 1f/2f);
                        p.assignedVy = dx * (float) Math.pow(sensitivity,1f/2f) / (float) Math.pow(r, 1f/2f);

                    }
                }


                    
            }
        });

        g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                colorAngle+=.1;
                if(colorAngle>=360)
                    colorAngle-=360;

                data = String.format("Number of particles: %d \n" +
                        "Sensitivity: %f \n" +
                        "Scale: %f\n"+
                        "X: %d\n"+
                        "Y: %d", numberOfParticles, sensitivity, scale, scaledMousePosition.x, scaledMousePosition.y);

                Dimension bounds = new Dimension((int)((float)getWidth()/scale), (int)((float)getHeight()/scale));



                updatePartcles(Main.particles, scaledMousePosition.x, scaledMousePosition.y, sensitivity, bounds);
                updateBackgroud(bounds,  g);

                addParametersLabel(data, g, scaledMousePosition.x, scaledMousePosition.y, bounds, scale);
                displayParticles(Main.particles, colorAngle, g);

                bs.show();


            }
        }, 0, 1000/60);

    }

    public void addParametersLabel(String label, Graphics2D g, float attractX, float attractY, Dimension bounds, float scale){
        g.setFont(new Font("courier new", Font.PLAIN, (int) (10/scale)));
        g.setColor(new Color(7, 122, 25));
        g.drawString(label, 5f/scale, (float) bounds.height - 40f/scale );

        g.fillOval((int)attractX-2, (int)attractY-2, 4, 4);

    }

    private void displayParticles(ArrayList<Particle> particles, float colorAngle, Graphics2D g) {
        g.setColor(ColorRotaion.getColor(colorAngle));
        for (Particle p : particles) {
            g.drawLine((int)p.x, (int)p.y,(int)p.x, (int)p.y);
        }
    }

    private void updateBackgroud(Dimension bounds, Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, bounds.width, bounds.height);
    }

    private void updatePartcles(ArrayList<Particle> particles, float attractX, float attractY, float sensetivity, Dimension bounds) {

        for (Particle p : particles) {
            p.attract(attractX, attractY, sensetivity, bounds);
            p.assinnedVx=0;
            p.assignedVy=0;
        }
    }
}

