package by.gsu.isheremetov.phonestation.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.models.Subscribe;

public interface SubscribeDAO {
    public ArrayList<Subscribe> getSubscribesForUser(int id) throws SQLException;
    public int deleteSubscribe(int serviceId, int userId) throws SQLException;
    public int addSubscribe(int serviceId, int userId) throws SQLException;
}
