/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.MyDAO;
import model.Users;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LAPTOP
 */
public class UserDAO extends MyDAO {

    public int insertUser(Users t) {
        int result = 0;
        try {
            String xSql = "INSERT INTO Users (UserName, Email, Password, IsAdmin, DOB, Gender, PhoneNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            ps = con.prepareStatement(xSql);
//            ps.setInt(1, t.getId());
            ps.setString(1, t.getFullName());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getPassword());
            ps.setBoolean(4, t.isIsAdmin());
            ps.setDate(5, t.getDob());
            ps.setBoolean(6, t.isGender());
            ps.setString(7, t.getPhoneNumber());

            result = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
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

    public List<Users> checkUser(String email, String passWord) {
        List<Users> t = new ArrayList<>();
        xSql = "select * from Users where Email = ? and [Password] = ?";
        System.out.println(email);
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, passWord);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.add(new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getDate(6),
                        rs.getBoolean(7),
                        rs.getString(8))
                );
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public boolean checkAdmin(String email, String passWord) {
        List<Users> t = new ArrayList<>();
        xSql = "select * from Users where Email = ? and [Password] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, passWord);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.add(new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getDate(6),
                        rs.getBoolean(7),
                        rs.getString(8))
                );
            }
            if (!t.isEmpty()) {
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i).isIsAdmin()) {
                        return true;
                    }
                }
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
