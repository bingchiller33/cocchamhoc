/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.servlet.jsp.jstl.sql.Result;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ChoiceCRUD;

/**
 *
 * @author Phuoc
 */
public class ChoiceDAO extends MyDAO {

    public List<ChoiceCRUD> getChoicebyQuestionId(int questionId) throws SQLException {
        List<ChoiceCRUD> lst = new ArrayList<>();
        xSql = "select * from Choices c inner join Questions q on c.QuestionID = q.QuestionID \n"
                + "where q.QuestionID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, questionId);
        rs = ps.executeQuery();
        while(rs.next()){
            lst.add(new ChoiceCRUD(rs.getInt("ChoiceID"),rs.getInt("QuestionID"),rs.getBoolean("IsTrueAnswer"), rs.getString("Description")) );
        }
        return lst;
    }
    public void createDefaultChoice(int questionId) throws SQLException{
        xSql="Insert into Choices (QuestionID,Description) values (?,?)";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, questionId);
        ps.setString(2,"Default Answer");
        ps.executeUpdate();
    }
    public void updateChoice(String description,boolean isTrueAnswer , int questionId, int choiceId ) throws SQLException {
        xSql="Update Choices set Description = ? ,IsTrueAnswer = ? where QuestionID = ? and ChoiceID = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, description );
        ps.setBoolean(2, isTrueAnswer);
        ps.setInt(3, questionId);
        ps.setInt(4, choiceId);
        ps.executeUpdate();
    }
}
