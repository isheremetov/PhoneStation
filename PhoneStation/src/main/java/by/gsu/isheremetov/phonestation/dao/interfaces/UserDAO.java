package by.gsu.isheremetov.phonestation.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.models.User;

public interface UserDAO {
    public User getUser(String phone, String password) throws SQLException;
    public void addUser(String phone, String password, String username, String email, String role, int active) throws SQLException;
    public ArrayList<User> getUsersList() throws SQLException;
    public void updateUser(int id, String phone, String password, String username, String email, Number purse, String role, Boolean active) throws SQLException;
}
