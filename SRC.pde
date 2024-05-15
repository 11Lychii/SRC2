import processing.core.*;

TextBox boxA;
TextBox boxB;
TextBox boxC;
DisplayBox boxD;
RootBox boxF;
Grapher grapher;

void setup() {
    size(2000, 1000);
    boxA = new TextBox(this, 200, 350, 800, 100);
    boxB = new TextBox(this, 200, 470, 800, 100);
    boxC = new TextBox(this, 200, 590, 800, 100);
    boxD = new DisplayBox(this, 20, 230, 980, 100);
    boxF = new RootBox(this, 20, 810, 360, 170);
    grapher = new Grapher(this);

}

void draw() {
    layout();

    boxA.display();
    boxB.display();
    boxC.display();

    boxD.display();
    boxD.showFunction(boxA.t, boxB.t, boxC.t);

    boxF.display();
    boxF.rootAmount(boxA.t, boxB.t, boxC.t);

    grapher.display();
    grapher.renderGraph(boxA.t, boxB.t, boxC.t, -10, 10);
}

void mousePressed(){
    boxA.checkClick();
    boxB.checkClick();
    boxC.checkClick();

}

void keyPressed(){
    boxA.keyPressed();
    boxB.keyPressed();
    boxC.keyPressed();

}

void layout(){
    background(200);
    textSize(180);
    fill(0);
    text("Parabler", 400, 120);
    textSize(150);
    text("a:", 100, 380);
    text("b:", 100, 520);
    text("c:", 100, 630);
    textSize(100);
    text("Rødder:", 180, 750);
    fill(255);
}