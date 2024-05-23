package atm_client;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;

public class Frame_GetMoney extends Frame {

    Frame_GetMoney frame = this;
    Button btn_Reset = new Button("重输金额");
    Button btn_OK = new Button("确认");
    Button btn_Back = new Button("返回");
    Label lbl_Money = new Label("请输入：");
    TextField txt_Money = new TextField();
    Panel pal_GetMoney = new Panel();

    public Frame_GetMoney() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }

            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        this.setTitle("ATM自动取款机");
        this.setSize(400, 320);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width
                - this.getSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height
                - this.getSize().height;
        this.setLocation((int) (width / 2), (int) (height / 2));


        lbl_Money.setSize(90, 20);
        lbl_Money.setForeground(new Color(0, 102, 204));
        lbl_Money.setLocation(50, 150);
        lbl_Money.setFont(new Font("Default",Font.BOLD, 20));

        txt_Money.setSize(160, 25);
        txt_Money.setLocation(140, 150);
        txt_Money.setFont(new Font("Default",Font.BOLD, 20));

        btn_Reset.setSize(60, 30);
        btn_Reset.setLocation(320, 150);
        // 重置按钮事件监听器
        btn_Reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("重输金额");
                txt_Money.setText("");
            }
        });

        btn_OK.setSize(60, 30);
        btn_OK.setLocation(320, 100);
        btn_OK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("确认");
                DataOutputStream os;
                try {
                    os = new DataOutputStream(Var.SOCKET.getOutputStream());
                    os.writeUTF("WDRA");
                    os.writeDouble(Double.valueOf(txt_Money.getText()));
                    os.flush();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


        btn_Back.setSize(60, 30);
        btn_Back.setLocation(320, 250);
        btn_Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("返回");
                Var.ATM_FRAME.removeAll();
                Var.ATM_FRAME.add((new Frame_Index()).pal_Index);
            }
        });


        // 向panel添加组件
        pal_GetMoney.add(this.btn_Reset);
        pal_GetMoney.add(this.btn_OK);
        pal_GetMoney.add(this.btn_Back);
        pal_GetMoney.add(this.txt_Money);
        pal_GetMoney.add(this.lbl_Money);

        pal_GetMoney.setLayout(null);
        pal_GetMoney.setSize(400, 300);
        pal_GetMoney.setLocation(0, 0);

        this.add(this.pal_GetMoney);

        this.setLayout(null);
    }

    public static void main(String[] args) {
    }
}
