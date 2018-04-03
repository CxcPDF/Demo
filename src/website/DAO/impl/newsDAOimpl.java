package website.DAO.impl;

import website.bean.news;
import website.DAO.newsDAO;
import website.DataBase.DBManager;

public class newsDAOimpl implements newsDAO {
    @Override
    public boolean delete(String title) {
        String sql="delete from news where newTitle="+title+"";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }

    @Override
    public boolean update(news news) {
        return false;
    }

    @Override
    public boolean add(news news) {
        String sql=" insert into from news("+news.getNewsTitle()+","+news.getContext()+","+
                news.getCreateTime().toString()+")";
        DBManager dbManager=new DBManager();
        return (dbManager.update(sql)==1);
    }
}
