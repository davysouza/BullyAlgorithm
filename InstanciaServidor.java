package valentao;

/**
 *
 * @author Davy Souza
 * @author Douglas Santos
 */
public class InstanciaServidor implements Runnable {

    @Override
    public void run() {
        Servidor s = new Servidor();
    }
    
}
