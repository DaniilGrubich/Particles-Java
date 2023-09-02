import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Particle> particles = new ArrayList<>();
    public static void main(String[] args){
        Scanner scannerIn = new Scanner(System.in);
        System.out.println("Enter number of particles:");

        int n = Integer.parseInt(scannerIn.next());
        for (int i = 0; i < n; i++) {
            particles.add(new Particle(i, i));
        }



        MyWindow window = new MyWindow(900, 500);


    }
}
