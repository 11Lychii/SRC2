import processing.core.*;
abstract class ClickBox extends Box{

    ClickBox(PApplet pap, int xPos, int yPos, int width, int height){
        super(pap, xPos, yPos, width, height);
    }
      void checkClick(){
        if(p.mouseX >x && p.mouseX<x+w && p.mouseY>y && p.mouseY<y+h) {
            clicked();
        } else {
            elseClicked();
        }
        
    }

    void clicked(){
        p.fill(164);
    }

    void elseClicked() {

    }

    
}