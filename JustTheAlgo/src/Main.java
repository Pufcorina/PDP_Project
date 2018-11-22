public class Main {
    public static void main(String[] args) {
        Image i = new Image("C:\\Users\\Cwori\\Desktop\\images.jpg");
        i.exportGrayScaleImageToFile("gray.jpg");
        i.exportGaussianBlurImageToFile("blur.jpg");
        i.exportImageToFile("image.jpg");
    }
}
