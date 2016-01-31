package valentao;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Davy Souza
 * @author Douglas Santos
 */
public class Cliente {

	public void run(){
		try {
			System.out.println("I'm here. Election!!!");
			Valentao.eleicao(Run.ID);
		} catch(InterruptedException ex) {
			System.out.println("Oops... something went wrong. :v");
		}
	}

    public static void sendToLider(Pacote pkt2send) {
        Socket socket;
        ObjectOutputStream outputStream;

        try {
            socket = new Socket("localhost", Run.porta[Run.liderID - 1]);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(pkt2send);
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (IOException ex) {
            try {
				System.out.println("The lider has fallen!!! Election!!!");
	            Valentao.eleicao(pkt2send.sourceID);
	        } catch (InterruptedException e) {
	        	System.out.println("Oops... something went wrong. :p");
	        }
        }
    }

    public static void sendToRest(Pacote responsePkt) {
        Socket socket;
        ObjectOutputStream outputStream;

        try {
            socket = new Socket("localhost", Run.porta[responsePkt.destinID - 1]);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(responsePkt);
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("The process " + responsePkt.destinID + " has fallen.");
        }
    }

}
