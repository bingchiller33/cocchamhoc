/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Question;

/**
 *
 * @author Viet
 */
public class UserAnswerDAO extends MyDAO{
    public Map<Question, Integer> getUserAnswers(List<Question> questionList, int paperId) throws SQLException{
        Map<Question, Integer> userAnswers = new HashMap<>();
        for(Question q :questionList){
            xSql = "SELECT * FROM dbo.UserAnswers JOIN dbo.Choices ON Choices.ChoiceID = UserAnswers.ChoiceID WHERE QuestionID = ? AND PaperID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, q.getQuestionID());
            ps.setInt(2, paperId);
            rs = ps.executeQuery();
            if(rs.next())
                userAnswers.put(q, rs.getInt(3));
            else 
                userAnswers.put(q, -1);
            rs.close();
            ps.close();
        }
        return userAnswers;
    }
}
