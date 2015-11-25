package ir.chbox.utils.video;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

public class MediaConvertor {
	private static final Integer WIDTH = 320;
	private static final Integer HEIGHT = 240;
 
	private static final String INPUT_FILE = "/home/farzad/dr/s4.mp4";
	private static final String OUTPUT_FILE = "/home/farzad/dr/out.mp4";
 
	public static void main(String[] args) {
		// create custom listeners
		MyVideoListener myVideoListener = new MyVideoListener(WIDTH, HEIGHT);
		Resizer resizer = new Resizer(WIDTH, HEIGHT);

		// reader
		IMediaReader reader = ToolFactory.makeReader(INPUT_FILE);
		reader.addListener(resizer);
 
		// writer
		IMediaWriter writer = ToolFactory.makeWriter(OUTPUT_FILE, reader);
		resizer.addListener(writer);
		writer.addListener(myVideoListener);
 
		// show video when encoding
		reader.addListener(ToolFactory.makeViewer(true));
 
		while (reader.readPacket() == null) { 
			// continue coding
		}
	}
}
