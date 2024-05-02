import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Img2Ascii {

    private BufferedImage img;
    private PrintWriter writer;
    private FileWriter fw;
    private double pixVal;

    /**
     * Constructor of Img2Ascii class.
     * Initializes a PrintWriter object to write ASCII art to a file named "Ascii_Image".
     * If file already exists it will be appended to.
     */
    public Img2Ascii() {
        try {
            writer = new PrintWriter(fw = new FileWriter("Ascii_Image", true));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Converts the specified image to ASCII art.
     * Load the image, then iterate through all the pixels finding their colour and luminosity which are then matched
     * to an ASCII char with similar brightness.
     * @param imgName
     */
    private void convert(String imgName){
        System.out.println("Converting " + imgName + " to ASCII....");

        loadImg(imgName);

        //iterate over pixels finding luminosity and ascii value to match
        for(int i = 0; i < img.getHeight(); i++){
            for(int j = 0; j < img.getWidth(); j++){
                //get color at pixel
                Color pixCol = new Color(img.getRGB(j,i));
                //get luminosity of pixel colour
//                pixVal = ((0.2126 * pixCol.getRed()) + (0.7152*pixCol.getGreen()) + (0.0722 * pixCol.getBlue()));
                pixVal = (((pixCol.getRed() * 0.30) + (pixCol.getBlue() * 0.59) + (pixCol
                        .getGreen() * 0.11)));
                print(strChar(pixVal));
            }
            //print line separator after last row pixel
            try{
                writer.println("");
                writer.flush();
                fw.flush();
            }catch (Exception e){
            }
        }
    }

    /**
     * Receives the input param of a pixel's luminosity and matches it to an ASCII char of similar brightness.
     * @param pixVal
     * @return ASCII value matching pixVal.
     */
    private String strChar(double pixVal){
        String str;
        if(pixVal >= 240){
            str = " ";
        }else if(pixVal >= 210){
            str = ".";
        }else if(pixVal >= 190){
            str = "*";
        }else if(pixVal >= 170){
            str = "+";
        }else if(pixVal >= 120){
            str = "^";
        }else if(pixVal >= 110){
            str = "&";
        }else if(pixVal >= 80){
            str = "8";
        }else if(pixVal >= 60){
            str = "#";
        }else{
            str = "@";
        }
        return str;
    }

    /**
     * Print the ASCII value for the pixel.
     * @param str
     */
    private void print(String str){
        try{
            writer.print(str);
            writer.flush();
            fw.flush();
        }catch (Exception e){}
    }

    /**
     * Loads an image from the specified file path and assigns it to the 'img' field.
     * The image file is expected to be located in the "Images" directory.
     * @param file
     */
    private void loadImg(String file){
        try{
            img = ImageIO.read(new File("Images/" + file));
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        Img2Ascii obj = new Img2Ascii();
        obj.convert(args[0]);
        System.out.println("Finished!");
    }
}
