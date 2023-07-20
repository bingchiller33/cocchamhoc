/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
        while (rs.next()) {
            users.add(fromResultSet(rs));
        }

        return users;
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        xSql = "select * from Users";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(fromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
        return rs.next() ? rs.getInt(1) : -1;
    }

    public User getUserById(int id) throws SQLException {
        xSql = "select * from Users where UserId = ?";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, id);

        rs = ps.executeQuery();
        return rs.next() ? fromResultSet(rs) : null;
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
                t.add(fromResultSet(rs));
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
                t.add(fromResultSet(rs));
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
                rs.getString(8),
                rs.getDate(9),
                rs.getString(10)
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

    public void updateUserRestriction(int id, Date until, String reason) throws SQLException {
        xSql = "update Users set RestrictedUntil = ?, RestrictedReason = ? where UserId = ?";

        ps = con.prepareStatement(xSql);
        ps.setDate(1, until);
        ps.setString(2, reason);
        ps.setInt(3, id);

        ps.execute();

    }

    public void updateUserRole(int id, int newRole) throws SQLException {
        xSql = "update Users set Role = ? where UserId = ?";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, newRole);
        ps.setInt(2, id);

        ps.execute();
    }

    public void updateUserProfile(int id, String fullName, String email, Date dob, int gender, String phoneNumber) throws SQLException {
        xSql = "update Users set UserName = ?, Email = ?, DOB = ?, Gender = ?, PhoneNumber = ? where UserId = ?";

        ps = con.prepareStatement(xSql);
        ps.setString(1, fullName);
        ps.setString(2, email);
        ps.setDate(3, dob);
        ps.setString(5, phoneNumber);
        ps.setInt(6, id);

        switch (gender) {
            case 0:
                ps.setInt(4, 0);
                break;
            case 1:
                ps.setInt(4, 1);
                break;
            default:
                ps.setNull(4, Types.BIT);
        }

        ps.execute();
    }

    public List<User> getUnassignedDesigners(int page, int size, int courseId, String search) {
        List<User> list = new ArrayList<>();
        int offset = (page - 1) * size;
        offset = offset < 0 ? 0 : offset;

        xSql = "SELECT *\n"
                + "FROM Users\n"
                + "WHERE Role = 2 AND UserID NOT IN (\n"
                + "    SELECT UserId\n"
                + "    FROM CourseAssignment\n"
                + "    WHERE CourseId = ?\n"
                + ")";

        // Add the search condition if the search value is not empty
        if (search != null && !search.trim().isEmpty()) {
            xSql += " AND (Users.UserName LIKE ?)";
        }
        xSql += " ORDER BY Users.UserID" // Move the ORDER BY clause here
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            int paramIndex = 2;

            if (search != null && !search.trim().isEmpty()) {
                ps.setString(paramIndex, "%" + search + "%");
                paramIndex++;
            }

            ps.setInt(paramIndex++, offset); // Set the offset parameter
            ps.setInt(paramIndex, size);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(fromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> getAssignedDesigners(int page, int size, int courseId, String search) {
        List<User> list = new ArrayList<>();
        int offset = (page - 1) * size;
        offset = offset < 0 ? 0 : offset;
        // SQL query with the search condition
        xSql = "SELECT *\n"
                + "FROM Users\n"
                + "INNER JOIN CourseAssignment "
                + "ON Users.UserID = CourseAssignment.UserId\n"
                + "WHERE CourseAssignment.CourseId = ? AND Users.Role = 2";

        // Add the search condition if the search value is not empty
        if (search != null && !search.trim().isEmpty()) {
            xSql += " AND (Users.UserName LIKE ?)";
        }
        xSql += " ORDER BY Users.UserID" // Move the ORDER BY clause here
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            int paramIndex = 2;

            if (search != null && !search.trim().isEmpty()) {
                ps.setString(paramIndex, "%" + search + "%");
                paramIndex++;
            }

            ps.setInt(paramIndex++, offset); // Set the offset parameter
            ps.setInt(paramIndex, size);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(fromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getAssignedDesignersCount(int courseId) {
        int count = 0;
        xSql = "SELECT COUNT(*) AS count "
                + "FROM Users\n"
                + "INNER JOIN CourseAssignment "
                + "ON Users.UserID = CourseAssignment.UserId\n"
                + "WHERE CourseAssignment.CourseId = ? AND Users.Role = 2";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getUnassignedDesignersCount(int courseId) {
        int count = 0;
        xSql = "SELECT COUNT(*) AS count "
                + "FROM Users\n"
                + "WHERE Role = 2 AND UserID NOT IN (\n"
                + "    SELECT UserId\n"
                + "    FROM CourseAssignment\n"
                + "    WHERE CourseId = ?\n"
                + ")";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
