package ir.chbox.utils.video;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;

import java.io.*;
import java.util.Date;

public class MediaConvertor {
    private static final Integer WIDTH = 320;
    private static final Integer HEIGHT = 240;

    private static final String INPUT_FILE = "/home/farzad/dr/s4.mp4";
    private static final String OUTPUT_FILE = "/home/farzad/dr/out.mp4";
    private static IContainer mIContainer = null;
    /**
     * A container we'll use to write data from.
     */
    private static IContainer mOContainer = null;

    public static void main(String[] args) {
        try {


        // create custom listeners
        MyVideoListener myVideoListener = new MyVideoListener(WIDTH, HEIGHT);
        Resizer resizer = new Resizer(WIDTH, HEIGHT);
        mIContainer = IContainer.make();
        mOContainer = IContainer.make();
        IContainerFormat iFmt = null;
        IContainerFormat oFmt = null;


        // override input format
        iFmt = IContainerFormat.make();

        /**
         * Try to find an output format based on what the user specified, or
         * failing that, based on the outputURL (e.g. if it ends in .flv, we'll
         * guess FLV).
         */
        byte[] data = null;
        mIContainer.open(new DataInputStream(new ByteArrayInputStream(data)), iFmt);
        oFmt = IContainerFormat.make();
        File file = File.createTempFile(new Date().toString(), ".mp4");
        file.deleteOnExit();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        mOContainer.open(new DataOutputStream(fileOutputStream), oFmt);



        // reader
        IMediaReader reader = ToolFactory.makeReader(mIContainer);
        reader.addListener(resizer);

        // writer
        IMediaWriter writer = ToolFactory.makeWriter(file.getAbsolutePath(), reader);
        resizer.addListener(writer);
        writer.addListener(myVideoListener);

        // show video when encoding
//        reader.addListener(ToolFactory.makeViewer(true));

        while (reader.readPacket() == null) {
            // continue coding
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
