/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebSocket;

/**
 *
 * @author Matthew
 */
import javax.websocket.Session;

public class WebPart {
    private Session session;
    private String webpart;

    public WebPart() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getWebpart() {
        return webpart;
    }

    public void setWebpart(String webpart) {
        this.webpart = webpart;
    }
    
}
