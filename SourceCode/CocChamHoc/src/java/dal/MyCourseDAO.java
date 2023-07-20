/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Category;
import model.Course;
import model.Course;
import model.Level;
import model.Level;

/**
 *
 * @author LAPTOP
 */
public class MyCourseDAO extends MyDAO {
//  and U.[Status] = 'Learning'
    public List<Course> listMyCourse(int uId) {
        CourseDAO courseDAO = new CourseDAO();
        List<Course> t = new ArrayList<>();
        xSql = "SELECT *\n"
                + "FROM dbo.Courses\n"
                + "    JOIN dbo.UsersEnroll\n"
                + "        ON UsersEnroll.CourseID = Courses.CourseID\n"
                + "    JOIN dbo.Categories\n"
                + "        ON Categories.CategoryID = Courses.CategoryID\n"
                + "    JOIN dbo.Levels\n"
                + "        ON Levels.LevelID = Courses.LevelID\n"
                + "WHERE UserId = ?\n"
                + "      AND Status = 'Learning'";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.add(courseDAO.fromResultSet(rs));
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
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
            while (rs.next()) {
                quantityCourse = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantityCourse;
    }
}
