package valentao;

/**
 *
 * @author Davy Souza
 * @author Douglas Santos
 */
public class InstanciaCliente implements Runnable {

    @Override
    public void run() {
        Cliente c = new Cliente();
        c.run();
    }
    
}
