# ImgToAscii
Img2Ascii is a simple Java program that converts an image into ASCII art. It reads an image file, calculates the luminosity of each pixel, and matches it to an ASCII character with similar brightness. The resulting ASCII art is then written to a file named "Ascii_Image".

## Customization

You can customize the ASCII character mapping by modifying the strChar method in the code. Adjust the luminosity thresholds and corresponding ASCII characters to achieve different visual effects in the ASCII art.

## Usage

Clone the repository or download the source code.
Compile the Java file using the command javac Img2Ascii.java.
Run the program with the command java Img2Ascii [imageFileName], where [imageFileName] is the name of the image file you want to convert.
Check the "Ascii_Image" file in the project directory to view the generated ASCII art.

### Example:
java Img2Ascii myImage.jpg

## Notes

The image file should be located in the "Images" directory relative to the project directory.
The program uses the luminosity of pixels to generate ASCII art, resulting in a grayscale representation of the image.
