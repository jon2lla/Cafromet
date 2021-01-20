package com.cafromet.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cafromet.server.Datos;

public class IOListenerClt implements Runnable{

    private Socket s;

    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private Datos datos;
//    private String ip = "192.168.0.20";
//    private String ip = "192.168.43.49"; 
//    private String ip = "192.168.0.13";
    private String ip = "192.168.6.241";
//      private String ip = "192.168.0.90";
    public IOListenerClt(Datos datos){
        this.datos = datos;
    }

    public Datos getDatos(){
        return datos;
    }

    @Override
    public void run() {
        try {
            s = new Socket(ip, 44444);

            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

            oos.writeObject(datos);
            oos.flush();
            datos = (Datos) ois.readObject();

            if(oos != null)
                oos.close();
            if(ois != null)
                ois.close();
            if(s != null)
                s.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }catch (ConnectException e) {
        	System.out.println("\n !ERROR => CONNECTEXCEPTION || CONEXION RECHAZADA");
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }
}