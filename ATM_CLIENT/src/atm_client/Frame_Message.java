package atm_client;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame_Message extends Frame {

    Frame_Message frame = this;
    // 进入主界面后的组件
    Button btn_Back = new Button("返回");
    Label lbl_Message = new Label();

    //	Label lbl_AccountName = new Label();
//	Label lbl_Money = new Label();
    Panel pal_Message = new Panel();

    public Frame_Message() {
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

        btn_Back.setSize(60, 30);
        btn_Back.setLocation(320, 250);
        // 确认按钮事件监听器
        btn_Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("返回");
                Var.ATM_FRAME.removeAll();
                Var.ATM_FRAME.add((new Frame_Index()).pal_Index);
            }
        });

        lbl_Message.setSize(200, 30);
        lbl_Message.setLocation(100, 100);
        lbl_Message.setForeground(new Color(0, 102, 204));
        lbl_Message.setFont(new Font("Default",Font.BOLD, 20));


        pal_Message.setLayout(null);
        pal_Message.setSize(400, 300);
        pal_Message.setLocation(0, 0);

        pal_Message.add(this.lbl_Message);
        pal_Message.add(this.btn_Back);

        this.add(this.pal_Message);

        // 显示窗口
        this.setLayout(null);
//		this.setVisible(true);
    }

    public static void main(String[] args) {
//		new ShowMessage_Frame();
    }
}
