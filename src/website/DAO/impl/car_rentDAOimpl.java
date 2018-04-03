package website.DAO.impl;

import website.bean.car;
import website.DAO.car_rentDAO;
import website.DataBase.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class car_rentDAOimpl implements car_rentDAO {

    @Override
    public boolean delete(String ID) {
        String sql = "delete from car where ID=" + ID + ";";//
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    /**
     * 将用户发布的车辆信息储存到数据库中
     *
     * @param car
     * @return
     */
    @Override
    public boolean add(car car) {
        String sql = "insert into car (carName,day_price,month_price,car_status,ownerName," +
                "pingpai,pailiang,licheng,age,type,statusCode,orderCode,getCarLocation,returnCarLocation,rentedUser," +
                "rentCarDate,picture_name) values " +
                "(\"" + car.getCarName() + "\"," + car.getDay_price() + "," +
                car.getMonth_price() + ",false,\"" + car.getOwnerName() + "\",\"" + car.getPingpai() + "\"," +
                car.getPailiang() + "," + car.getLicheng() + "," + car.getAge() + ",\"" + car.getType() + "\","
                + car.getStatusCode() + "," + car.getOrderCode() + ",\"" + car.getCarLocation + "\",\"" + car.getReturnCarLocation() + "\"," +
                car.getRentedUser() + ",\"" + car.getRentCarDate() + "\",\"" + car.getPicture_name() + "\");";
        DBManager dbManager = new DBManager();
        return (dbManager.update(sql) == 1);
    }

    @Override
    public int update(String sql) {//对数据库的内容进行更新
        DBManager dbManager = new DBManager();
        return dbManager.update(sql);
    }

    @Override
    public ResultSet query(String sql) {
        DBManager dbManager = new DBManager();
        return dbManager.query(sql);
    }

    /**
     * 返回租车时间
     */
    @Override
    public String getRentTime(String ID) {
        String sql = "select * from car where ID=" + ID + ";";
        DBManager dbManager = new DBManager();
        ResultSet set = dbManager.query(sql);
        String rentCarTime = null;
        try {
            while (set.next()) {
                rentCarTime = set.getString("rentCarDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentCarTime;
    }

    /**
     * 返回不同的租金价格
     */
    @Override
    public int[] getPrice(String ID) {
        String sql = "select * from car where carName=" + ID + ";";
        DBManager dbManager = new DBManager();
        ResultSet set = dbManager.query(sql);
        int[] a = new int[2];
        try {
            while (set.next()) {
                a[0] = set.getInt("day_price");
                a[1] = set.getInt("month_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 返回车的主人
     */
    public String getOwnner(String ID) {
        String sql = "select * from car where ID=" + ID + ";";
        DBManager dbManager = new DBManager();
        ResultSet set = dbManager.query(sql);
        String owner = "";
        try {
            while (set.next()) {
                owner = set.getString("ownerName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }
}
