package com.cafromet.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Encriptacion {

	public Encriptacion() {
	}

	public SecretKey generarClave(String claveUsuario) {

		SecretKeyFactory skf;
		DESKeySpec clavEspec;
		SecretKey claveSecreta = null;
		try {
			skf = SecretKeyFactory.getInstance("DES");
			clavEspec = new DESKeySpec(claveUsuario.getBytes());
			claveSecreta = skf.generateSecret(clavEspec);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return claveSecreta;
	}

	public boolean comprobarClaves() {
		return true;
	}

	public boolean encriptar(String mensaje, String claveUsuario, String ruta) {
		Cipher cipher;

		File fic = new File(ruta + File.separator + "mensaje");
		File fic2 = new File(ruta + File.separator + "iv");
		try {
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, generarClave(claveUsuario));
			byte[] mensajeCodificado = cipher.doFinal(mensaje.getBytes());
			byte[] iv = cipher.getIV();
			escribirFichero(fic, mensajeCodificado);
			escribirFichero(fic2, iv);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public byte[] desencriptar(String ruta, String claveUsuario) {
		byte[] mensajeCodificado;
		byte[] iv;
		byte[] mensajeDecodificado = null;
		try {
			File fic = new File(ruta + File.separator + "mensaje");
			File fic2 = new File(ruta + File.separator + "iv");

			mensajeCodificado = leerFichero(fic);
			iv = leerFichero(fic2);
			mensajeDecodificado = null;
			Cipher cipher;
			try {
				cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
				IvParameterSpec dps = new IvParameterSpec(iv);
				cipher.init(Cipher.DECRYPT_MODE, generarClave(claveUsuario), dps);
				mensajeDecodificado = cipher.doFinal(mensajeCodificado);

			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return mensajeDecodificado;

	}

	public boolean escribirFichero(File fichero, byte[] mensajeCodificado) {

		int numBytes = 0;
		boolean cargado = true;
		try {

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));

			oos.writeObject(mensajeCodificado);
			oos.flush();
			oos.close();

			System.out.println(
					"\nSe han guardado " + mensajeCodificado.length + " bytes en el fichero " + fichero.getName());

		} catch (FileNotFoundException fn) {
			System.out.println("\nNo se encuentra el fichero");
			cargado = false;
		} catch (IOException io) {
			System.out.println("\nError de E/S ");
		}
		return cargado;
	}

	public byte[] leerFichero(File fichero) {

		byte[] bytes = null;

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));

			bytes = (byte[]) ois.readObject();

			ois.close();

			if (bytes.length == 0) {
				System.out.println("\nNo se ha cargado ningun byte ");
			} else {
				System.out.println("\nSe han cargado en memoria " + bytes.length + " bytes");
			}
		} catch (FileNotFoundException fn) {
			System.out.println("\nNo se encuentra el fichero de carga");
		} catch (IOException io) {
			System.out.println("\nError de E/S ");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

	public static String generateHash(String data, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.reset();

			byte[] hash = md.digest(data.getBytes());

			return bytesToStringHex(hash);

		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Devuelve un hash a partir de un tipo y un texto
	 * 
	 * @param txt
	 * @param hashType
	 */
//	public static String generateHash(String data, String algorithm, byte[] salt) {
//		try {
//			MessageDigest md = MessageDigest.getInstance(algorithm);
//			md.reset();
//			md.update(salt);
//
//			byte[] hash = md.digest(data.getBytes());
//
//			return bytesToStringHex(hash);
//
//		} catch (NoSuchAlgorithmException e) {
//			System.out.println(e.getMessage());
//		}
//		return null;
//	}

	public static String bytesToStringHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();
	}

	public static byte[] createSalt() {
		byte[] bytes = new byte[20];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}
}
