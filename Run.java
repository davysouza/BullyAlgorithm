package valentao;
/**
 *
 * @author Davy Souza
 * @author Douglas Santos
 * @version 2.0
 * @since 30-09-2015
 */
public class Run {

    public static int ID;
    public static int liderID;
    public static int numPortas = 4;
    public static int[] porta = {11111, 22222, 33333, 44444};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Escolhe a porta
        if (args[0] != null) {
            ID = Integer.parseInt(args[0]);
        }

        // Instancia a parte cliente em uma thread
        InstanciaCliente cliente = new InstanciaCliente();
        Thread thread1 = new Thread(cliente);
        thread1.start();

        // Instancia a parte servidor em outra thread
        InstanciaServidor servidor = new InstanciaServidor();
        Thread thread2 = new Thread(servidor);
        thread2.start();
    }
}
