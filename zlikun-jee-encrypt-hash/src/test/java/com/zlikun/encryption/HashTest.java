package com.zlikun.encryption;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 哈希加密
 * @author	zlikun
 * @date	2016年5月14日 下午4:43:22
 */
public class HashTest {

	@Test
	public void testMD5() throws IOException {
		
		// 字符串 MD5
		Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", DigestUtils.md5Hex("123456"));

		// 文件(流) MD5
		URL url = new URL("http://zlikun.com/content/images/2016/01/zlikun-com-qrcode-2.png") ;
		Assert.assertEquals("dd96c860b617bcb152d4885970290124", DigestUtils.md5Hex(url.openStream()));
		
	}
	
	@Test
	public void testSHA1() throws IOException {

		Assert.assertEquals("7c4a8d09ca3762af61e59520943dc26494f8941b", DigestUtils.sha1Hex("123456"));
		
		URL url = new URL("http://zlikun.com/content/images/2016/01/zlikun-com-qrcode-2.png") ;
		Assert.assertEquals("fd1effbf59b2be40a94102adc301d949b07fefe6", DigestUtils.sha1Hex(url.openStream()));
	}

	@Test
	public void testSHAOther() {
		Assert.assertEquals("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"
				, DigestUtils.sha256Hex("123456"));
		Assert.assertEquals("0a989ebc4a77b56a6e2bb7b19d995d185ce44090c13e2984b7ecc6d446d4b61ea"
				+ "9991b76a4c2f04b1b4d244841449454", DigestUtils.sha384Hex("123456"));
		Assert.assertEquals("ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548b"
				+ "aeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413"
				, DigestUtils.sha512Hex("123456"));
	}
	
}
