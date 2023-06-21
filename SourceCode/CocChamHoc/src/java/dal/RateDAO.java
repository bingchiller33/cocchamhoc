/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Rate;

/**
 *
 * @author LAPTOP
 */
public class RateDAO extends MyDAO {

    public Rate rateResultSet(ResultSet rs) throws SQLException {
        return new Rate(rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getDate(4),
                rs.getString(5));
    }

    public void insertRatings(int cId, int uId, int rateNo) {
        if ((rateNo < 1 || rateNo > 5)) {
            return;
        } 
        try {
            xSql = "insert into Ratings(UserID, CourseID, Rating) values (?, ?, ?)";
            ps = con.prepareCall(xSql);
            ps.setInt(1, uId);
            ps.setInt(2, cId);
            ps.setInt(3, rateNo);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSumRateNo(int cId) {
        int sum = 0;
        try {
            xSql = "select sum(Rating) as number\n"
                    + "from Ratings\n"
                    + "where CourseID = ?\n"
                    + "group by CourseID";
            ps = con.prepareCall(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    public int getCountRateNo(int cId) {
        int count = 0;
        try {
            xSql = "select count(Rating) as number\n"
                    + "from Ratings\n"
                    + "where CourseID = ?\n"
                    + "group by CourseID";
            ps = con.prepareCall(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int rateAvg(int count, int sum) {
        if (count != 0) {
            double avg = sum / count;
            if (avg % 1 == 0) {
                return (int) avg;
            } else {
                double number = avg % 1;
                if (number > 0 && number <= 0.5) {
                    return (int) Math.floor(avg);
                } else if (number > 0.5 && number < 1) {
                    return (int) Math.ceil(avg);
                }
            }
        }
        return 0;
    }

    public void updateRating(int cId, int uId, int rateNo) {
        if ((rateNo < 1 || rateNo > 5)) {
            return;
        } 
        try {
            xSql = "update Ratings"
                    + " set Rating = ? "
                    + "where CourseID = ? and UserID = ?";
            ps = con.prepareCall(xSql);
            ps.setInt(1, rateNo);
            ps.setInt(2, cId);
            ps.setInt(3, uId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
