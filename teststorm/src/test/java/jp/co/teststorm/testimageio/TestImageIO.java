package jp.co.teststorm.testimageio;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestImageIO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath("image.png");
		try {
			byte[] bytes = Files.readAllBytes(p);
			byte[] formatedBytes = CommonUtils.changeImageFormat(bytes, "jpg");
			Files.write(fs.getPath("image.jpg"), formatedBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
