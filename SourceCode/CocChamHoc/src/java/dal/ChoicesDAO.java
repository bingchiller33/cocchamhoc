/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Choice;

/**
 *
 * @author Viet
 */
public class ChoicesDAO extends MyDAO{
    public List<Choice> getChoices(int questionID) throws SQLException{
        List<Choice> choiceList = new ArrayList<>();
        xSql = "SELECT * FROM dbo.Choices WHERE QuestionID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, questionID);
        rs = ps.executeQuery();
        while(rs.next()){
            choiceList.add(new Choice(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4)));
        }
        return choiceList;
    }

}
