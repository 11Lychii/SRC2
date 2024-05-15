import processing.core.*;
import java.lang.*;

class DisplayBox extends Box{

    String a, b, c;
    
    DisplayBox(PApplet pap, int xPos, int yPos, int width, int height){
        super(pap, xPos, yPos, width, height);
    }

     void display(){
        p.fill(255);
        super.display();
        p.textSize(60);
     }

    void showFunction(String ai, String bi, String ci){
        String a = ai, b = bi, c = ci;

        if(a.length() == 0){
            a = "0";
        }
        if(b.length() == 0){
            b = "0";
        }
        if(c.length() == 0){
            c = "0";
        }

        if(a.length() == 1 && a.charAt(0) == '-'){
            a = "-0";
        }
        if(b.length() == 1 && b.charAt(0) == '-'){
            b = "-0";
        }
        if(c.length() == 1 && c.charAt(0) == '-'){
            c = "-0";
        }

        if(b.charAt(0) != '-'){
            p.fill(0);
            if(c.charAt(0) != '-'){
                p.text("f(x) = " + a + "x^2+" + b + "x+" + c, x + w/2, y + h/2);
            } else if(c.charAt(0) == '-'){
                p.text("f(x) = " + a + "x^2+" + b + "x" + c, x + w/2, y + h/2);
            }

        }

        if(b.charAt(0) == '-'){
            p.fill(0);
            if(c.charAt(0) != '-'){
                p.text("f(x) = " + a + "x^2" + b + "x+" + c, x + w/2, y + h/2);
            } else if(c.charAt(0) == '-'){
                p.text("f(x) = " + a + "x^2" + b + "x" + c, x + w/2, y + h/2);
            }

        }
    }

}