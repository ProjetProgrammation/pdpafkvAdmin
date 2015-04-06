/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thumbnail;

import java.awt.image.BufferedImage;
import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.ToolFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Thibaut
 */
public class Picture {
    
    private final SliceFrame sFrame;
    
    public Picture(String filePath) {
        
        
        IMediaReader mediaReader = ToolFactory.makeReader(filePath);

        //Stipulate that we want BufferedImages created in BGR 24bit color space
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        //Call the functiun to create all frame
        sFrame = new SliceFrame();
        mediaReader.addListener(sFrame);

        // read out the contents of the media file and dispatch events to the attached listener
        while (mediaReader.readPacket() == null){};    
    }
    
    /**
     * Save the ThumBnail picture and return the filePath
     * @param outputFile
     * @return String the FilePath of the Pic.
     */
    public String dumpImageToFile(String outputFile) {
        BufferedImage image = sFrame.getImages().get(0);
        String outputFilename = outputFile + ".jpg";
        try {
            File f = new File(System.getProperty("user.dir") + outputFilename);
            ImageIO.write(image, "jpg", f);
            return outputFilename;        
        } catch (IOException ex) {
            System.out.println("[dumpImageToFile]Creation of file of picture is failed");
            return null;
        }
    }
    
    /**
     * Get the ArrayList of the SliceFrame
     * @return ArrayList<>
     */
    public ArrayList<BufferedImage> getBufferedImage(){
        return sFrame.getImages();
    }
}