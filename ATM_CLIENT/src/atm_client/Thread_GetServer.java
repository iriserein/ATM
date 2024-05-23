package atm_client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Thread_GetServer extends Thread {
    Socket s;

    public Thread_GetServer(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            DataInputStream dis = new DataInputStream(s
                    .getInputStream());
            while (!s.isClosed()) {
                int c = dis.readInt();
                Var.SERVER_CODE = c;
                switch(c){
                    case 61:{
                        Var.ATM_FRAME.removeAll();
                        Var.ATM_FRAME.add((new Frame_Index()).pal_Index);
                        Var.ATM_FRAME.setTitle("正在为<"
                                +Var.CURRENT_ACCOUNT + ">服务中...");
                        break;
                    }
                    case 60:{
                        Var.ATM_FRAME.removeAll();
                        Frame_Login af = new Frame_Login();
                        af.lbl_Message.setText("输入的帐号密码有误！");
                        Var.ATM_FRAME.add(af.pal_Login);
                        break;
                    }
                    case 11:{
                        Double remainCash = dis.readDouble();
                        Var.ATM_FRAME.removeAll();
                        Frame_Message smf = new Frame_Message();
                        smf.lbl_Message.setText("余额为:" + remainCash.toString() + " 元");
                        Var.ATM_FRAME.add(smf.pal_Message);
                        break;
                    }


                    case 311:{
                        Var.ATM_FRAME.removeAll();
                        Frame_Message smf = new Frame_Message();
                        smf.lbl_Message.setText("请提取现金！<_>");
                        Var.ATM_FRAME.add(smf.pal_Message);
                        break;
                    }
                    case 310:{
                        Var.ATM_FRAME.removeAll();
                        Frame_Message smf = new Frame_Message();
                        smf.lbl_Message.setText("您的金额不足！");
                        Var.ATM_FRAME.add(smf.pal_Message);
                        break;
                    }

                    case 410:{
                        Var.ATM_FRAME.removeAll();
                        Frame_Message smf = new Frame_Message();
                        smf.lbl_Message.setText("帐号错误或余额不足！");
                        Var.ATM_FRAME.add(smf.pal_Message);
                        break;
                    }

                }
            }
        } catch (Exception e) {

        }

    }

}
