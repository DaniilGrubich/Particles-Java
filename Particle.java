import java.awt.*;

public class Particle {
    float x,y;
    float oldX, oldY;
    float assinnedVx = 0;
    float assignedVy = 0;
    Particle(float xi, float yi){
        x = xi;
        y = yi;
        oldX = x;
        oldY = y;
    }

    void attract(float xf, float yf, float sensetivity, Dimension bounds){
        float velX;
        float velY;

        velX = (x - oldX) + assinnedVx;
        velY = (y - oldY) + assignedVy;

        oldX = x;
        oldY = y;

        x+=velX;
        y+=velY;


        float dx = xf - x;
        float dy = yf - y;

        float distance = (float)Math.sqrt(dx*dx+dy*dy);

        x+=dx/distance*sensetivity;
        y+=dy/distance*sensetivity;




//        if(x>bounds.width)
//            x-=bounds.width;
//        else if(x<0)
//            x+=bounds.width;
//
//        if(y>bounds.height)
//            y-=bounds.height;
//        else if(y<0)
//            y+=bounds.height;

//        System.out.println(x+"  "+y);
    }


}
