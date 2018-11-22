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

    private void grayScaleImageFilter() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                int sum = (int) (r[i][j] * 0.299 + g[i][j] * 0.587 + b[i][j] * 0.114);
                if (sum > 255)
                    grayScale[i][j] = 255;
                else
                    grayScale[i][j] = sum;
            }
    }

    public void exportGrayScaleImageToFile(String fileName) {
        grayScaleImageFilter();
        exportImage(fileName, createPixelMatrix(grayScale, grayScale, grayScale));
    }

    public void exportGaussianBlurImageToFile(String fileName) {
        gaussianBlurImageFilter();
        exportImage(fileName, blureMatrix);
    }

    private void gaussianBlurImageFilter() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                blureMatrix[i][j] = getAveragePixel(i, j, 9);
    }

    private int[] getAveragePixel(int x, int y, int size) {
        int[] average = new int[3];
        average[0] = 0;
        average[1] = 0;
        average[2] = 0;
        int pix = 0;
        for (int i = x - (size / 2); i < x + (size / 2) + 1; i++)
            if (i >= 0 && i < height)
                for (int j = y - 1; j < y + 2; j++)
                    if (j >= 0 && j < width)
                        if (x != i || y != j) {
                            pix++;
                            average[0] += r[i][j];
                            average[1] += g[i][j];
                            average[2] += b[i][j];
                        }
        average[0] = average[0] / pix;
        average[1] = average[1] / pix;
        average[2] = average[2] / pix;

        return average;
    }
}
