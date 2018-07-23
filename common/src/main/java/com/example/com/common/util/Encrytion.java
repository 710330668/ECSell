package com.example.com.common.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class Encrytion {
	private static final String KEY_MD5 = "MD5";

	public static byte[] encryptToMD5(String data) {

		byte[] digestdata = null;
		try {
			MessageDigest alga = MessageDigest.getInstance(KEY_MD5);
			alga.update(data.getBytes());
			digestdata = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return digestdata;
	}

	/**
	 * MD5加密
	 * @return
	 */
	public static String MD5(String pwd) {
		StringBuffer signatureData = new StringBuffer(pwd);
		byte[] byteMD5 = encryptToMD5(signatureData.toString());

		return toHexString(byteMD5);
	}
	
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String toHexString(byte[] b) { // String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}
	
	/***
	 * MD5加密-生成32位
	 * */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}
	
	
	
	/**
	 * MD5加密-与.net同步
	 * @param s
	 * @return
	 */
	public static String toMD5Code(String s) {
		byte[] bytes=toUnicodeMC(s);
	     StringBuffer sb = new StringBuffer();
	     try {
	         MessageDigest md5 = MessageDigest.getInstance("MD5");
	         md5.reset();
	         md5.update(bytes);
	         byte[] after = md5.digest();

	         for (int i = 0; i < after.length; i++) {
	             String hex = Integer.toHexString(0xff & after[i]);
	             if (hex.length() == 1)
	                 hex = "0" + hex;
	                 sb.append(hex);
	             }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	      return sb.toString();
	 }
	private static byte[] toUnicodeMC(String s) {
	     byte[] bytes = new byte[s.length() * 2];
	     for (int i = 0; i < s.length(); i++) {
	         int code = s.charAt(i) & 0xffff;
	         bytes[i * 2] = (byte) (code & 0x00ff);
	         bytes[i * 2 + 1] = (byte) (code >> 8);
	     }
	     return bytes;
	 }
	
	
	
	
		/** 
	     * 将byte[]数组转化为8、10、16等各种进制
	     * @param bytes byte[]
	     * @param radix-代表各种进制
	     * @return String
	     */  
	    public static String binary(byte[] bytes, int radix){
	        return new BigInteger(1, bytes).toString(radix);// �����1�������
	    }  
	      
	    /** 
	     * AES转换为二进制数组
	     * @param content 内容
	     * @param encryptKey 秘钥
	     * @return byte[]
	     * @throws Exception
	     */  
	    public static byte[] encryptToBytes(String content, String encryptKey) throws Exception {
	        KeyGenerator kgen = KeyGenerator.getInstance("AES");
	        kgen.init(128, new SecureRandom(encryptKey.getBytes()));
	  
	        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
	        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
	          
	        return cipher.doFinal(content.getBytes("utf-8"));  
	    }  
	      
	    /** 
	     * AES-通过秘钥进行Base64编码加密
	     * @param content 内容
	     * @param encryptKey 秘钥 
	     * @return String
	     * @throws Exception
	     */  
	    public static String aesEncrypt(String content, String encryptKey) throws Exception {
	        return Base64.encodeToString(encryptToBytes(content, encryptKey));
	    }  
	      
	    /** 
	     * AES-解密byte[]成String
	     * @param encryptBytes byte[]内容
	     * @param decryptKey 秘钥
	     * @return String 
	     * @throws Exception
	     */  
	    public static String decryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
	        KeyGenerator kgen = KeyGenerator.getInstance("AES");
	        kgen.init(128, new SecureRandom(decryptKey.getBytes()));
	          
	        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
	        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
	        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
	          
	        return new String(decryptBytes);
	    }  
	      
	    /** 
	     * AES-解密String成String
	     * @param encryptStr 需解密的内容 
	     * @param decryptKey 秘钥
	     * @return string 
	     * @throws Exception
	     */  
	    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
	        return (encryptStr==null || "".equals(encryptStr)) ? null : decryptByBytes(Base64.decode(encryptStr), decryptKey);  
	    }
}
