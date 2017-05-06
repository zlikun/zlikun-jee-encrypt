package com.zlikun.encrypt;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * 将文件转换为BASE64编码，通常用于图片
 * @author	zlikun
 * @date	2016年5月14日 下午4:16:22
 */
public class FileBase64Test {

	@Test
	public void encode() throws IOException {
		URL url = FileBase64Test.class.getClassLoader().getResource("logo.gif") ;
		byte [] buf = readImage(url.openStream()) ;
		// 将文件字节数组进行Base64编码
		String encryptMsg = Base64.encodeBase64String(buf) ;
		System.err.println(encryptMsg);
	}
	
	/**
	 * 读取图片文件，返回字节数组
	 * @param input
	 * @return
	 */
	private byte[] readImage(InputStream input) {
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		try {
			BufferedImage bi = ImageIO.read(input) ;
			ImageIO.write(bi, "gif", imageStream) ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageStream.toByteArray();
	}
	
}
