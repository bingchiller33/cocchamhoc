/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Exam;

/**
 *
 * @author Viet
 */
public class ExamDAO extends MyDAO{
    public List<Exam> getExams(int courseID) throws SQLException{
        List<Exam> examList = new ArrayList<>();
        xSql = "SELECT * FROM dbo.Exams WHERE CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseID);
        rs = ps.executeQuery();
        while(rs.next())
            examList.add(new Exam(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTime(4)));
        return examList;
    }
    public Exam getExamByID(int examID) throws SQLException{
        xSql = "SELECT * FROM dbo.Exams WHERE ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, examID);
        rs = ps.executeQuery();
        if(rs.next())
            return new Exam(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTime(4));
        return null;
    } 
    
    public int getPassAttempt(int userId, int courseId) throws SQLException{
        xSql = "SELECT COUNT(DISTINCT c.ExamID)\n"
                + "FROM\n"
                + "(\n"
                + "    SELECT a.Score,\n"
                + "           b.ExamID\n"
                + "    FROM dbo.ExamPapers a\n"
                + "        FULL JOIN dbo.Exams b\n"
                + "            ON a.ExamID = b.ExamID\n"
                + "    WHERE b.ExamID IN\n"
                + "          (\n"
                + "              SELECT ExamID FROM dbo.Exams WHERE CourseID = ?\n"
                + "          )\n"
                + "          AND a.UserID = ?\n"
                + "    GROUP BY a.Score,\n"
                + "             b.ExamID\n"
                + "    HAVING CEILING(   a.Score /\n"
                + "                      (\n"
                + "                          SELECT COUNT(*) FROM dbo.Questions WHERE ExamID = b.ExamID\n"
                + "                      ) * 100\n"
                + "                  ) >= 80\n"
                + ") AS c;";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseId);
        ps.setInt(2, userId);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public int getExamCount(int courseID) throws SQLException{
        xSql = "SELECT COUNT(*) FROM dbo.Exams WHERE CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseID);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
