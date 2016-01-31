package ir.chbox.utils.video;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

import java.io.InputStream;

/**
 * Created by farzad on 1/30/16.
 */
public class VideoUtils {
    // Create a Xuggler container object
    IContainer container = IContainer.make();
    int height;
    int width;

    public void sizeDetector(InputStream inputStream) {
// Open up the container
        if (container.open(inputStream, null) < 0)
            throw new IllegalArgumentException("could not open file: ");

        // query how many streams the call to open found
        int numStreams = container.getNumStreams();

        // and iterate through the streams to find the first video stream
        int videoStreamId = -1;
        IStreamCoder videoCoder = null;
        for (int i = 0; i < numStreams; i++) {
            // Find the stream object
            IStream stream = container.getStream(i);
            // Get the pre-configured decoder that can decode this stream;
            IStreamCoder coder = stream.getStreamCoder();

            if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
                videoStreamId = i;
                videoCoder = coder;
                break;
            }
        }
        if (videoStreamId == -1)
            throw new RuntimeException("could not find video stream in container: ");

/*
 * Now we have found the video stream in this file.
 * Let's open up our decoder so it can do work.
 */
        if (videoCoder.open() < 0)
            throw new RuntimeException("could not open video decoder for container: ");

        // here you have what you need
        height = videoCoder.getHeight();
        width = videoCoder.getWidth();


    }

    public IContainer getContainer() {
        return container;
    }

    public void setContainer(IContainer container) {
        this.container = container;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
