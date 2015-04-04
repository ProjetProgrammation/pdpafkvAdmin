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

        // stipulate that we want BufferedImages created in BGR 24bit color space
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        
        sFrame = new SliceFrame();
        mediaReader.addListener(sFrame);

        // read out the contents of the media file and dispatch events to the attached listener
        while (mediaReader.readPacket() == null){};    
    }
    
    public String dumpImageToFile(String outputFile) throws IOException {
        BufferedImage image = sFrame.getImages().get(0);
            String outputFilename = outputFile + ".jpg";
            ImageIO.write(image, "jpg", new File(System.getProperty("user.dir") + outputFilename));
            return outputFilename;        
    }
    
    public ArrayList<BufferedImage> getBufferedImage(){
        return sFrame.getImages();
    }
}
