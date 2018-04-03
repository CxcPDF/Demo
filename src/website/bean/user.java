package website.bean;

/*
用户的基本信息
 */
public class user {
    public String name;
    public String password;
    public String sex;
    public String birthday;
    public double money;//钱包金额
    public boolean accountStatus;//账号状态，false为不可用，true为可用

    public user() {
    }

    public user(String name) {
    }

    public user(String name, String password, String sex, String birthday) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }
}
