package website.DAO;

import website.bean.user;

public interface userDAO {
    public boolean add(user user);
    public int update(user user,String sql);
    public int getId(user user);
    public String getPassword(String userName);//返回对应用户的密码
    public void createUserTable(String userName);//创建每个用户对应的表格
    public boolean getAccountStatus(String name);//返回用户账号的状态
    public boolean changeAccountStatus(String userName,boolean b);//修改用户的账号状态
    public int getMoney(String userName);//获取用户账户中有多少钱
    public boolean chargeMoney(int money,String userName);//进行充值操作
}
