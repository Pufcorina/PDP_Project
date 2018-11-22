public class Main {
    public static void main(String[] args) {
        Image i = new Image("images.jpg");

        float start =  System.nanoTime() / 1000000;
        i.exportGrayScaleImageToFile("gray");
        float end = System.nanoTime() / 1000000;

        System.out.println("Exec time: " + (end - start) / 1000);



        System.out.println("Iuliaaaa <3");
    }
}
