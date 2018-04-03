package website.DAO;

import website.bean.news;

public interface newsDAO {
    public boolean delete(String title);
    public boolean update(news news);
    public boolean add(news news);
}
