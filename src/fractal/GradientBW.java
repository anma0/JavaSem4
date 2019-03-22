package fractal;

import javafx.scene.paint.Color;


public class GradientBW implements Palette {
    @Override
    public Color getColor(double ind) {
//        int c = (int) (ind * 256);
//        if (c != 0 && c != 1 && c != 255 && c != 256)
//            System.out.println(c);
        return Color.gray(ind);
    }
}

