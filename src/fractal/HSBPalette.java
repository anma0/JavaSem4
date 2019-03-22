package fractal;

import javafx.scene.paint.Color;


public class HSBPalette implements Palette {
    @Override
    public Color getColor(double ind) {

        return Color.hsb(ind * 360 * 10 % 360, 0.8, 1);
    }
}

