package valentao;

import java.io.Serializable;

/**
 *
 * @author Davy Souza
 * @author Douglas Santos
 */
public class Pacote implements Serializable {

    private static final long serialVersionUID = 5950169519310163575L;

    public int sourceID;
    public int destinID;
    public String message;

    public Pacote(int sourceID, int destinID, String message){
        this.sourceID = sourceID;
        this.destinID = destinID;
        this.message = message;
    }
    
}
