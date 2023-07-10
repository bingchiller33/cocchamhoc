/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Chapter;

import model.Course;
import model.Lesson;

import model.Level;

/**
 *
 * @author LAPTOP
 */
public class ProgressDAO extends MyDAO {
    
    public List<Course> listProgressCourse(int uId) {
        List<Course> t = new ArrayList<>();
        xSql = "select * from Courses C inner join UsersEnroll U on  C.CourseID = U.CourseID where U.UserId = ?  and U.[Status] = 'Learning'";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level level = new Level(rs.getInt(1), rs.getString(2));
                Category category = new Category(rs.getInt(1), rs.getString(2));
                t.add(new Course(rs.getInt("CourseID"),
                        rs.getString("Title"),
                        rs.getString("courseDescription"),
                        rs.getString("CourseBannerImage"),
                        rs.getDate("PublishDate"),
                        rs.getString("Lecturer"),
                        level,
                        category,
                        rs.getInt("DurationInSeconds")));
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
  public List<Lesson> getLessonData(int courseId) throws SQLException {
        ArrayList<Lesson> lessons = new ArrayList<>();
        xSql = "SELECT *\n"
                + "FROM Lessons l\n"
                + "JOIN Chapters c ON l.ChapterID = c.ChapterID\n"
                + "JOIN Courses cr ON c.CourseID = cr.CourseID\n"
                + "WHERE cr.CourseID = ?\n"
                + "ORDER BY c.ChapterID ASC, l.LessonNumber;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson(
                        rs.getInt("ChapterID"),
                        rs.getInt("LessonNumber"),
                        rs.getString("LessonName"),
                        rs.getString("LessonDescription"),
                        rs.getString("LessonVideo"));
                lessons.add(l);
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }
    public int quantityCourse(int uId) {
        int quantityCourse = 0;
        xSql = "select COUNT(*) as quantityCourses\n"
                + "from Courses C inner join UsersEnroll U \n"
                + "on  C.CourseID = U.CourseID \n"
                + "where U.UserId = ?  and U.[Status] = 'Learning'\n"
                + "group by U.UserId";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            rs = ps.executeQuery();
            while(rs.next()){
                quantityCourse = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantityCourse;
    }
   
}
