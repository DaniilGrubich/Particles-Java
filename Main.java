
import java.util.ArrayList;

public class Main {
    static ArrayList<Particle> particles = new ArrayList<>(); // creates an ArrayList of Particle objects to hold all particles
    public static void main(String[] args){

        int n = 10000; // sets the number of particles to be created qwe

        for (int i = 0; i < n; i++) 
            particles.add(new Particle(i, i));
        
        
        new MyWindow(1024, 512); // creates a new window to display the particles

        System.out.println("The number of particles created is " + n);

    }
}
