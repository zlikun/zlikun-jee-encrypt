package com.zlikun.encrypt;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * RSA非对称加密算法
 * @author	zhanglikun
 * @date	2015年11月21日
 */
public class RSATest {
	
    private final static String KEY_RSA = "RSA";
    private final static String SIGNATURE_ALGORITHM = "MD5withRSA" ;

	@Test
	public void test() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, SignatureException {
		
		// 数据传输方，假设是甲方
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);
        generator.initialize(1024);
        KeyPair keyPair = generator.generateKeyPair();
        // 获取公钥
        PublicKey pubKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey priKey = keyPair.getPrivate();
        
        // 生成公私钥文本(BASE64编码)，用于密钥传递
        String pubKeyString = Base64.encodeBase64String(pubKey.getEncoded()) ;
        String priKeyString = Base64.encodeBase64String(priKey.getEncoded()) ;
        
        // 打印公钥、私钥
        System.out.println("公钥(BASE64)：" + pubKeyString);
        System.out.println("私钥(BASE64)：" + priKeyString);
        
        String msg = "你好，世界！";
        System.out.println("加密前：" + msg);
        
        // 公钥加密
        byte[] encryptMsg = encrypt(msg ,pubKeyString);
        System.out.println("加密后(BASE64)：" + Base64.encodeBase64String(encryptMsg));
        
        // 私钥解密
        String decryptMsg = decrypt(encryptMsg ,priKeyString) ;
        System.out.println("解密后: " + decryptMsg);
        
        // 同样可以私钥加密，公钥解密，示例略
        
        // 下例私钥生成签名、公钥验证签名，也可以反过来
        // 数字签名(密文 + 私钥生成签名)
        String signString = sign(encryptMsg, priKeyString) ;
        System.out.println("生成的数字签名：" + signString);
        // 密文 + 公钥验证签名
        Assert.assertTrue(verify(encryptMsg, pubKeyString, signString)) ;
		
	}
	
	/**
	 * 使用公钥加密(公钥由BASE64编码，需要解码)
	 * @param msg
	 * @param pubKeyString
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private byte [] encrypt(String msg ,String pubKeyString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
		KeyFactory factory = KeyFactory.getInstance(KEY_RSA) ;
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		// 根据公钥字符串获取公钥对象
		byte [] pubKey = Base64.decodeBase64(pubKeyString) ;
		// 加密使用X509EncodedKeySpec类
		X509EncodedKeySpec spec = new X509EncodedKeySpec(pubKey) ;
		// 执行加密操作
        cipher.init(Cipher.ENCRYPT_MODE, factory.generatePublic(spec));
        return cipher.doFinal(msg.getBytes());
	}
	
	/**
	 * 使用私钥解密(公钥由BASE64编码，需要解码)
	 * @param data
	 * @param priKeyString
	 * @return
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 */
	private String decrypt(byte [] data ,String priKeyString) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
		KeyFactory factory = KeyFactory.getInstance(KEY_RSA) ;
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		// 根据私钥字符串获取私钥对象
		byte [] priKey = Base64.decodeBase64(priKeyString) ;
		// 解密使用PKCS8EncodedKeySpec类
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(priKey) ;
		// 执行解密操作
		cipher.init(Cipher.DECRYPT_MODE, factory.generatePrivate(spec));
		return new String(cipher.doFinal(data)) ;
	}
	
	/**
	 * 使用私钥对信息生成数字签名
	 * @param data
	 * @param priKeyString
	 * @return
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 */
	private String sign(byte [] data ,String priKeyString) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		KeyFactory factory = KeyFactory.getInstance(KEY_RSA) ;
		// 根据私钥字符串获取私钥对象
		byte [] priKey = Base64.decodeBase64(priKeyString) ;
		// 解密使用PKCS8EncodedKeySpec类
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(priKey) ;
		PrivateKey privateKey = factory.generatePrivate(spec) ;
		// 用私钥信息生成数字签名
		Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM) ;
		sign.initSign(privateKey);
		sign.update(data);
		return Base64.encodeBase64String(sign.sign()) ;
	}
	
	/**
	 * 使用公钥校验数字签名
	 * @param data
	 * @param pubKeyString
	 * @param originSign
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @throws InvalidKeyException 
	 * @throws SignatureException 
	 */
	private boolean verify(byte [] data ,String pubKeyString ,String originSign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		KeyFactory factory = KeyFactory.getInstance(KEY_RSA) ;
		X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decodeBase64(pubKeyString));
		PublicKey publicKey = factory.generatePublic(spec) ;
		Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM) ;
		sign.initVerify(publicKey);
		sign.update(data);
		return sign.verify(Base64.decodeBase64(originSign)) ;
	}
	
}
