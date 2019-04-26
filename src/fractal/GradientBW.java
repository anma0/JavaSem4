package fractal;

import javafx.scene.paint.Color;


public class GradientBW implements Palette {
    @Override
    public Color getColor(double ind) {
        int c = (int) (ind * 256);
        return Color.gray(ind);
    }
}

