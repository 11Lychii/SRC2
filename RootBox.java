import processing.core.*;
import java.lang.Math;

class RootBox extends Box{

    float root1 = 0;
    float root2 = 0;

    RootBox(PApplet pap, int xPos, int yPos, int width, int height){
        super(pap, xPos, yPos, width, height);
    }

    void display(){
        p.fill(255);
        super.display();
        p.textSize(90);
    }

    float roundNumber(double input, double decimalPlaces) {
        double divisor = Math.pow(10d, decimalPlaces);
  
        return (float)(Math.round(input * divisor) / divisor);
    }

    void rootAmount(String ai, String bi, String ci){
        String a = ai, b = bi, c = ci;
        float d = 0;
        
        if(a.length() == 0){ a = "1"; }
        if(b.length() == 0){ b = "0"; }
        if(c.length() == 0){ c = "0"; }

        if (!a.equals("-") && !b.equals("-") && !c.equals("-")){
            int newX = x + w + 20;
            int newY = y - 50;
            int newW = w+240;
            int newH = h-70;
            d = Float.parseFloat(b)*Float.parseFloat(b) - 4*Float.parseFloat(a)*Float.parseFloat(c);

            if(d < 0){
                p.fill(0);
                p.text("0", x + w/2, y + h/2);
            }

            if(d == 0){
                p.fill(255);
                p.rect(newX, newY, newW, newH);

                p.fill(0);
                p.text("1", x + w/2, y + h/2);
                root1 = roundNumber((-1*Float.parseFloat(b)+Math.sqrt(d))/(2*Float.parseFloat(a)), 2d);
                p.text("x = " + root1, newX + newW/2, newY + newH/2);
            }

            if(d > 0){
                p.fill(255);
                p.rect(newX, newY, newW, newH);
                p.rect(newX, newY + 120, newW, newH);

                p.fill(0);
                p.text("2", x + w/2, y + h/2);
                root1 = roundNumber((-1*Float.parseFloat(b)+Math.sqrt(d))/(2*Float.parseFloat(a)), 2d);
                root2 = roundNumber((-1*Float.parseFloat(b)-Math.sqrt(d))/(2*Float.parseFloat(a)), 2d);
                p.text("x = " + root1, newX + newW/2, newY + newH/2);
                p.text("x = " + root2, newX + newW/2, newY + 120 + newH/2);
            }
        }
    }
}