/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Certificates;
import model.Course;
import model.Exam;
import model.Level;

/**
 *
 * @author LAPTOP
 */
public class CertificateDAO extends MyDAO {

    public List<Course> getCourseCer(int uId) {
        List<Course> list = new ArrayList<>();
        xSql = "SELECT * \n"
                + "FROM dbo.Courses JOIN dbo.Levels ON Levels.LevelID = Courses.LevelID \n"
                + "JOIN dbo.Categories ON Categories.CategoryID = Courses.CategoryID \n"
                + "JOIN dbo.[Certificates] ON Courses.CourseID = [Certificates].CourseID "
                + "where [Certificates].UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level level = new Level(rs.getInt("LevelID"), rs.getString("LevelDescription"));
                Category cat = new Category(rs.getInt("CategoryID"), rs.getString("CategoryDescription"));
                Course c = new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getString("CourseDescription"), rs.getString("CourseBannerImage"), rs.getDate("PublishDate"), rs.getString("Lecturer"), level, cat, rs.getInt("DurationInSeconds"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getTitle(int uId, int cId) {
        try {
            xSql = "select c.Title\n"
                    + "from [Certificates] cf join [Users] u \n"
                    + "on cf.UserID = u.UserID join [Courses] c\n"
                    + "on u.UserID = c.CourseID \n"
                    + "where cf.UserID = ? and cf.CourseID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            ps.setInt(2, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Date getDate(int uId, int cId) {
        try {
            xSql = "select cf.IssueDate\n"
                    + "from [Certificates] cf join [Users] u \n"
                    + "on cf.UserID = u.UserID join [Courses] c\n"
                    + "on u.UserID = c.CourseID \n"
                    + "where cf.UserID = ? and cf.CourseID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            ps.setInt(2, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDate(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEligibleForCertificate(int passAttemptCount, int totalExamCount){
        if(passAttemptCount == totalExamCount)
            return true;
        return false;
    }

    public boolean hasCertificate(int userId, int courseId) throws SQLException {
        xSql = "SELECT * FROM dbo.Certificates WHERE UserID = ? AND CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userId);
        ps.setInt(2, courseId);
        rs = ps.executeQuery();
        return rs.next();
    }

    public void issueCertificate(int userId, int courseId) throws SQLException {
        xSql = "INSERT INTO dbo.Certificates\n"
                + "(\n"
                + "    UserID,\n"
                + "    CourseID,\n"
                + "    IssueDate\n"
                + ")\n"
                + "VALUES\n"
                + "(   ?,        -- UserID - int\n"
                + "    ?,        -- CourseID - int\n"
                + "    GETDATE() -- IssueDate - date\n"
                + "    )";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userId);
        ps.setInt(2, courseId);
        ps.executeUpdate();
    }
    public void UpdateStatusCer(int uId, int cId){
        xSql = "update Certificates set Status='Revork' where CourseID = ? and UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            ps.setInt(2, uId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Certificates> getStatusCer(int uId){
        List<Certificates> list = new ArrayList<>();
        xSql = "select * from Certificates where UserID = ?";
        try {
            ps = con.prepareStatement(xSql); 
            ps.setInt(1, uId);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Certificates(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getString(4)));
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
