package logico;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RespaldoHilado extends Thread {

    @Override
    public void run() {
        try {
            Socket sfd = new Socket("127.0.0.1", 7000);
            File archivoLocal = new File("BolsaData.dat");
            BolsaLaboral.getInstancia().guardarMemoria();

            if (archivoLocal.exists()) 
            {
                DataInputStream aux = new DataInputStream(new FileInputStream(archivoLocal));
                DataOutputStream salidaSocket = new DataOutputStream(sfd.getOutputStream());

                int unByte; 
                while ((unByte = aux.read()) != -1) 
                {
                    salidaSocket.write(unByte);
                }
                salidaSocket.flush();
                aux.close();
                salidaSocket.close();
                sfd.close();

                SwingUtilities.invokeLater(new Runnable() 
                {
                    public void run() 
                    {
                        JOptionPane.showMessageDialog(null, 
                            "¡Respaldo enviado exitosamente al Servidor en la Nube!", 
                            "Respaldo Completado", JOptionPane.INFORMATION_MESSAGE);
                    }
                });}

        } catch (ConnectException ce) 
        {

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, 
                        "El Servidor esta apagado o inaccesible.", 
                        "Servidor Desconectado", JOptionPane.WARNING_MESSAGE);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}