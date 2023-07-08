/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Question;

/**
 *
 * @author Viet
 */
public class QuestionDAO extends MyDAO{
    public List<Question> getQuestions(int examID) throws SQLException{
        List<Question> questionList = new ArrayList<>();
        ChoicesDAO cd = new ChoicesDAO();
        xSql = "SELECT * FROM dbo.Questions WHERE ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, examID);
        rs = ps.executeQuery();
        while(rs.next()){
            questionList.add(new Question(rs.getInt(1), rs.getString(2), rs.getInt(3), cd.getChoices(rs.getInt(1))));
        }
        return questionList;
    }
    public int getQuestionCount(int examID) throws SQLException{
        xSql = "SELECT COUNT(*) FROM dbo.Questions WHERE ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, examID);
        rs = ps.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
}
