package by.gsu.isheremetov.phonestation.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.models.Service;

public interface ServiceDAO {
    public ArrayList<Service> getServiceList() throws SQLException;
}
