package jp.co.teststorm.testimageio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Collator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {

	public static BufferedImage getImageFromBytes(byte[] bytes) throws IOException {
		
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		BufferedImage img = ImageIO.read(bais);
//		BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
//		ColorModel model = img.getColorModel();
//		System.out.println("model hasAlpha : " + model.hasAlpha());
		Graphics2D g = image.createGraphics();
		try{
			g.drawImage(img, 0, 0, Color.WHITE,null);
//			g.setBackground(Color.WHITE);
//			g.clearRect(0, 0, image.getWidth(), image.getHeight());
//			g.drawImage(img, 0, 0, image.getWidth(), image.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
		} finally {
			g.dispose();
		}
//		BufferedImage img_rtn = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		System.out.println(img.getType());
//		System.out.println(img_rtn.getType());
		return image;
	}
	
	public static byte[] getBytesFromImage(BufferedImage img, String format) throws IOException {
		if(StringUtils.isEmpty(format)) {
			format = "png";
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, format, baos);
			return baos.toByteArray();
	}
	
	public static byte[] changeImageFormat(byte[] bytes, String format) throws IOException {
		BufferedImage img = getImageFromBytes(bytes);
		return getBytesFromImage(img, format);
//		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		BufferedImage img = ImageIO.read(bais);
//		ImageIO.write(img, format, baos);
//		return baos.toByteArray();
	}
	
	public static void unzipAndStore(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ZipInputStream zis = new ZipInputStream(bais);
		byte[] buffer = new byte[1024];
		try{
			ZipEntry entry;
			while((entry = zis.getNextEntry()) != null) {
				if(!entry.isDirectory() && !entry.getName().contains("/")){
					System.out.println(entry.getName() + " start");
					FileOutputStream fos = new FileOutputStream(new File("unzip_" + entry.getName()));
					int size = 0;
					while(0 < (size = zis.read(buffer))){
						fos.write(buffer, 0, size);
					}	
				}
				zis.closeEntry();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
