package website.DAO.impl;

import website.bean.park;
import website.DAO.park_rentDAO;
import website.DataBase.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class park_rentDAOimpl implements park_rentDAO {

    @Override
    public boolean delete(String id) {
        String sql="delete from park where parkId="+id+";";
        website.DataBase.DBManager dbManager=new website.DataBase.DBManager();
        return (dbManager.update(sql)==1);
    }

    @Override
    public boolean add(park park) {
        String sql="insert into park (day_price,hour_price,park_status,park_location,tel)" +
                " values ("+park.getDay_price()+","+park.getHour_price()+","
                +","+park.getPark_status()+",\""+ park.getPark_location()+"\",\""+park.getTel()+"\";";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }

    @Override
    public int update(park park,String str) {
        DBManager dbManager=new DBManager();
        return dbManager.update(str);
    }

    @Override
    public ResultSet query(String sql) {
        DBManager dbManager=new DBManager();
        return dbManager.query(sql);
    }

    @Override
    public long getRentTime(int parkId) {
        String sql="select * from park where parkId="+ parkId+";";
        DBManager dbManager=new DBManager();
        ResultSet set=dbManager.query(sql);
        long rentParkTime=0;
        try {
            while (set.next()) {
                rentParkTime = set.getLong("rentParkDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentParkTime;
    }

    @Override
    public int[] getPrice(int parkId) {
        String sql="select * from park where parkId="+parkId+";";
        DBManager dbManager=new DBManager();
        ResultSet set=dbManager.query(sql);
        int[] a=new int[2];
        try {
            while (set.next()) {
                a[0] = set.getInt("hour_price");
                a[1] = set.getInt("day_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}
