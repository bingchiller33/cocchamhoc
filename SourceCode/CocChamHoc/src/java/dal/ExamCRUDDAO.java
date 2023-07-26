/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.ExamCRUD;

/**
 *
 * @author Phuoc
 */
public class ExamCRUDDAO extends MyDAO {

    public void createDefaultExam(int courseId) throws SQLException {
        xSql = "Insert into Exams (ExamName,CourseID,Duration)\n"
                + "values(?,?,?)";
        ps = con.prepareStatement(xSql);
        ps.setString(1, "Default Exam");
        ps.setInt(2, courseId);
        String timeString = "00:30:00";
        Time time = Time.valueOf(timeString);
        ps.setTime(3, time);
        ps.execute();
    }

    public List<ExamCRUD> getExam(int courseId) throws SQLException {
        List<ExamCRUD> lst = new ArrayList<>();
        xSql = "select * from Exams\n"
                + "where CourseID =?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseId);
        rs = ps.executeQuery();
        while (rs.next()) {
            Time time = rs.getTime(4);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String formattedTime = sdf.format(time);
            lst.add(new ExamCRUD(rs.getInt(1), rs.getString(2), rs.getInt(3), formattedTime));

        }
        return lst;
    }

    public ExamCRUD getExamById(int examId, int courseId) throws SQLException {
        xSql = "select * from Exams\n"
                + "where ExamID = ?  and  CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, examId);
        ps.setInt(2, courseId);
        rs = ps.executeQuery();
        while (rs.next()) {
            Time time = rs.getTime(4);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String formatedTime = sdf.format(time);
            return new ExamCRUD(rs.getInt(1), rs.getString(2), rs.getInt(3), formatedTime);
        }
        return null;
    }

    public void updateExam(String examName, String duration, int courseId, int examId) throws SQLException {
        xSql = "update Exams set ExamName= ? ,Duration = ? where CourseID = ? and ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, examName);
        // Xu ly String thành sql.Time
        Time examtime = Time.valueOf(duration);
        ps.setTime(2, examtime);
        ps.setInt(3, courseId);
        ps.setInt(4, examId);
        ps.execute();
    }

    public void deleteExam(int courseId, int examId) throws SQLException {
        xSql = "delete Exams where CourseID = ? and ExamID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseId);
        ps.setInt(2, examId);
        ps.execute();
    }

    public ExamCRUD getNewExam() throws SQLException {
        xSql = "SELECT TOP 1 *\n"
                + "FROM Exams\n"
                + "ORDER BY ExamID DESC;";
        ps = con.prepareStatement(xSql);
        rs = ps.executeQuery();
        if(rs.next()){
            return new ExamCRUD(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        ExamCRUDDAO dao = new ExamCRUDDAO();
        System.out.println(dao.getNewExam());
    }

}
