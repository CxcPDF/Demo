package website.DAO;

import website.bean.park;

import java.sql.ResultSet;

public interface park_rentDAO {
    public boolean delete(String id);//删除停车位
    public boolean add(park park);//增加一个停车位
    public int update(park park,String sql);//更新停车位的数据
    public ResultSet query(String sql);
    public long getRentTime(int parkId);
    public int[] getPrice(int parkId);
}
