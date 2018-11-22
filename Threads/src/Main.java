public class Main {
    public static void main(String[] args) {
        Image i = new Image("images.jpg");
        i.exportImageToFile("final_image");
        i.exportGaussianBlurImageToFile("blure");
        i.exportGrayScaleImageToFile("gray");
        System.out.println("Iuliaaaa <3");
    }
}
