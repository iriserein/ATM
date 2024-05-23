package atm_server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Thread_GetClient extends Thread {
    Socket s;
    User_Deal deal;

    public Thread_GetClient(Socket s, User_Deal deal) {
        this.s = s;
        this.deal = deal;
    }

    public void run() {
        DataInputStream dis;
        try {
            dis = new DataInputStream(s.getInputStream());
            while (true) {
                if (s != null) {
                    Thread.sleep(1);
                    String ClientCode = dis.readUTF();
                    switch (ClientCode) {
                        case "BALA": {
                            deal.getBalance(s, dis);
                            break;
                        }
                        case "WDRA":{
                            deal.getMoney(s, dis);
                            break;
                        }
                        case "BYE":{
                            s.close();
                            s = null;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
