/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Course;

/**
 *
 * @author Viet
 */
public class UserEnrollDAO extends MyDAO {

    public void enroll(int userID, int id) {
        xSql = "INSERT INTO dbo.UsersEnroll\n"
                + "(\n"
                + "    UserId,\n"
                + "    CourseID\n"
                + ")\n"
                + "VALUES\n"
                + "(   ?, -- UserId - int\n"
                + "    ? -- CourseID - int\n"
                + "    )";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEnroll(int userID, int id) {
        xSql = "SELECT * FROM dbo.UsersEnroll WHERE UserId = ? AND CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<Integer, String> getEnrollmentStatus(int userId) throws SQLException {
        xSql = "select * from UsersEnroll where UserId = ?";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, userId);
        rs = ps.executeQuery();

        Map<Integer, String> res = new HashMap<>();
        while (rs.next()) {
            res.put(rs.getInt("CourseId"), rs.getString("Status"));
        }

        return res;
    }

    public Map<Course, Integer> getAllProgress(List<Course> courses, int userId) throws SQLException {
        Map<Course, Integer> map = new HashMap<>();
        for (Course c : courses) {
            map.put(c, getProgress(userId, c.getId()));
        }
        return map;
    }

    public int getProgress(int userID, int id) throws SQLException {
        xSql = "SELECT Progress FROM dbo.UsersEnroll WHERE UserId = ? AND CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userID);
        ps.setInt(2, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void trackProgress(int userID, int id) throws SQLException {
        xSql = "SELECT Progress FROM dbo.UsersEnroll WHERE UserId = ? AND CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userID);
        ps.setInt(2, id);
        rs = ps.executeQuery();
        rs.next();
        int progress = rs.getInt(1);
        progress++;
        xSql = "UPDATE dbo.UsersEnroll SET Progress = ? WHERE UserId = ? AND CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, progress);
        ps.setInt(2, userID);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public void completeCourse(int userID, int courseID) throws SQLException {
        xSql = "UPDATE dbo.UsersEnroll SET Status = 'Complete' WHERE UserId = ? AND CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, userID);
        ps.setInt(2, courseID);
        ps.executeUpdate();
    }

    public String getStatus(int userID, int courseID) throws SQLException {
        xSql = "SELECT [Status] FROM dbo.UsersEnroll WHERE CourseID = ? AND UserId = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseID);
        ps.setInt(2, userID);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return "Learning";
    }
}
