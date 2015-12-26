package ir.chbox.utils.video;


public class Resizer /*extends MediaToolAdapter */{
	private Integer width;
	private Integer height;
 
//	private IVideoResampler videoResampler = null;
 
	public Resizer(Integer aWidth, Integer aHeight) {
		this.width = aWidth;
		this.height = aHeight;
	}
 
//	@Override
//	public void onVideoPicture(IVideoPictureEvent event) {
//		IVideoPicture pic = event.getPicture();
//		if (videoResampler == null) {
//			videoResampler = IVideoResampler.make(width, height, pic.getPixelType(), pic.getWidth(), pic
//					.getHeight(), pic.getPixelType());
//		}
//		IVideoPicture out = IVideoPicture.make(pic.getPixelType(), width, height);
//		videoResampler.resample(out, pic);
//
//		IVideoPictureEvent asc = new VideoPictureEvent(event.getSource(), out, event.getStreamIndex());
//		super.onVideoPicture(asc);
//		out.delete();
//	}
}