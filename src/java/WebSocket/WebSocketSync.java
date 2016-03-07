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
import java.io.IOException;
import java.util.ArrayList;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/sync")
public class WebSocketSync {
    private static ArrayList<WebPart> sessionList = new ArrayList<>();
    
    public void addSession(WebPart webPart){
        synchronized(sessionList){
            sessionList.add(webPart);
        }
        
    }
    public void removeSession(Session session){
        synchronized(sessionList){
            for(WebPart webPart:sessionList){
                if(webPart.getSession().equals(session)){
                    sessionList.remove(webPart);
                    break;
                }
            }
                
            }
        }
    
    
    @OnOpen
        public void open(Session session) {
            System.out.println("session open");
    }

    @OnClose
        public void close(Session session) {
            removeSession(session);
    }

    @OnError
        public void onError(Throwable error) {
    }

    @OnMessage
        public void handleMessage(String message, Session session) throws IOException {
            String[] messageBreak = message.split(",");
            switch(messageBreak[0]){
                case "newSession":
                    WebPart webPart = new WebPart();
                    webPart.setWebpart(messageBreak[1]);
                    webPart.setSession(session);
                    addSession(webPart);
                    boolean page1=false;
                    boolean page2=false;
                    boolean page3=false;
                    for(WebPart webPart1:sessionList){
                        switch(webPart1.getWebpart()){
                            
                            case "page1":
                                page1=true;
                                break;
                            case "page2":
                                page2=true;
                                break;
                            case "page3":
                                page3=true;
                                break;
                        }
                    }
                    if(page1&&page2&&page3){
                        for(WebPart webPart1:sessionList){
                            Session session1 = webPart1.getSession();
                            session1.getOpenSessions();
                            session1.getBasicRemote().sendText("alert");
                        }
                    }
                    break;
                
            }
    }

}
