using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MPI_ImageFilter
{
    class Controller
    {
        public void run()
        {
            while (true)
            {
                printMenu();
                try
                {
                    int input = Convert.ToInt32(Console.ReadLine());
                    switch (input)
                    {
                        case 0: Environment.Exit(0); break;
                        case 1: gaussianBlur(); break;
                        case 2: grayScale(); break;
                        default:
                            Console.WriteLine("Invalid option");
                            break;
                    }
                }
                catch (System.IO.IOException e)
                {
                    Console.WriteLine(e.Message);
                }
            }
        }

        private void grayScale()
        {
            Image img2048x1174 = new Image("../../../../data/animal-beagle-canine-2048x1174.jpg");
            Console.WriteLine("Time for 2048x1174 is: " + img2048x1174.exportGrayScaleImageToFile("../../../data/gray_img2048x1174") + " seconds");

            Image img640x336 = new Image("../../../../data/pexels-photo-640x336.jpeg");
            Console.WriteLine("Time for 640x336 is: " + img640x336.exportGrayScaleImageToFile("../../../data/gray_img640x336") + " seconds");

            Image img1280x733 = new Image("../../../../data/pexels-photo-1280x733.jpeg");
            Console.WriteLine("Time for 1280x733 is: " + img1280x733.exportGrayScaleImageToFile("../../../data/gray_img1280x733") + " seconds");
        }

        private void gaussianBlur()
        {
            Image img2048x1174 = new Image("../../../../data/animal-beagle-canine-2048x1174.jpg");
            Console.WriteLine("Time for 2048x1174 is: " + img2048x1174.exportGaussianBlurImageToFile("../../../data/blur_img2048x1174") + " seconds");

            Image img640x336 = new Image("../../../../data/pexels-photo-640x336.jpeg");
            Console.WriteLine("Time for 640x336 is: " + img640x336.exportGaussianBlurImageToFile("../../../data/blur_img640x336") + " seconds");

            Image img1280x733 = new Image("../../../../data/pexels-photo-1280x733.jpeg");
            Console.WriteLine("Time for 1280x733 is: " + img1280x733.exportGaussianBlurImageToFile("../../../data/blur_img1280x733") + " seconds");
        }

        private void printMenu()
        {
            Console.WriteLine();
            Console.WriteLine("Choose which image filter do you want to apply:");
            Console.WriteLine("1) Gaussian Blur");
            Console.WriteLine("2) Gray scale");
            Console.WriteLine("0) Exit");
            Console.WriteLine("Your choice: ");
            Console.WriteLine();
        }
    }
}
