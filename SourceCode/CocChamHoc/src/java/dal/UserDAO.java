/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LAPTOP
 */
public class UserDAO extends MyDAO {

    public List<User> searchUsers(String name, int role, int page, int pageSize) throws SQLException {
        int offset = page * pageSize;
        
        xSql = "select * from Users\n"
                + "where (-1 = ? or Role = ?)\n"
                + "and ('' = ? or UserName like ?)\n"
                + "order by UserID\n"
                + "offset ? rows fetch next ? rows only";
        
        ps = con.prepareStatement(xSql);
        ps.setInt(1, role);
        ps.setInt(2, role);
        ps.setString(3, "%" + name + "%");
        ps.setString(4, "%" + name + "%");
        ps.setInt(5, offset);
        ps.setInt(6, pageSize);
        
        rs = ps.executeQuery();
        List<User> users = new ArrayList<>();
        while(rs.next()) {
            users.add(fromResultSet(rs));
        }
        
        return users;
    }
    
    public int searchUserCount(String name, int role) throws SQLException {
        xSql = "select Count(*) from Users\n"
                + "where (-1 = ? or Role = ?)\n"
                + "and ('' = ? or UserName like ?)";
        
        ps = con.prepareStatement(xSql);
        ps.setInt(1, role);
        ps.setInt(2, role);
        ps.setString(3, "%" + name + "%");
        ps.setString(4, "%" + name + "%");
        
        rs = ps.executeQuery();
        return rs.next() ? rs.getInt(1) : -1    ;
    }

    public int insertUser(User t) {
        int result = 0;
        try {
            String xSql = "INSERT INTO Users (UserName, Email, Password, Role, DOB, Gender, PhoneNumber) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            ps = con.prepareStatement(xSql);
            ps.setString(1, t.getFullName());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getPassword());
            ps.setInt(4, t.getRole());
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

    public String getPassword(String email) {
        String result = "";
        try {
            String sql = "SELECT Password FROM Users WHERE Email=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> checkUser(String email, String passWord) {
        List<User> t = new ArrayList<>();
        xSql = "select * from Users where Email = ? and [Password] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, passWord);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
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
        List<User> t = new ArrayList<>();
        xSql = "select * from Users where Email = ? and [Password] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, passWord);
            rs = ps.executeQuery();
            while (rs.next()) {
                t.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getBoolean(7),
                        rs.getString(8))
                );
            }
            if (!t.isEmpty()) {
                for (int i = 0; i < t.size(); i++) {
                    if (t.get(i).getRole() == 3) {
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

    public User fromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getDate(6),
                rs.getBoolean(7),
                rs.getString(8)
        );
    }

    public User getUser(String email, String pass) throws SQLException {
        xSql = "select * from Users where Email = ? and [Password] = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, email);
        ps.setString(2, pass);
        rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        }
        return null;
    }

}
