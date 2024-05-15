import processing.core.*;
import java.lang.*;
class TextBox extends ClickBox{
    float a;
    String t = "0";
    boolean selected;

    TextBox(PApplet pap, int xPos, int yPos, int width, int height){
        super(pap, xPos, yPos, width, height);
    }

    void clicked(){
        super.clicked();
        selected = true;
    }

    void elseClicked() {
        selected = false;
    }

    void keyPressed() {
        if((p.key >= '0' && p.key <= '9' || p.key == '-' && t.length() == 0) && selected && t.length() < 4) {
            t += p.key;
        }
        
        if(p.key == p.BACKSPACE && t.length() > 0 && selected) {
            t = t.substring(0, t.length() - 1);
        }

        if(p.key == '.' && !t.contains(".") && (!t.contains("-") && t.length() > 0 || t.contains("-") && t.length() > 1) && selected) {
            t += p.key;
        }
    }

    void display(){
        if(selected == false){
            p.fill(255);
        } else {
            p.fill(164);
        }
        super.display();
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.textSize(90);
        p.text(t, x + w/2, y + h/2);
    }
    
}