import java.lang.Math;

abstract class Calc {

    static float polyn(double x, double a, double b, double c) {
        return (float)(a * Math.pow(x, 2) + b * x + c);
    }
}