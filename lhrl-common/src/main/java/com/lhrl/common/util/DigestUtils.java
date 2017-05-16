package com.lhrl.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Mei Xianzhi 2014-12-25 0:11:31
 */
public final class DigestUtils {

    private static final String MD5_ALGORITHM_NAME = "MD5";
    private static final String SHA1_ALGORITHM_NAME = "SHA-1";
    private static final String SHA256_ALGORITHM_NAME = "SHA-256";

    private static final String CHARSET_DEFAULT = "UTF-8";

    private static final char[] HEX_CHARS
            = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * Calculate the MD5 digest of the given bytes.
     *
     * @param bytes the bytes to calculate the digest over
     * @return the digest
     */
    public static byte[] md5Digest(byte[] bytes) {
        return digest(MD5_ALGORITHM_NAME, bytes);
    }

    /**
     * Return a hexadecimal string representation of the MD5 digest of the given
     * bytes.
     *
     * @param bytes the bytes to calculate the digest over
     * @return a hexadecimal digest string
     */
    public static String md5DigestAsHex(byte[] bytes) {
        return digestAsHexString(MD5_ALGORITHM_NAME, bytes);
    }

    /**
     * Append a hexadecimal string representation of the MD5 digest of the given
     * bytes to the given {@link StringBuilder}.
     *
     * @param bytes the bytes to calculate the digest over
     * @param builder the string builder to append the digest to
     * @return the given string builder
     */
    public static StringBuilder appendMd5DigestAsHex(byte[] bytes, StringBuilder builder) {
        return appendDigestAsHex(MD5_ALGORITHM_NAME, bytes, builder);
    }

    public static byte[] sha1Digest(byte[] bytes) {
        return digest(SHA1_ALGORITHM_NAME, bytes);
    }

    public static String sha1DigestAsHex(byte[] bytes) {
        return digestAsHexString(SHA1_ALGORITHM_NAME, bytes);
    }

    public static byte[] sha256Digest(byte[] bytes) {
        return digest(SHA256_ALGORITHM_NAME, bytes);
    }

    public static String sha256DigestAsHex(byte[] bytes) {
        return digestAsHexString(SHA256_ALGORITHM_NAME, bytes);
    }

    /**
     * Creates a new {@link MessageDigest} with the given algorithm. Necessary
     * because {@code MessageDigest} is not thread-safe.
     */
    private static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + algorithm + "\"", ex);
        }
    }

    private static byte[] digest(String algorithm, byte[] bytes) {
        return getDigest(algorithm).digest(bytes);
    }

    private static String digestAsHexString(String algorithm, byte[] bytes) {
        char[] hexDigest = digestAsHexChars(algorithm, bytes);
        return new String(hexDigest);
    }

    private static StringBuilder appendDigestAsHex(String algorithm, byte[] bytes, StringBuilder builder) {
        char[] hexDigest = digestAsHexChars(algorithm, bytes);
        return builder.append(hexDigest);
    }

    private static char[] digestAsHexChars(String algorithm, byte[] bytes) {
        byte[] digest = digest(algorithm, bytes);
        return encodeHex(digest);
    }

    private static char[] encodeHex(byte[] bytes) {
        final int l = bytes.length;
        final char chars[] = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            chars[j++] = HEX_CHARS[(bytes[i] >>> 0x4) & 0xf];
            chars[j++] = HEX_CHARS[bytes[i] & 0xf];
        }
        return chars;
    }

}
