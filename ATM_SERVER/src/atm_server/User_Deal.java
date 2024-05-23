package atm_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class User_Deal {
    static User user = new User();
    String account_transfer;
    Double money_transfer;
    String account;

    public boolean checkUserLogin(Socket s, DataInputStream dis)
            throws IOException {
        account = dis.readUTF();
        String password = dis.readUTF();
        user = User_Manage.getUser(account);// 如果没有这个用户 则返回 null
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        if (user != null) {
            String password_server = user.getAccountPassword();
            if (password.equals(password_server)) {
                dos.writeInt(61);
                dos.flush();
                // dos.close();
                return true;
            } else {
                dos.writeInt(60);
                dos.flush();
                // dos.close();
                return false;
            }
        } else {
            dos.writeInt(60);
            dos.flush();
            // dos.close();
            return false;
        }
    }

    public void getBalance(Socket s, DataInputStream dis) {
        user = User_Manage.getUser(account);
        Double balance = user.getCash();
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeInt(11);
            dos.writeDouble(balance);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMoney(Socket s, DataInputStream dis) {
        try {
            user = User_Manage.getUser(account);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Double money = dis.readDouble();
            if (money <= user.getCash()) {
                // 减少用户账户余额
                user.setCash(user.getCash() - money);
                // 写入取款日志
                writeWithdrawLog(account, money);
                dos.writeInt(311);
                dos.flush();
            } else {
                dos.writeInt(310);
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 写入取款日志
    private void writeWithdrawLog(String accountId, double amount) {
        try {
            FileWriter writer = new FileWriter("d:/取款日志.txt", true);
            writer.write("账号：" + accountId + " 取款金额：" + amount + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


