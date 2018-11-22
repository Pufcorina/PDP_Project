import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private int height;
    private int width;
    private String filePath;

    private int[][] r;
    private int[][] g;
    private int[][] b;
    private int[][] grayScale;
    private int[][][] blureMatrix;

    public Image(String filePath) {
        this.filePath = filePath;
        readImageFromFile();
    }

    private void readImageFromFile() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath));
            height = img.getHeight();
            width = img.getWidth();
            r = new int[height][width];
            g = new int[height][width];
            b = new int[height][width];
            grayScale = new int[height][width];
            blureMatrix = new int[height][width][3];

            int[] aux;
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++) {
                    aux = img.getRaster().getPixel(j, i, new int[3]);
                    r[i][j] = aux[0];
                    g[i][j] = aux[1];
                    b[i][j] = aux[2];
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[][][] createPixelMatrix(int[][] r, int[][] g, int[][] b) {
        int[][][] pixelMatrix = new int[height][width][3];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                int[] pixel = new int[3];
                pixel[0] = r[i][j];
                pixel[1] = g[i][j];
                pixel[2] = b[i][j];
                pixelMatrix[i][j] = pixel;
            }

        return pixelMatrix;
    }

    public void exportImageToFile(String filePath) {
        exportImage(filePath, createPixelMatrix(r, g, b));
    }

    private void exportImage(String fileName, int[][][] pixelMatrix) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                img.getRaster().setPixel(j, i, pixelMatrix[i][j]);
            }
        fileName = fileName + ".jpg";
        File outputfile = new File(fileName);
        try {
            ImageIO.write(img, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}