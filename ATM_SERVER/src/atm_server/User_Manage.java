package atm_server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;

public class User_Manage {
    /**
     * @return
     * @throws IOException
     */
    static Hashtable userMap = new Hashtable();
    public static Hashtable getUserFile(){

        try {
            File account_file = new File("d:/帐号文件.txt");
            InputStream is;
            is = new FileInputStream(account_file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader dis = new BufferedReader(isr);
            String account;
            while ((account = dis.readLine()) != null) {
                String[] acount_details = account.split(",");
                User user = new User();
                user.setAccountId(acount_details[0]);
                user.setAccountPassword(acount_details[1]);
                user.setCash(Double.valueOf(acount_details[2]));
                userMap.put(acount_details[0], user);
            }


        } catch (FileNotFoundException e) {
            System.out.println("错误：帐户文件没有找到，请检查文件的存放路径！");
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userMap;
    }

    public static User getUser(String accountId){
        User user = (User)userMap.get(accountId);
        return user;
    }

    public static void printUser(String userAccountId) throws IOException{
        Hashtable h = getUserFile();
        User u = (User) h.get(userAccountId);
    }

}
