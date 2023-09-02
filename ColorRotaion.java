import java.awt.*;

public class ColorRotaion {

    public static Color getColor(float angle) {
        float r = 0;
        float g = 0;
        float b = 0;

        while (angle >= 360){
            angle -= 360;
        }


        if(angle<=60){
            r = 1;
            b = 0;
            g=angle/60f;
        }else if(angle<=120){
            angle-=60;

            g = 1;
            b = 0;
            r = 1.0f - angle/60f;
        }else if(angle<=180){
            angle-=120;

            g = 1;
            r = 0;
            b = angle/60f;
        }else if(angle<=240){
            angle-=180;

            r = 0;
            b = 1;
            g = 1.f - angle/60f;
        }else if(angle<=300){
            angle-=240;

            b = 1;
            g = 0;
            r = angle/60f;
        }else if(angle<=360){
            angle-=300;

            r = 1;
            g = 0;
            b = 1.f - angle/60f;
        }

        return new Color(r, g, b);
    }
}
