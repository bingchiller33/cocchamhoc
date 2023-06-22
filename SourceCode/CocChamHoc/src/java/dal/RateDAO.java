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

    public void insertRatings(int cId, int uId, int rateNo, String review) {
        if ((rateNo < 1 || rateNo > 5)) {
            return;
        }
        try {
            xSql = "insert into Ratings(UserID, CourseID, Rating, Review) values (?, ?, ?, 4)";
            ps = con.prepareCall(xSql);
            ps.setInt(1, uId);
            ps.setInt(2, cId);
            ps.setInt(3, rateNo);
            ps.setString(4, review);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getQuantityReviewCorse(int cid) {
        try {
            xSql = "select count(*) countReview \n"
                    + "from Ratings \n"
                    + "where CourseId = / and Review is not null";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getUserRateNo(int cId, int uId) {
        try {
            xSql = "select Rating from Ratings where UserID = ? and courseID = ?";
            ps = con.prepareCall(xSql);
            ps.setInt(1, uId);
            ps.setInt(2, cId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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

    public int getQuantityRateNo(int cId) {
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

    public double rate(int count, int sum) {
        if (count != 0) {
            return sum / count;
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

    public void updateReview(int cId, int uId, String review) {
        if (review.isEmpty()) {
            return;
        }
        try {
            xSql = "update Ratings "
                    + "set Review = ? \n"
                    + "where UserID = ? and CourseID = ?";
            ps = con.prepareCall(xSql);
            ps.setString(1, review);
            ps.setInt(2, uId);
            ps.setInt(3, cId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getTitleCourse (int cId) {
        try {
            xSql = "select Title from Courses where CourseID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
            ps.execute();
            ps.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
