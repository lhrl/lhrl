package com.lhrl.common.util;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Mei Xianzhi 2015-1-26 16:40:13
 */
public class RSA {

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    
    
    public static void  main(String[] args) throws Exception {
        String businessResult = "02wWfQkZk/WzKfsXwlFBh5Ndj64sexJw65kIy1dF/9RZIE5i5Hy+lTuk16MBdCquqWbp3t64YiA/0IQvDN6lgFW3XoAHNqMRVek3tqpnBCKAL629YUOsFNcZBilqr5TSyyIRNmmDAEmjohPIiCcJKXSKZOm5nnYlUAATc/3qr3BUdJijjSztYOPgRd001b7l3wlPzg62qz9SFP+roUnN04YGqj2ygabpc/FW8d9d0yGmavsxqqaqqX3uSPgibNltaFv6Aedycx8AGOAqou4OcsNId9GR3Z89OnogCcZrBOMs/BCRqifIuIywYKvhRUeQl2zfqUglzIR/5lTSg1Uaow==";
        String PrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAO/6XwLgdEbaFoR9yGvc+5FpvaklEUrmvHQzEyRQ6iNrV6mkywrZj5wAh6kbDopa5ow8GZdr6bvq3JlDBIJsS4htqds6VT/LWKDVQ5pvANx1w8U1otTdQLA7DtNRBadejb7IWOmrBTy1A4zOxRBAyJJVW/UQ1uC8EaGYuYT4lY1DAgMBAAECgYBNAYSXgbsOOVFcdphntGOth8bqjhB8fooep30O/EAF2Z4pu83Dz4h7wNfHdf02fHDaszZrFp/MQ1NCgvThfSrHH4azgWxNEgdTF0DPsGd9YEx3zWmxNQINL++XrSALQNXWlJGAuvPu9e6c6AGnM4f3/cPy3/+Ujzgb8ZcRFoFPwQJBAPjSwSnSMvWjR+/hFqdc0LjGwDHNbzQnA05v2QBNhCG18J4btl4r1Qm6iE49IHRe0usK5Iokr+H3sb4z0tyRXdECQQD25k48oSkMSaaHaOgFyEBUG2DG8zB0pLCTjZ+qcDbmD1iXplXwcoseZiyRflRh6J8WOcglSA8fblr0wU+u3hrTAkEAtDx1j0WjhCryQOBa+IBS8IWHUovssWpRCtAZ8A42V/TihqSsRSqa9qEXexTsHV9SiM8rHKXWgWhaZ9xCMWNbAQJAUOotXcVbVIGWp+4DjFGSc339e3YbvT5z+AeD+jGIbGE/IYQLyh9f5HiLxqrmMKgx6vDI3R7+hch+2pO375EurQJAXNgT3uRmuSNitPBzE7rfhar636s3YCQZGrPXSbsuWivab4nHOCg9YT9r3wT6N7/aZcfLRjzaI2vbFBrMwVXSCA==";
        String result = RSA.decrypt(businessResult, PrivateKey);
        System.out.println(result);
    }

    /**
     * RSA签名
     *
     * @param content 待签名数据
     * @param privateKey 商户私钥
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String input_charset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(input_charset));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content 待签名数据
     * @param sign 签名值
     * @param ali_public_key 支付宝公钥
     * @param input_charset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String ali_public_key, String input_charset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(ali_public_key);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(input_charset));

            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 解密
     *
     * @param content 密文
     * @param private_key 商户私钥
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String private_key) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);
        // 指定特定Provider，防止非线程安全导致的乱码
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), "UTF-8");
    }

    /**
     * 解密
     *
     * @param content 密文
     * @param public_key
     * @return 解密后的字符串
     */
    public static String encrypt(String content, String public_key) throws Exception {
        PublicKey publicKey = getPublicKey(public_key);
        // 指定特定Provider，防止非线程安全导致的乱码
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        InputStream ins = new ByteArrayInputStream(content.getBytes());
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;
        byte[] block;
        while ((bufl = ins.read(buf)) != -1) {

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                System.arraycopy(buf, 0, block, 0, bufl);
            }

            writer.write(cipher.doFinal(block));
        }
        return Base64.encode(writer.toByteArray());
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64.decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decode(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
}
