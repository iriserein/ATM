package atm_client;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;

public class Frame_Index extends Frame {

    Frame_Index frame = this;
    Button btn_CheckRemain = new Button("查询余额");
    Button btn_GetMoney = new Button("取款");
    Button btn_Exit = new Button("退出");

    Panel pal_Index = new Panel();

    public Frame_Index() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }

            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        this.setTitle("");
        this.setSize(400, 320);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width
                - this.getSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height
                - this.getSize().height;
        this.setLocation((int) (width / 2), (int) (height / 2));

        btn_CheckRemain.setSize(60, 30);
        btn_CheckRemain.setLocation(20, 100);
        btn_CheckRemain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("查询余额");
                DataOutputStream os;
                try {
                    os = new DataOutputStream(Var.SOCKET.getOutputStream());
                    os.writeUTF("BALA");
                    os.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });



        btn_GetMoney.setSize(60, 30);
        btn_GetMoney.setLocation(320, 100);
        btn_GetMoney.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("取款");
                Var.ATM_FRAME.removeAll();
                Var.ATM_FRAME.add((new Frame_GetMoney()).pal_GetMoney);
            }
        });



        btn_Exit.setSize(60, 30);
        btn_Exit.setLocation(320, 250);
        btn_Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("退出");
                DataOutputStream os;
                try {
                    os = new DataOutputStream(Var.SOCKET.getOutputStream());
                    os.writeUTF("BYE");
                    os.flush();
                    Var.SOCKET.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Var.ATM_FRAME.removeAll();
                Var.ATM_FRAME.add((new Frame_Login()).pal_Login);
                Var.ATM_FRAME.setTitle("ATM取款机--欢迎使用...");
            }
        });

        pal_Index.setLayout(null);
        pal_Index.setSize(400, 300);
        pal_Index.setLocation(0, 0);

        pal_Index.add(this.btn_CheckRemain);
        pal_Index.add(this.btn_GetMoney);
        pal_Index.add(this.btn_Exit);

        this.add(this.pal_Index);

        this.setLayout(null);
    }

    public static void main(String[] args) {
    }
}
