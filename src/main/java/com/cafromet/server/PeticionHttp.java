package com.cafromet.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class PeticionHttp {

	
	public PeticionHttp(String url, String nombreFichero){
		try {
	
			if (handshakeHttps()) {
				crearJson(url, nombreFichero);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean handshakeHttps() throws NoSuchAlgorithmException, KeyManagementException {
		// Create a trust manager that does not validate certificate chains
		boolean correcto = false;
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		correcto = true;
		return correcto;
	}

	public void crearJson(String urlJson, String NomFichero) {

		try {
			URL url = new URL(urlJson);
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String lineaActual;
			
			FileWriter fichero = new FileWriter(NomFichero);
			
			PrintWriter pw = new PrintWriter(fichero);
			
			while ((lineaActual = br.readLine()) != null) {
				pw.println(lineaActual);
			}
			fichero.close();
			System.out.println("\n FICHERO TEMPORAL CREADO => " + NomFichero);
		} catch (Exception e) {
			System.out.println("\n !ERROR -> NO SE ENCUENTRA LA URL");;
		}
		
	}
}
