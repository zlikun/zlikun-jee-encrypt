package com.zlikun.crypto;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * DES加密算法实现测试
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/7 8:17
 */
public class DESTest {

    // 算法名称
    private final String KEY_ALGORITHM = "DES";
    // 算法名称/加密模式/填充方式
    // DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
    // private final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";
    private final String CIPHER_ALGORITHM = "DES/ECB/ISO10126Padding";

    // 实例化Cipher对象，它用于完成实际的加解密操作
    private Cipher cipher ;

    // 密钥字节信息(不直接使用SecretKey实例原因是为了模拟网络传输情形)
    private byte [] secretKeyBytes ;

    /**
     * 初始化SecretKey实例
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    @Before
    public void init() throws NoSuchAlgorithmException, NoSuchPaddingException {

        cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        // Java6 只支持56位密钥，该模式下，将随机生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM) ;
        keyGenerator.init(56);
        SecretKey secretKey = keyGenerator.generateKey() ;
        this.secretKeyBytes = secretKey.getEncoded() ;

        System.out.println(String.format("密钥：%s" ,Base64.encodeBase64String(this.secretKeyBytes)));
    }

    /**
     * 将字节密钥还原成SecretKey实例
     * @param key
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    SecretKey toKey(byte [] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        // 实例化DES密钥材料
        DESKeySpec dks = new DESKeySpec(key);
        // 实例化DES密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        // 生成密钥
        return keyFactory.generateSecret(dks);
    }

    @Test
    public void des() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, NoSuchProviderException {

        // 要加密的字符串
        final String message = "这是一个要加密的字符串。" ;

        // I 加密

        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey(this.secretKeyBytes), new SecureRandom());
        // 执行加密操作
        byte [] encryptBytes = cipher.doFinal(message.getBytes());
        // 加密后的结果通常都会用Base64编码进行传输
        System.out.println(String.format("加密后字符串(使用BASE64编码)：%s" ,Base64.encodeBase64String(encryptBytes)));


        // II 解密

        // 初始化Cipher对象，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey(this.secretKeyBytes), new SecureRandom());
        // 执行解密操作
        Assert.assertEquals(message ,new String(cipher.doFinal(encryptBytes))) ;

    }

}