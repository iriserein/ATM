package atm_client;

import java.net.Socket;

public class Var {
    static Frame_Login ATM_FRAME = new Frame_Login();
    static int SERVER_CODE = 0;
    static Socket SOCKET = null;
    static String CURRENT_ACCOUNT = "";
}
