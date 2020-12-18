package com.cafromet.util;

public class Cifrado {
	 
	/**
	 *Devuelve un hash a partir de un tipo y un texto
	 *@param txt
	 *@param hashType
	 */
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /**
	 *Devuelve un texto encriptado utilizando el algoritmo MD5
	 *@param txt
	 */
    public static String md5(String txt) {
        return Cifrado.getHash(txt, "MD5");
    }
 
    /**
	 *Devuelve un texto encriptado utilizando el algoritmo SHA1
	 *@param txt
	 */
    public static String sha1(String txt) {
        return Cifrado.getHash(txt, "SHA1");
    }
 
}
