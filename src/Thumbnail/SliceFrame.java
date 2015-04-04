/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thumbnail;

import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Thibaut
 */
public class SliceFrame extends MediaListenerAdapter {
        
    private final double SECONDS_BETWEEN_FRAMES = 0.001;
    private final long MICRO_SECONDS_BETWEEN_FRAMES = (long) (Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
    // The video stream index, used to ensure we display frames from one and
    // only one video stream from the media container.
    private static int mVideoStreamIndex = -1;
    // Time of last frame write
    private static long mLastPtsWrite = Global.NO_PTS;
    private final ArrayList<BufferedImage> images = new ArrayList();
    
    
    
    @Override
    public void onVideoPicture(IVideoPictureEvent event) {

        if (event.getStreamIndex() != mVideoStreamIndex) {
                // if the selected video stream id is not yet set, go ahead an select this lucky video stream
                if (mVideoStreamIndex == -1) {
                    mVideoStreamIndex = event.getStreamIndex();
                } // no need to show frames from this video stream
                else {
                    return;
                }
            }

            // if uninitialized, back date mLastPtsWrite to get the very first frame
            if (mLastPtsWrite == Global.NO_PTS) {
                mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;
            }

            // if it's time to write the next frame
            if (event.getTimeStamp() - mLastPtsWrite >= MICRO_SECONDS_BETWEEN_FRAMES) {
                    
                BufferedImage resizedImage = new BufferedImage(129, 81, event.getImage().getType());
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(event.getImage(), 0, 0, 129, 81, null);
                g.dispose();
                this.BufferedImageInArray(resizedImage);
                                    
                // update last write time
                mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
            }
        }
    
    private void BufferedImageInArray(BufferedImage image) {
        this.images.add(image);
    }
      
    public ArrayList<BufferedImage> getImages() {
        return images;
    }
    
}
