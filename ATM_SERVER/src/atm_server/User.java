package atm_server;

public class User {
    String AccountId = "";
    String AccountPassword = "";
    String username = "";
    Double cash = 0.0;

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getAccountPassword() {
        return AccountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        AccountPassword = accountPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }


}
