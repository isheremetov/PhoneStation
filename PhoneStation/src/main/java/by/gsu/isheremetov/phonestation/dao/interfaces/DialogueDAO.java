package by.gsu.isheremetov.phonestation.dao.interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import by.gsu.isheremetov.phonestation.models.Dialogue;

public interface DialogueDAO {
    public void addDialogue(Date dialogueDate, int duration, String destination, int userId, int serviceId) throws SQLException;
    public ArrayList<Dialogue> getDialogues(int userId) throws SQLException;
    public Dialogue getDialogue(int dialogueId) throws SQLException;
    public void pay(int userId, int dialogueId) throws SQLException;

}
