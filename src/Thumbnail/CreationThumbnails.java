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
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Thibaut
 */
public class CreationThumbnails {

    String pathFilePicture;
    String pathFileGif;
    Picture picture;
    
    /**
     * Construct an object to create Thumbnails (pic and gif)
     * @param getpathFile FilePath of the video
     * @param getname Name of the video
     */
    public CreationThumbnails(String getpathFile, String getname) {
        // FilePath intra-OS
        String filePath = System.getProperty("user.dir") + FilenameUtils.separatorsToSystem(getpathFile);
        // FilePath without format to put in db
        String outputFileDB = FilenameUtils.separatorsToSystem("\\Resources\\Video\\")+getname;
        this.createPicture(filePath, outputFileDB);
        this.createGif(outputFileDB);
    }
    
    /**
     * Function to create the pic
     * @param filePath FilePath of the video
     * @param outputFileDB FilePath without format to put in db
     */
    private void createPicture(String filePath, String outputFileDB){
        this.picture = new Picture(filePath);
        this.pathFilePicture = this.picture.dumpImageToFile(outputFileDB);
    }
    
    /**
     * Function to create the gif
     * @param outputFileDB FilePath without format to put in db
     */
    private void createGif(String outputFileDB){
        
        if (this.picture.getBufferedImage().size() > 1) {
            try {
                // grab the output image type from the first image in the sequence
                BufferedImage firstImage = this.picture.getBufferedImage().get(0);
                
                // create a new BufferedOutputStream with the last argument
                ImageOutputStream output = new FileImageOutputStream(new File(System.getProperty("user.dir")+outputFileDB+".gif"));
                
                // create a gif sequence with the type of the first image, 1 second
                // between frames, which loops continuously
                Gif writer = new Gif(output, firstImage.getType(), 50, false);
                
                // write out the first image to our sequence
                writer.writeToSequence(firstImage);
                //write out all others frames to our sequence
                for (int i = 1; i < this.picture.getBufferedImage().size(); i++) {
                    BufferedImage nextImage = this.picture.getBufferedImage().get(i);
                    writer.writeToSequence(nextImage);
                }
                
                writer.close();
                output.close();
                
                this.pathFileGif = outputFileDB+".gif";
            } catch (IOException ex) {
                System.out.println("[CreateGif]Creation of file of gif is failed");
            }
        } else {
            System.out.println("[CreateGif]One frame in video");
        }
    }

    /**
     * get the FilePath of the picture create
     * @return String 
     */
    public String getPathFilePicture() {
        return pathFilePicture;
    }

    /**
     * get the FilePath of the gif create
     * @return String
     */
    public String getPathFileGif() {
        return pathFileGif;
    }
    
}
