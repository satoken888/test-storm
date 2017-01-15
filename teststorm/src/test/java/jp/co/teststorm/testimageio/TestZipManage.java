package jp.co.teststorm.testimageio;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestZipManage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath("image.zip");
		try {
			byte[] bytes = Files.readAllBytes(p);
			CommonUtils.unzipAndStore(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
