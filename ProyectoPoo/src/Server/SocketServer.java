package Server;

import java.io.*;
import java.net.*;

public class SocketServer extends Thread {
    
    public static void main(String[] args) {
        ServerSocket sfd = null;
        try 
        {
            sfd = new ServerSocket(7000);
            System.out.println("=======================================");
            System.out.println(" Respaldo Iniciado ");
            System.out.println(" Enviando al Servidor pricipal ");
        } catch (IOException ioe) {
            System.out.println("Error al conectar con el servidor: " + ioe);
            System.exit(1);
        }

        while (true) 
        {
            try 
            {
                Socket nsfd = sfd.accept();
                System.out.println("Conexion entrante aceptada desde: " + nsfd.getInetAddress());
                DataInputStream ois = new DataInputStream(nsfd.getInputStream());
                File archivoRespaldo = new File("SaveNube.dat");
                FileOutputStream fos = new FileOutputStream(archivoRespaldo);
                DataOutputStream escritor = new DataOutputStream(fos);

                int unByte;
                while ((unByte = ois.read()) != -1) 
                {
                    escritor.write(unByte);
                }
                ois.close();
                escritor.close();
                nsfd.close();

                System.out.println("¡Archivo recibido!\n ¡Guardado correctamente!\n");

            } catch (IOException e) 
            {
                System.out.println("Error al procesar el archivo: " + e.getMessage());
            }}}}