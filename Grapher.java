

 import processing.core.*;
 import java.lang.Math;
 
 class Grapher {
      int x = 1020, y = 20, w = 960, h = 960;
      float xmult = 1, ymult = 1, startx = -400, endx = 400, starty = -400, endy = 400;
 
     PApplet p;

      float capx(float xin) {
         float returnValue = xin;
         
         if(xin < startx) { returnValue = startx; }
         if(xin > endx) { returnValue = endx; }
         
         return returnValue;
     }
 
      float capy(float yin) {
         float returnValue = yin;
         
         if(yin < starty) { returnValue = starty; }
         if(yin > endy) { returnValue = endy; }
         
         return returnValue;
     }

      void line(float x1, float y1, float x2, float y2, int sw) {
         p.strokeWeight(sw);
         p.line(xToPixels(x1), yToPixels(y1), xToPixels(x2), yToPixels(y2));
     }
 
      int xToPixels(float input) {
         return (int)((capx(input) - startx) * xmult + x);
     }
 
      int yToPixels(float input) {
         return (int)(y + h - (capy(input) - starty) * ymult);
     }
 
      float xToMath(int input) {
         return (float)(input / xmult + startx);
     }
 
      float yToMath(int input) {
         return (float)(input / ymult + starty);
     }
 
      int gX(int input) {
         return input + x;
     }
 
      int gY(int input) {
         return y + h - input;
     }
 
      Grapher(PApplet p) {
         this.p = p;
     }
 
      void drawAxes() {
         p.stroke(128);
         line(-10000000, 0, 10000000, 0, 2);
         line(0, -10000000, 0, 10000000, 2);
     }
 
      void renderGraph(String sa, String sb, String sc, float xstart, float xend) {

        double a = 0, b = 0, c = 0;

        if(sa.length() > 0) { 
            String tempa = sa; 
            if(sa.charAt(0) == '-') {sa = sa.substring(1);} 
            if(sa == "") {a = 0;} else {a = Double.parseDouble(sa);} 
            if(tempa.charAt(0) == '-') {a *= -1;} 
        }
        if(sb.length() > 0) {
            String tempb = sb;
            if(sb.charAt(0) == '-') {sb = sb.substring(1);} 
            if(sb == "") {b = 0;} else {b = Double.parseDouble(sb);}
            if(tempb.charAt(0) == '-') {b *= -1;}
        }
        if(sc.length() > 0) {
            String tempc = sc;
            if(sc.charAt(0) == '-') {sc = sc.substring(1);} 
            if(sc == "") {c = 0;} else {c = Double.parseDouble(sc);}
            if(tempc.charAt(0) == '-') {c *= -1;}
        }
        
         startx = xstart;
         endx = xend;
 
         float previousy = Calc.polyn(xstart, a, b, c);
         float highesty = previousy, lowesty = previousy;
         float newy;
       
         for(int i = 1; i < w; i++) {
             newy = Calc.polyn(xToMath(i), a, b, c);
             line(xToMath(i-1), previousy, xToMath(i), newy, 5);
             previousy = newy;

             if(newy > highesty) { highesty = newy; }
             if(newy < lowesty) { lowesty = newy; }
         }

         starty = lowesty;
         endy = highesty;

         xmult = w / (float)Math.abs(endx - startx);
         ymult = h / (float)Math.abs(endy - starty);

         
         p.println("StartX - EndX: " + startx + " to " + endx);
         p.println("StartY - EndY: " + starty + " to " + endy);
         p.println("Xfactor Yfactor: " + xmult + " | " + ymult);
         
     }
 
      void display() {
         p.fill(255);
         p.strokeWeight(5);
         p.stroke(0);
         p.rect(x, y, w, h);
         drawAxes();
     }
 }