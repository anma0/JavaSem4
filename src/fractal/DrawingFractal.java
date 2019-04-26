package fractal;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DrawingFractal extends Application {
    private double x0 = -2;
    private double y0 = 2;
    private double dx = 0.01;


    private Pane mainPane;
    private ImageView imgView = new ImageView();
    private FileChooser fileChooser = new FileChooser();

    private Fractal fractal = new Mandelbrot1();
    private Palette palette = new HSBPalette();

    private Task<WritableImage> task = null;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Фрактал");

        initInterface();
        primaryStage.setScene(new Scene(mainPane));

        mainPane.setPrefSize(400, 400);

        InvalidationListener sizeChangeListener = a -> drawFractal();
        mainPane.widthProperty().addListener(sizeChangeListener);
        mainPane.heightProperty().addListener(sizeChangeListener);

        configuringFileChooser(fileChooser);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            //System.out.println("key_pressed" + ke.getCode());
            switch (ke.getCode()) {
                case UP:
                    y0 += dx * 100;
                    drawFractal();
                    break;
                case DOWN:
                    y0 -= dx * 100;
                    drawFractal();
                    break;
                case RIGHT:
                    x0 += dx * 100;
                    drawFractal();
                    break;
                case LEFT:
                    x0 -= dx * 100;
                    drawFractal();
                    break;
                case EQUALS:
                case MINUS:
                    double dx1 = ke.getCode() == KeyCode.EQUALS ? dx / 1.2 : dx * 1.2;
                    x0 += mainPane.getWidth() * (dx - dx1) / 2;
                    y0 -= mainPane.getHeight() * (dx - dx1) / 2;
                    dx = dx1;
                    drawFractal();
                    break;
                case S:
                    File dir = fileChooser.showSaveDialog(primaryStage);
                    if (dir != null)
                        saveImage(dir);
                case DIGIT1:
                    palette = new GradientBW();
                    drawFractal();
                    break;
                case DIGIT2:
                    palette = new HSBPalette();
                    drawFractal();
                    break;
            }
        });

        primaryStage.show();
    }

    private void saveImage(File dir) {
        Image fractal = imgView.getImage();
        File outputFile = new File(dir.getAbsolutePath());
//        BufferedImage imageRGB = new BufferedImage((int)fractal.getWidth(), (int)fractal.getHeight(), BufferedImage.TYPE_INT_RGB);
//        SwingFXUtils.fromFXImage(fractal, imageRGB); !!!!!!!!!!!!!!!!!!!!!!!!!!!
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(fractal, null), "png", outputFile);
        } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }


    private Parent initInterface() {
        //1) pane - сохранить как поле класса
        //2) при рисовании фрактала спрашивать размеры у pane
        //3) добавить слушателей на изменение размера pane (свойства width, height) - вызвать drawFractal
        //4) если какой-то размер 0, в методе drawFractal не надо ничего делать
        mainPane = new Pane(imgView);
        return mainPane;
    }

    private void drawFractal() {
        if (task != null)
            task.cancel();
        int width = (int) mainPane.getWidth();
        int height = (int) mainPane.getHeight();
        if (width <= 0 || height <= 0)
            return;

        Task<WritableImage> t = new Task<WritableImage>() {
            @Override
            protected WritableImage call() throws Exception {
                WritableImage wImage = new WritableImage(width, height);
                PixelWriter pWriter = wImage.getPixelWriter();

                for (int xl = 0; xl < width - 1; xl++) {
                    for (int yl = 0; yl < height - 1; yl++) {
                        double x = x0 + xl * dx;
                        double y = y0 - yl * dx;
                        double colorInd = fractal.getColor(x, y);
                        Color color = palette.getColor(colorInd);
                        pWriter.setColor(xl, yl, color);
                    }
                    if (isCancelled())
                        return null;
                    WritableImage copyImage = new WritableImage(wImage.getPixelReader(), width, height);
                    updateValue(copyImage);
                }
                return wImage;
            }
        };
        t.valueProperty().addListener(v -> imgView.setImage(task.getValue()));
        new Thread(t).start();
        task = t;
        t.onSucceededProperty().addListener(s -> task = null);
    }

    private void configuringFileChooser(FileChooser fileChooser) {
        // Set title
        fileChooser.setTitle("Select Some Directories");
        // Set Initial Directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
    }
}