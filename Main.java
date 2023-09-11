
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
    static ArrayList<Particle> particles = new ArrayList<>(); // creates an ArrayList of Particle objects to hold all particles
    static boolean fullscreen = false;

    public static void prepareVariables() {
        int nParticles = 10000;
        try {
            nParticles = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of particles (10000 default): "));
        } catch (Exception e) {}


        fullscreen = JOptionPane.showInputDialog(null, "Fullscrean?(-/+):").equals("+");

        for (int i = 0; i < nParticles; i++) 
            particles.add(new Particle(200+i%200, 200+i%300));

    }
    
    public static void main(String[] args){

        prepareVariables(); 
        new MyWindow(1024, 512); // creates a new window to display the particles

    }
}
