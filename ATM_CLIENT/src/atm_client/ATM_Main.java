package atm_client;

import java.awt.Color;

public class ATM_Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Var.ATM_FRAME = new Frame_Login();
        Var.ATM_FRAME.setVisible(true);
        Var.ATM_FRAME.setResizable(false);;
    }
}
