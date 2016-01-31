package valentao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davy Souza
 * @author Douglas Santos
 */
public class Servidor implements Runnable {

    private Socket cliente = null;

    public Servidor() {
        server();
    }

    public Servidor(Socket cliente) {
        this.cliente = cliente;
    }

    public static void server() {

        try {
            ServerSocket servidor = new ServerSocket(Run.porta[Run.ID - 1]);
            while (true) {

                // Espera por conexao
                Socket cliente = servidor.accept();

                // Cria uma thread do servidor para tratar a conexao
                Servidor tratamento = new Servidor(cliente);
                Thread t = new Thread(tratamento);

                // Inicia a thread
                t.start();
            }
        } catch (IOException ex) {
            System.out.println("Oops...");
        }
    }

    @Override
    public void run() {
        try {
            try (ObjectInputStream inputStream = new ObjectInputStream(this.cliente.getInputStream())) {
                Pacote pacote = (Pacote) inputStream.readObject();
                if(pacote.sourceID != Run.ID) {
                    if (pacote.destinID == Run.liderID) {
                        Valentao.lider(pacote);
                    } else {
                        try {
                            Valentao.resto(pacote);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                // Finaliza objetos
            }
            this.cliente.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Oops...");
        }
    }
}
