package website.DAO.impl;

import website.bean.picture;
import website.DAO.pictureDAO;
import website.DataBase.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class pictureDAOimpl implements pictureDAO {
    @Override
    public boolean AddPicture(picture picture) {
        String sql="insert into picture(name,path) values (\""+picture.getName()+"\",\""+
                picture.getPath()+"\");";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }

    @Override
    public boolean DeletePicture(String name) {
        String sql="delete from picture where pictureName=\""+name+ "\";";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }

    @Override
    public List<picture> ListPictures() {
        return null;
    }

    @Override
    public Vector getPictureSavePath(String itemName) {
        Vector v=new Vector();
        String sql="select * from picture where name like \"%"+itemName+"%\";";
        DBManager dbManager=new DBManager();
        ResultSet set=dbManager.query(sql);
        try {
            while (set.next()){
                v.add(set.getString("name")+"_"+set.getString("path"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }
}
