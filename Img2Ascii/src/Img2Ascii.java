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
     *
     */
    public Img2Ascii() {
        try {
            writer = new PrintWriter(fw = new FileWriter("Ascii_Image", true));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     *
     * @param imgName
     */
    private void convert(String imgName){
        System.out.println("Converting " + imgName + " to ascii....");
        //read file in
        try{
            img = ImageIO.read(new File("Images/" + imgName));
        }catch (IOException ex){
            System.out.println(ex);
        }
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
     * Receives the input param of a pixel's luminosity and matches it to an ascii char of similar brightness.
     * @param pixVal
     * @return ascii value matching pixVal.
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
     * Print the ascii value for the pixel.
     * @param str
     */
    private void print(String str){
        try{
            writer.print(str);
            writer.flush();
            fw.flush();
        }catch (Exception e){}
    }

    public static void main(String[] args) {
        Img2Ascii obj = new Img2Ascii();
        obj.convert("face.jpg");
        System.out.println("Finished!");
    }
}
