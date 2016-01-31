package valentao;

/**
 *
 * @author Davy Souza
 * @author Douglas Santos
 */
public class Valentao {

    public static int contMSG = 1;
    public static boolean lider = false;

    public static void lider(Pacote pacote) {
    	if ("Eleicao".equals(pacote.message)) {
            if (pacote.destinID > pacote.sourceID) {
                System.out.println("Number " + pacote.sourceID + ", you are weak. I'm the boss!!");
                Pacote pkt2send = new Pacote(pacote.destinID, pacote.sourceID, "Fraco");
                Cliente.sendToRest(pkt2send);
                try {
	                eleicao(pacote.destinID);
	            } catch (InterruptedException ex) {
	            	System.out.println("Oops.... ");
	            }
            } else {
            	try {
                    Valentao.lider = false;
            		Thread.sleep(1500);
            	} catch (InterruptedException ex) {
	            	System.out.println("Oops.. ");
            	}
            }
        } else if ("Lider".equals(pacote.message)) {
        	Run.liderID = pacote.sourceID;
        	Pacote pkt2send = new Pacote(Run.ID, Run.liderID, "Message " + contMSG++);
            Cliente.sendToLider(pkt2send);
        } else {
            System.out.println("=====================");
		    System.out.println(pacote.message);
		    System.out.println("Sent by: " + pacote.sourceID);
            System.out.println("=====================\n");

		    Pacote response = new Pacote(Run.liderID, pacote.sourceID, "ACK:\n" + pacote.message);
		    Cliente.sendToRest(response);
        }
    }

    public static void resto(Pacote pacote) throws InterruptedException {
        if ("Eleicao".equals(pacote.message)) {
            if (pacote.destinID > pacote.sourceID) {
                System.out.println("Number " + pacote.sourceID + ", you are weak. I'm the boss!!");
                Pacote pkt2send = new Pacote(pacote.destinID, pacote.sourceID, "Fraco");
                Cliente.sendToRest(pkt2send);
                eleicao(pacote.destinID);
            }
        } else if ("Fraco".equals(pacote.message)) {
            System.out.println("OK number " + pacote.sourceID + ". Don't bully me, you are the boss.");
            Valentao.lider = false;
            Thread.sleep(3000);
        } else if ("Lider".equals(pacote.message)) {
        	Run.liderID = pacote.sourceID;
        } else {
            switch (pacote.destinID) {
                case 1:
                    Thread.sleep(2001);
                    break;
                case 2:
                    Thread.sleep(2555);
                    break;
                case 3:
                    Thread.sleep(3507);
                    break;
                case 4:
                    Thread.sleep(1602);
                    break;
            }
            System.out.println("\n=====================");
		    System.out.println(pacote.message + " from the leader " + pacote.sourceID);
            System.out.println("=====================");

            Pacote pkt2send = new Pacote(pacote.destinID, Run.liderID, "Message " + contMSG++);
            Cliente.sendToLider(pkt2send);
        }
    }

    public static void eleicao(int sourceID) throws InterruptedException {
        Valentao.lider = true;
        for (int i = Run.ID + 1; i <= Run.numPortas; i++) {
            Pacote pkt2election = new Pacote(sourceID, i, "Eleicao");
            Cliente.sendToRest(pkt2election);

            Thread.sleep(300);
            if(!Valentao.lider) {
                Thread.sleep(1700);
                break;
            }
        }

        if (Valentao.lider) {
            Run.liderID = sourceID;
            System.out.println("\nI am the Boss!!!");
            for (int i = 1; i <= Run.numPortas; i++) {
            	if(Run.ID != i){
	                Pacote pkt2election = new Pacote(sourceID, i, "Lider");
    	            Cliente.sendToRest(pkt2election);
    	        }
            }
        } else {
        	Pacote pkt2send = new Pacote(Run.ID, Run.liderID, "Message " + contMSG++);
            Cliente.sendToLider(pkt2send);
        }
    }
}
