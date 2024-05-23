package atm_server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Thread_Server extends Thread {
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(2525);
            while (true) {
                Thread.sleep(1);
                Socket s = new Socket();
                System.out.println("<<等待用户的登陆请求..");
                s = ss.accept();
                DataInputStream dis;
                dis = new DataInputStream(s.getInputStream());
                int ClientCode = dis.readInt();
                if(ClientCode != 6){
                    continue;
                }else{
                    User_Deal deal = new User_Deal();
                    if(deal.checkUserLogin(s, dis)){
                        if (s != null) {
                            new Thread_GetClient(s, deal).start();
                        }
                    }else{
                        continue;
                    }
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
