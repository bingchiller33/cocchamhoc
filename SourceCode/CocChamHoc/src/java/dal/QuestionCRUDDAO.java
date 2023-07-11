/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ChoiceCRUD;
import model.QuestionCRUD;

/**
 *
 * @author Phuoc
 */
public class QuestionCRUDDAO extends MyDAO {

    public List<QuestionCRUD> getQuestion(int examId) throws SQLException {
        List<QuestionCRUD> lst = new ArrayList<>();
        xSql = "select * from Questions where ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, examId);
        rs = ps.executeQuery();
        while (rs.next()) {
            lst.add(new QuestionCRUD(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return lst;
    }

    public void createDefaultQuestion(int examId) throws SQLException {
        xSql = "Insert into Questions (QuestionDetail, ExamID) values (?,?)";
        ps = con.prepareStatement(xSql);
        ps.setString(1, "Default tsad Questions");
        ps.setInt(2, examId);
        ps.executeUpdate();
    }

    public void updateQuestion(int questionId, int examId, String questionDetail) throws SQLException {
        xSql = "update Questions set QuestionDetail  = ? where QuestionID= ? and ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, questionDetail);
        ps.setInt(2, questionId);
        ps.setInt(3, examId);
        ps.executeUpdate();
    }

    public Map<QuestionCRUD, List<ChoiceCRUD>> getGroupChoice(List<QuestionCRUD> questions) throws SQLException {
        ChoiceDAO cdao = new ChoiceDAO();
        HashMap<QuestionCRUD, List<ChoiceCRUD>> kv = new HashMap<>();
        for (QuestionCRUD q : questions) {
            kv.put(q, cdao.getChoicebyQuestionId(q.getQuestionId()));
        }
        return kv;
    }

}
