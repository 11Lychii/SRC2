/*
 * I denne fil bruges ordene reel og matematisk rigtig meget. Når noget ses som matematisk, ses det, som det, grafen afbilder.
 * Altså, en matematisk ændring med 1 på x-aksen svarer til at øge den uafhængige variabel i funktionen med 1. Grafen kommer
 * altid reelt set til at være 800*800 pixels, men dens matematiske start- og slutpunkter ændrer sig, afhængig af, hvad man sætter.
 * Da den matematiske afstand mellem venstre og højre side af grafen kan være alt fra 0.1 til 1000000 (og det samme med øverst og nederst),
 * er xmult og ymult brugt til at beskrive, hvor mange pixels der er hen over en matematisk ændring af x på 1.
 * 
 * FOR EKSEMPEL:
 * Hvis den matematiske bredde / definitionsmængden af en graf er lig 800 (altså det sammme som den reele bredde), er xmult bare 1, 
 * da der er 1 pixel for hver 1, man går hen på x-værdien.
 * 
 * Hvis den matematiske bredde derimod er mindre end den reele (hvis man f.eks. prøver at afbilde en graf mellem x = -4 og x = 4), 
 * går der mange pixels pr. 1 ændring af x. I dette specifikke tilfælde ville xmult være 100, da den matematiske bredde af grafen
 * er 100 gange mindre end den reele, og der dermes går 100 pixels vandret på 1 ændring af x.
 * 
 * BTW i java omdannes typer anderledes end man normalt ville gøre i processing. Hvis jeg vil have float værdien af 17+3, så er det:
 * (float)(17+3)
 * Det er meget mystisk, men man vænner sig til det i guess
 * 
 * Føl dig ikke skyldig, hvis du skal læse koden igennem 10-15 gange for at forstå det. Det kommer jeg også til at skulle gøre i morgen :(
 * 
 * 13/06/2024 | 02:12 AM
 */

 import processing.core.*;
 import java.lang.Math;
 
 class Grapher {
      int x = 1020, y = 20, w = 960, h = 960;
     //xmult er hvor mange pixels der er på 1 ændring i x-aksen matematisk. Samme med y.
     //Start og slut er de matematiske start- og slutpunkter af grafen
      float xmult = 1, ymult = 1, startx = -400, endx = 400, starty = -400, endy = 400;
 
     PApplet p;
 
     //capx og capy gør, så linjerne ikke går ud over grafen
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
 
     //Tager de matematiske koordinater af to punkter på en linje og tegner dem på grafen.
     //Her omvendes y blandt andet (da y går opad i et traditionelt koordinatsystem, men nedad i programmering)
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
 
     //Tegner de to akser
      void drawAxes() {
         p.stroke(128);
         line(-10000000, 0, 10000000, 0, 2);
         line(0, -10000000, 0, 10000000, 2);
     }
 
     //Tegner grafen udfra funktionens konstanter samt x-rækkevidden. Denne køres i draw()
      void renderGraph(String sa, String sb, String sc, float xstart, float xend) {

        // lav 3 variabler, af typen double
        double a = 0, b = 0, c = 0;

        //slet "-" tegn i starten af stringen, hvis der er et
        if(sa.length() > 0) { // hvis stringen ikke er tom
            String tempa = sa; // gem stringen i en temp variabel, da vi skal bruge den senere
            if(sa.charAt(0) == '-') {sa = sa.substring(1);} // fjern "-" tegnet i starten, da den ellers fucker med parsing til double
            if(sa == "") {a = 0;} else {a = Double.parseDouble(sa);} // hvis sa ikke er "", så parse til en double 
            if(tempa.charAt(0) == '-') {a *= -1;} // da vi fjernede "-", skal den tilføjes tilbage hvis den var der, (gang med -1)
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
        
        // hvis en af de 3 strings er tom, så sættes variablen til 0

        // da vi slettede "-" tegnet i starten af stringen, skal vi nu gange double variablen med -1, hvis der var et "-" tegn i starten
         
         
         //opdaterer hele classens startx og endx værdier
         startx = xstart;
         endx = xend;
 
         //previousy er variablen, der gemmer y-værdien fra forrige udregning. Se Calc.java på mere detalje om, hvordan denne her funktion fungerer
         float previousy = Calc.polyn(xstart, a, b, c);
         //Disse bruges til at finde funktionens ekstrenumpunkter indenfor rækkevidden. Kan ikke starte på 0 fordi den jo KAN være lavere.
         float highesty = previousy, lowesty = previousy;
         //newy gemmer værdien for den mest nylige udregning
         float newy;
         //Går vandret gennem alle 800 pixels på skærmen
         for(int i = 1; i < w; i++) {
             //omregner fra pixel på skærmen til dens tilsvarende matematiske værdi, hvorefter tilsvarende y-værdi udregnes
             newy = Calc.polyn(xToMath(i), a, b, c);
             //Tegner en linje fra det sidste y til det nuværedne
             line(xToMath(i-1), previousy, xToMath(i), newy, 5);
             //Opdaterer previousy til det næste loop. Således dannes en kæde af streger, der former den endelige graf
             previousy = newy;
             //Søger ekstrenumpunkter
             if(newy > highesty) { highesty = newy; }
             if(newy < lowesty) { lowesty = newy; }
         }
         //Efter det hele er overstået, sættes grafens start- og slut- y-værdier til den øverste og nederste i funktionen
         starty = lowesty;
         endy = highesty;
         //Opdaterer forholdet mellem pixels og matematisk ændring
         xmult = w / (float)Math.abs(endx - startx);
         ymult = h / (float)Math.abs(endy - starty);
         //Debugging statements
         
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