/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;

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
    public boolean isEnroll(int userID, int id){
        xSql = "SELECT * FROM dbo.UsersEnroll WHERE UserId = ? AND CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            if(rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
