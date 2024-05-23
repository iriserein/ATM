package atm_server;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;



public class ATM_Server_Main extends Frame{
    Thread_Server st;
    ATM_Server_Main frame = this;
    Label lbl_Message = new Label("");
    Button btn_Login = new Button("开启服务器");
    Button btn_Exit = new Button("关闭服务器");
    /**
     * @param args
     */

    public ATM_Server_Main(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("服务器停止！");
                System.exit(0);
            }
            public void windowClosing(WindowEvent e) {

                frame.dispose();
            }
        });
        this.setTitle("ATM自动取款机服务器端");
        this.setSize(400, 320);


        int width = Toolkit.getDefaultToolkit().getScreenSize().width
                - this.getSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height
                - this.getSize().height;
        this.setLocation((int) (width / 2), (int) (height / 2));
        this.setLayout(null);


        lbl_Message.setSize(220, 20);
        lbl_Message.setLocation(130, 250);
        lbl_Message.setForeground(Color.RED);
        lbl_Message.setFont(new Font("Default",Font.BOLD, 20));


        btn_Login.setSize(70, 30);
        btn_Login.setLocation(150, 120);
        btn_Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                User_Manage.getUserFile();
                st = new Thread_Server();
                st.start();
                lbl_Message.setText("服务器启动！");
                System.out.println("服务器启动！");
            }
        });

        btn_Exit.setSize(70, 30);
        btn_Exit.setLocation(150, 160);



        btn_Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                lbl_Message.setText("服务器停止！");
                System.out.println("服务器停止！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });

        this.add(this.btn_Login);
        this.add(this.btn_Exit);
        this.add(this.lbl_Message);


        this.setLayout(null);

    }

    public static void main(String[] args) {
        ATM_Server_Main asm = new ATM_Server_Main();
        asm.setVisible(true);
    }

}
