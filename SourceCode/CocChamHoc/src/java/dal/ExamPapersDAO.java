/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.ExamPapers;
import model.Question;

/**
 *
 * @author Viet
 */
public class ExamPapersDAO extends MyDAO {

    public List<ExamPapers> getExamPapers(int examID, int userID) throws SQLException {
        List<ExamPapers> list = new ArrayList<>();
        xSql = "SELECT * FROM dbo.ExamPapers WHERE ExamID = ? and UserID = ? and State = 2";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, examID);
        ps.setInt(2, userID);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new ExamPapers(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getInt(6), rs.getInt(7)));
        }
        return list;
    }
    
    public ExamPapers getExamPaperByID(int attemptId) throws SQLException{
        xSql = "SELECT * FROM dbo.ExamPapers WHERE paperID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, attemptId);
        rs = ps.executeQuery();
        if(rs.next())
            return new ExamPapers(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getInt(6), rs.getInt(7));
        return null;
    }
    
    public int attempt(int userID, int examID) throws SQLException {
        int check = isAttempting(userID, examID);
        if (check != -1) {
            return check;
        }
        xSql = "INSERT INTO dbo.ExamPapers\n"
                + "(\n"
                + "    UserID,\n"
                + "    ExamID,\n"
                + "    TimeStart,\n"
                + "    State\n"
                + ")\n"
                + "VALUES\n"
                + "(   ?,         -- UserID - int\n"
                + "    ?,         -- ExamID - int\n"
                + "    GETDATE(), -- TimeStart - datetime\n"
                + "    1         -- State - int    \n"
                + "    )";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userID);
        ps.setInt(2, examID);
        ps.executeUpdate();
        xSql = "SELECT PaperID FROM dbo.ExamPapers WHERE State = 1 AND UserID = ? AND ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userID);
        ps.setInt(2, examID);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public int isAttempting(int userID, int examID) throws SQLException {
        xSql = "SELECT * FROM dbo.ExamPapers WHERE State = 1 AND userID = ? AND ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userID);
        ps.setInt(2, examID);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public void completeAttempt(int paperID, Map<Question, Integer> userAnswers, int grade) throws SQLException {
        xSql = "UPDATE dbo.ExamPapers SET State = 2, TimeEnd = GETDATE(), Score = ? WHERE PaperID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, grade);
        ps.setInt(2, paperID);
        ps.executeUpdate();
        for (Map.Entry<Question, Integer> entry : userAnswers.entrySet()) {
            Question key = entry.getKey();
            int val = entry.getValue();
            if (val != -1) {
                xSql = "INSERT INTO dbo.UserAnswers\n"
                        + "(\n"
                        + "    PaperID,\n"
                        + "    ChoiceID\n"
                        + ")\n"
                        + "VALUES\n"
                        + "(   ?, -- PaperID - int\n"
                        + "    ?  -- ChoiceID - int\n"
                        + "    )";
                ps = con.prepareStatement(xSql);
                ps.setInt(1, paperID);
                ps.setInt(2, val);
                ps.executeUpdate();
            }
        }
    }

    public ExamPapers getBestAttempt(int userID, int examID) throws SQLException{
        xSql = "SELECT TOP(1) *\n"
                + "FROM dbo.ExamPapers\n"
                + "WHERE Score>=ALL\n"
                + "(\n"
                + "    SELECT Score FROM dbo.ExamPapers WHERE Score IS NOT NULL\n"
                + ")\n"
                + "      AND UserID = ? \n"
                + "      AND ExamID = ? \n"
                + "ORDER BY TimeEnd DESC";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userID);
        ps.setInt(2, examID);
        rs = ps.executeQuery();
        if(rs.next())
            return new ExamPapers(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getInt(6), rs.getInt(7));
        return null;
    }
}
