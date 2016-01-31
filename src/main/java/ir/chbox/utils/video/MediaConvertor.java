package ir.chbox.utils.video;


import com.amazonaws.util.IOUtils;
import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;
import ir.chbox.utils.Watermark;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class MediaConvertor {
    private final Integer WIDTH = 320;
    private final Integer HEIGHT = 240;

    public final double SECONDS_BETWEEN_FRAMES = 10;
    public boolean capture = false;


    // The video stream index, used to ensure we display frames from one and
    // only one video stream from the media container.
    private int mVideoStreamIndex = -1;

    // Time of last frame write
    private long mLastPtsWrite = Global.NO_PTS;

    public final long MICRO_SECONDS_BETWEEN_FRAMES =
            (long) (Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
    /**
     * A container we'll use to write data from.
     */
    byte[] fileBytes;
    private BufferedImage thumbnail;

    public byte[] Convert(InputStream inputStream) {
        try {


            // create custom listeners
            MyVideoListener myVideoListener = new MyVideoListener(WIDTH, HEIGHT);
            Resizer resizer = new Resizer(WIDTH, HEIGHT);

            /**
             * Try to find an output format based on what the user specified, or
             * failing that, based on the outputURL (e.g. if it ends in .flv, we'll
             * guess FLV).
             */
            File input = File.createTempFile(new Date().toString(), ".mp4");


            fileBytes = IOUtils.toByteArray(inputStream);
            FileOutputStream write = new FileOutputStream(input);
            write.write(fileBytes);
            write.close();
            File file = File.createTempFile(new Date().toString(), ".mp4");
            file.deleteOnExit();

            // reader
            IMediaReader reader = ToolFactory.makeReader(input.getAbsolutePath());
            reader.addListener(resizer);
            reader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
            ImageSnapListener imageSnapListener = new ImageSnapListener();
            reader.addListener(imageSnapListener);

            // read out the contents of the media file and
            // dispatch events to the attached listener
            // writer
            IMediaWriter writer = ToolFactory.makeWriter(file.getAbsolutePath(), reader);
            resizer.addListener(writer);
            writer.addListener(myVideoListener);

            // show video when encoding
//        reader.addListener(ToolFactory.makeViewer(true));

            while (reader.readPacket() == null) {
                if (capture) {
                    reader.removeListener(imageSnapListener);
                }
                // continue coding
            }
            input.delete();
            new Watermark().doWatermark(file);
            return Files.readAllBytes(Paths.get(file.toURI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    private class ImageSnapListener extends MediaListenerAdapter {

        public void onVideoPicture(IVideoPictureEvent event) {

            if (event.getStreamIndex() != mVideoStreamIndex) {
                // if the selected video stream id is not yet set, go ahead an
                // select this lucky video stream
                if (mVideoStreamIndex == -1)
                    mVideoStreamIndex = event.getStreamIndex();
                    // no need to show frames from this video stream
                else
                    return;
            }

            // if uninitialized, back date mLastPtsWrite to get the very first frame
            if (mLastPtsWrite == Global.NO_PTS)
                mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;

            // if it's time to write the next frame
            if (event.getTimeStamp() - mLastPtsWrite >=
                    MICRO_SECONDS_BETWEEN_FRAMES) {

                //todo store thumbnail
                thumbnail=event.getImage();

                capture = true;
            }

        }


    }

    public BufferedImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
    }
}
