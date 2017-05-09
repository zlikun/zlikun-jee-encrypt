package com.zlikun.crypto;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * 从JKS文件中提取私钥
 *
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/9 16:00
 */
public class KeyStoreTest {

    private String type = "JKS";
    private String alias = "api.zlikun.com";
    private char[] password = "123456".toCharArray();

    @Test
    public void export() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {

        KeyStore keystore = KeyStore.getInstance(type);
        keystore.load(KeyStoreTest.class.getClassLoader().getResourceAsStream("api.zlikun.com.keystore"), password);

        KeyPair keyPair = null;

        Key key = keystore.getKey(alias, password);
        if (key instanceof PrivateKey) {
            java.security.cert.Certificate cert = keystore.getCertificate(alias);
            PublicKey pubKey = cert.getPublicKey();
            keyPair = new KeyPair(pubKey, (PrivateKey) key);
        }

        PrivateKey priKey = keyPair.getPrivate();
        String encoded = new BASE64Encoder().encode(priKey.getEncoded());

        StringBuilder sb = new StringBuilder();
        sb.append("-----BEGIN RSA PRIVATE KEY-----")
                .append("\n")
                .append(encoded)
                .append("\n")
                .append("-----END RSA PRIVATE KEY-----");

        System.out.println();
        System.out.println();
        System.out.println(sb.toString());
        System.out.println();
        System.out.println();
    }

}
