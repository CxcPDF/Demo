package website.DAO.impl;

import website.bean.user;
import website.DAO.userDAO;
import website.DataBase.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *增加用户
 */
public class usersDAOimpl implements userDAO {
    @Override
    public boolean add(user user) {
        String sql="insert into user (name,password,sex,birthday,money,accountStatus) values " +
                "(\""+user.getName()+"\",\""+ user.getPassword()+"\",\""+user.getSex()+"\",\""+user.getBirthday()+"\","+
                user.getMoney()+","+user.isAccountStatus()+");";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }

    @Override
    public int update(user user,String sql) {
        DBManager dbManager=new DBManager();
        return dbManager.update(sql);
    }

    @Override
    public int getId(user user) {//返回用户的ID号
        String sql="select user_id from rentcar_park where name="+user.getName();
        DBManager dbManager=new DBManager();
        ResultSet id=dbManager.query(sql);
        try {
            return id.getInt(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //返回用户的密码
    @Override
    public String getPassword(String userName) {
        String sql="select * from user where name=\""+userName+"\";";
        DBManager dbManager=new DBManager();
        ResultSet set=dbManager.query(sql);
        String psd = null;
        try {
            while (set.next())
            try {
                 psd=set.getString("password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return psd;
    }


    /**
     * 以用户名为表的名称来创建表
     * 表的内容有租借物品的名称，租借状态，借出时间，归还时间以及花费
     * @param userName
     */
    @Override
    public void createUserTable(String userName) {
        String sql="create table `"+userName+
                "` (orderItems char(30),"+
                "itemId int(5),"+
                "orderStatus int(5),"+
                "rentTime char(20),"+
                "returnTime char(20),"+
                "cost int(5));";

        DBManager dbManager=new DBManager();
        if(dbManager.update(sql)==1){
        System.out.println("it's Ok");
        }else{
            System.out.println("oh no");
        }
    }

    /**
     * 返回用户的账户状态，false为已被冻结，
     * true代表可以正常使用
     */
    public boolean getAccountStatus(String name){
        boolean b=false;
        String sql="select * from user where name=\""+name+"\";";
        DBManager dbManager=new DBManager();
        ResultSet set=dbManager.query(sql);
        try {
            while (set.next()){
                b=set.getBoolean("accountStatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public boolean changeAccountStatus(String userName,boolean b) {
        String sql="update user set accountStatus="+b+" where name=\""+userName+"\";";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }

    @Override
    public int getMoney(String userName) {
        String sql="select * from user where name=\""+userName+"\";";
        DBManager dbManager=new DBManager();
        ResultSet set=dbManager.query(sql);
        int money=0;
        try {
            while (set.next()){
                money=set.getInt("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }

    @Override
    public boolean chargeMoney(int money, String userName) {
        String sql="update user set money="+money+" where name=\""+userName+"\";";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }
}
