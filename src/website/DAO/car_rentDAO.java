package website.DAO;

import website.bean.car;

import java.sql.ResultSet;

public interface car_rentDAO {
    public boolean delete(String id);
    public boolean add(car car);
    public int update(String sql);
    public ResultSet query(String sql);
    public String getRentTime(String Id);
    public int[] getPrice(String Id);
    public String getOwnner(String ID);
}
