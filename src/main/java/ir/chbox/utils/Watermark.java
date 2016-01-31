package ir.chbox.utils;

import com.xuggle.mediatool.*;
import com.xuggle.mediatool.event.IVideoPictureEvent;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by farzad on 11/24/15.
 */
public class Watermark {

    /**
     * Create and display a number of bouncing balls on the
     */

    public File doWatermark(File inputFile) throws IOException {

        File outputFile = File.createTempFile(new Date().toString(), ".mp4");
        outputFile.deleteOnExit();

        // create a media reader and configure it to generate BufferImages

        IMediaReader reader = ToolFactory.makeReader(inputFile.toString());
        reader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        // create a writer and configure it's parameters from the reader

        IMediaWriter writer = ToolFactory.makeWriter(outputFile.toString(), reader);

        // create a tool which paints video time stamp into frame

        IMediaTool addTimeStamp = new TimeStampTool();

        // create a tool which reduces audio volume to 1/10th original


        // create a tool chain:
//           reader -> addTimeStamp -> reduceVolume -> writer

        reader.addListener(addTimeStamp);
        addTimeStamp.addListener(writer);

        // add a viewer to the writer, to see media modified media

//        writer.addListener(ToolFactory.makeViewer());

        // read and decode packets from the source file and
        // then encode and write out data to the output file

        while (reader.readPacket() == null)
            do {} while(false);

        return outputFile;
    }

    /**
     * Create a tool which adds a time stamp to a video image.
     */

    static class TimeStampTool extends MediaToolAdapter
    {
        /** {@inheritDoc} */

        @Override
        public void onVideoPicture(IVideoPictureEvent event)
        {
            // get the graphics for the image

            Graphics2D g = event.getImage().createGraphics();

            // establish the timestamp and how much space it will take

            String timeStampStr ="CHALLENGEBOX.COM";
            Rectangle2D bounds = g.getFont().getStringBounds(timeStampStr,
                    g.getFontRenderContext());

            // compute the amount to inset the time stamp and translate the
            // image to that position

            double inset = bounds.getHeight() / 2;
            g.translate(inset, event.getImage().getHeight() - inset);

            // draw a white background and black timestamp text

            g.setColor(Color.white);
            g.drawString(timeStampStr, 0, 0);

            // call parent which will pass the video onto next tool in chain

            super.onVideoPicture(event);
        }
    }

    /**
     * Create a tool which adjusts the volume of audio by some constant factor.
     */


}
