/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thumbnail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author Thibaut
 */
public class CreationThumbnails {

    String pathFilePicture;
    String pathFileGif;
    Picture picture;
    
    public CreationThumbnails(String getpathFile, String getname) throws IOException {
        String filePath = System.getProperty("user.dir") + getpathFile;
        String outputFileDB = "\\Ressources\\"+getname;
        this.createPicture(filePath, outputFileDB);
        this.createGif(outputFileDB);
    }
    
    private void createPicture(String filePath, String outputFileDB) throws IOException{
        this.picture = new Picture(filePath);
        this.pathFilePicture = this.picture.dumpImageToFile(outputFileDB);
    }
    
    private void createGif(String outputFileDB) throws IOException{
        
        if (this.picture.getBufferedImage().size() > 1) {
            // grab the output image type from the first image in the sequence
            BufferedImage firstImage = this.picture.getBufferedImage().get(0);
            
            // create a new BufferedOutputStream with the last argument
            ImageOutputStream output = new FileImageOutputStream(new File(System.getProperty("user.dir")+outputFileDB+".gif"));

            // create a gif sequence with the type of the first image, 1 second
            // between frames, which loops continuously
            Gif writer = new Gif(output, firstImage.getType(), 50, false);

            // write out the first image to our sequence...
            writer.writeToSequence(firstImage);
            for (int i = 1; i < this.picture.getBufferedImage().size(); i++) {
                BufferedImage nextImage = this.picture.getBufferedImage().get(i);
                writer.writeToSequence(nextImage);
            }

            writer.close();
            output.close();
            
            this.pathFileGif = outputFileDB+".gif";
            
        } else {
            System.out.println(
                    "Usage: java GifSequenceWriter [list of gif files] [output file]");
        }
    }

    public String getPathFilePicture() {
        return pathFilePicture;
    }

    public String getPathFileGif() {
        return pathFileGif;
    }
    
}
