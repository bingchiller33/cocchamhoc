/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author hoang
 */
public class UserDAO extends MyDAO {

    public int insertUser(User t) {
        int result = 0;
        try {
            String xSql = "INSERT INTO Users (UserName, Email, Password, IsAdmin, DOB, Gender, PhoneNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            ps = con.prepareStatement(xSql);
//            ps.setInt(1, t.getId());
            ps.setString(1, t.getUsername());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getPassword());
            ps.setBoolean(4, t.isIsAdmin());
            ps.setDate(5, t.getDoB());
            ps.setBoolean(6, t.isGender());
            ps.setString(7, t.getPhoneNumber());
            
            result = ps.executeUpdate();           
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public boolean userCheck(String email) {
        boolean ketQua = false;

        try {
            String sql = "SELECT * FROM Users WHERE Email=?";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1, email);

            rs = ps.executeQuery();

            while (rs.next()) {
                ketQua = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public boolean usernameCheck(String email) {
        boolean ketQua = false;
        try {
            String sql = "SELECT * FROM Users WHERE Email=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            System.out.println(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ketQua = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

}
