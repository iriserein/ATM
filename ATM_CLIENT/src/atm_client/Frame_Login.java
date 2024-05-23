package atm_client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Frame_Login extends Frame {
    Frame_Login frame = this;
    Label lbl_CardId = new Label("卡号:");
    Label lbl_Password = new Label("密码:");
    Label lbl_Message = new Label("");
    TextField txt_CardId = new TextField();
    TextField txt_Password = new TextField();
    Button btn_Login = new Button("确认");
    Button btn_Reset = new Button("清空");
    Panel pal_Login = new Panel();
    Button btn_Exit = new Button("关机");


    public Frame_Login() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                try {
                    if (Var.SOCKET != null) {
                        Var.SOCKET.close();
                    }

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.exit(0);
            }

            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        this.setTitle("ATM取款机-欢迎使用...");
        this.setSize(400, 320);
//		this.setBackground(Color.blue);


        //设置frame位置
        int width = Toolkit.getDefaultToolkit().getScreenSize().width
                - this.getSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height
                - this.getSize().height;
        this.setLocation((int) (width / 2), (int) (height / 2));
        //设置frame布局
        this.setLayout(null);


        lbl_Message.setSize(220, 20);
        lbl_Message.setLocation(80, 250);
        lbl_Message.setForeground(Color.RED);
        lbl_Message.setFont(new Font("Default", Font.BOLD, 20));


        lbl_CardId.setSize(80, 20);
        lbl_CardId.setLocation(60, 150);
        lbl_CardId.setForeground(new Color(0, 102, 204));
        lbl_CardId.setFont(new Font("Default", Font.BOLD, 20));

        lbl_Password.setSize(80, 20);
        lbl_Password.setForeground(new Color(0, 102, 204));
        lbl_Password.setLocation(60, 190);
        lbl_Password.setFont(new Font("Default", Font.BOLD, 20));

        txt_CardId.setSize(160, 25);
        txt_CardId.setLocation(140, 150);
        txt_CardId.setFont(new Font("Default", Font.BOLD, 20));

        txt_Password.setSize(160, 25);
        txt_Password.setLocation(140, 190);
        txt_Password.setEchoChar("*".charAt(0));
        txt_Password.setFont(new Font("Default", Font.BOLD, 20));

        btn_Reset.setSize(60, 30);
        btn_Reset.setLocation(320, 150);
        // 清空按钮事件监听器
        btn_Reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btn_Reset_actionPerformed(arg0);
            }
        });

        btn_Login.setSize(60, 30);
        btn_Login.setLocation(320, 100);
        // 确定按钮事件监听器
        btn_Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    btn_Login_actionPerformed(arg0);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        btn_Exit.setSize(60, 30);
        btn_Exit.setLocation(320, 250);
        // 确定按钮事件监听器
        btn_Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("关机");
                try {
                    if (Var.SOCKET != null) {
                        Var.SOCKET.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });


        pal_Login.setLayout(null);//设置panel的布局
        pal_Login.setSize(400, 300);
        pal_Login.setLocation(0, 0);


        pal_Login.add(lbl_CardId);
        pal_Login.add(txt_CardId);
        pal_Login.add(lbl_Password);
        pal_Login.add(txt_Password);
        pal_Login.add(lbl_Message);
        pal_Login.add(btn_Reset);
        pal_Login.add(btn_Login);
        pal_Login.add(btn_Exit);

        this.add(this.pal_Login);

        //显示窗口
        this.setLayout(null);
//		this.setVisible(true);
    }

    public void btn_Reset_actionPerformed(ActionEvent arg0) {
        txt_CardId.setText("");
        txt_Password.setText("");
    }

    public void btn_Login_actionPerformed(ActionEvent arg0) throws InterruptedException {
        String card = txt_CardId.getText();
        String password = txt_Password.getText();
        Var.CURRENT_ACCOUNT = card;
        try {
            Socket s = new Socket("localhost", 2525);//服务器端ip地址
            Var.SOCKET = s;
            Thread getServerCode = new Thread_GetServer(s);
            getServerCode.start();
            DataOutputStream os = new DataOutputStream(s.getOutputStream());
            os.writeInt(6);
            os.writeUTF(card);
            os.writeUTF(password);
            os.flush();
//			getServerCode.stop();
//			System.out.println("ATM获得当前的SERVER_CODE的值：" + ATM_VAR.SERVER_CODE);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//		ATM_VAR.ATM_FRAME = new ATM_Frame();
    }
}