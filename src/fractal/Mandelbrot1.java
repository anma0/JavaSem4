package fractal;

public class Mandelbrot1 implements Fractal {
    @Override
    public double getColor(double x, double y) {
        double rez = 0;
        double imz = 0;
        double R = 10e9;
        int N = 1000;
        for (int i = 0; i < N; i++) {
            double rez_n = rez * rez - imz * imz + x;
            double imz_n = 2 * rez * imz + y;
            rez = rez_n;
            imz = imz_n;
            double abs2 = rez * rez + imz * imz;
            if (abs2 > R * R) {
                double fix = Math.log(Math.log(abs2) / Math.log(R) / 2) / Math.log(2);
//                double fix = 0;
                return (i - fix) / N;
            }
        }
        return 1;
    }
}
