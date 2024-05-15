import processing.core.*;
abstract class Box {
    int x, y, w, h;
    PApplet p;

    Box(PApplet pap, int xPos, int yPos, int width, int height){
        p = pap;
        x = xPos;
        y = yPos;
        w = width;
        h = height;
    }    

    void display(){
        p.rect(x, y, w, h);
    }
}