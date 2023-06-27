/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author hoang
 */
public class ProfileDAO extends MyDAO {

    public boolean updateProfile(String username, String phoneNumber, Boolean gender, Date dob, int userId) {
        xSql = "UPDATE Users SET username = ?, phoneNumber = ?, gender = ?, dob = ? WHERE userId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, phoneNumber);
            ps.setBoolean(3, gender);
            ps.setDate(4, dob);
            ps.setInt(5, userId);

            int isUpdated = ps.executeUpdate();

            return isUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmail(String email, int userId) {
        xSql = "UPDATE Users SET email = ? WHERE userId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setInt(2, userId);

            int isUpdated = ps.executeUpdate();

            return isUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
