/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
                rs.getInt(4),
                rs.getDate(5),
                rs.getString(6),
                rs.getBoolean(7));
    }

    public void insertRatings(int cId, int uId, int rateNo, String review) {
        if ((rateNo < 1 || rateNo > 5)) {
            return;
        }
        if (!review.trim().isEmpty()) {
            xSql = "insert into Ratings(UserID, CourseID, Rating, Review) values (?, ?, ?, ?)";
        }
        if (review.trim().isEmpty()) {
            xSql = "insert into Ratings(UserID, CourseID, Rating) values (?, ?, ?)";
        }
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            ps.setInt(2, cId);
            ps.setInt(3, rateNo);
            if (!review.trim().isEmpty()) {
                ps.setString(4, review);
            }
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
                    + "where CourseId = ? and Review is not null";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            if (rs.next()) {
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
            ps = con.prepareStatement(xSql);
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
            ps = con.prepareStatement(xSql);
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
            ps = con.prepareStatement(xSql);
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
            ps = con.prepareStatement(xSql);
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
            ps = con.prepareStatement(xSql);
            ps.setString(1, review);
            ps.setInt(2, uId);
            ps.setInt(3, cId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTime(int cId, int uId) {
        LocalDateTime currentTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(currentTime);
        try {
            xSql = "update Ratings set RateTime = ? where UserID = ? and CourseID = ?";
            ps = con.prepareStatement(xSql);
            ps.setTimestamp(1, timestamp);
            ps.setInt(2, uId);
            ps.setInt(3, cId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitleCourse(int cId) {
        try {
            xSql = "select Title from Courses where CourseID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            ps.execute();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Rate> getReviewRate(int cid, int rateNo, int page, int pageSize) {
        List<Rate> list = new ArrayList<>();
        int offset = page * pageSize;
        try {
            xSql = "select * from Ratings "
                    + "where Review is not null and CourseID = ? and (-1 = ? or Rating = ?) "
                    + "order by RateTime desc offset ? rows fetch next ? rows only";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cid);
            ps.setInt(2, rateNo);
            ps.setInt(3, rateNo);
            ps.setInt(4, offset);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rateResultSet(rs));
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> getCourseId(int uId, int cId) {
        List<Integer> list = new ArrayList<>();
        try {
            xSql = "select courseId from Ratings where UserID = ? and CourseID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uId);
            ps.setInt(2, cId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateReport(int cId, int rId) {
        xSql = "update Ratings set IsReport = 1 where CourseId = ? and RatingID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            ps.setInt(2, rId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRate(int cId, int uId) {
        xSql = "delete Ratings where CourseID = ? and UserId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            ps.setInt(2, uId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getQuantity5(int cId) {
        xSql = "select Count(*) from Ratings where Rating = 5 and CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getQuantity4(int cId) {
        xSql = "select Count(*) from Ratings where Rating = 4 and CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getQuantity3(int cId) {
        xSql = "select Count(*) from Ratings where Rating = 3 and CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getQuantity2(int cId) {
        xSql = "select Count(*) from Ratings where Rating = 2 and CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getQuantity1(int cId) {
        xSql = "select Count(*) from Ratings where Rating = 1 and CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getQuantityAll(int cId) {
        xSql = "select Count(*) from Ratings where CourseID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSizeFilter(int cid, int rateNo) {
        xSql = "select Count(*) from Ratings where Review is not null and CourseID = ? and (-1 = ? or Rating = ?) ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cid);
            ps.setInt(2, rateNo);
            ps.setInt(3, rateNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Rate> getReportedList(int page, int size) {
        List<Rate> list = new ArrayList<>();
        try {
            int offset = (page - 1) * size;
            offset = offset < 0 ? 0 : offset;
            xSql = "SELECT * FROM Ratings WHERE IsReported = 1 ORDER BY RatingID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, offset);
            ps.setInt(2, size);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rateResultSet(rs));

            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getQuantityReport() {
        int quantityReport = 0;
        xSql = "SELECT COUNT(*) AS RecordCount\n"
                + "FROM Ratings\n"
                + "WHERE IsReported = 1";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                quantityReport = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantityReport;
    }

    public boolean deleteRatingById(int ratingId) {
        boolean result = false;
        xSql = "DELETE FROM Ratings WHERE RatingID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ratingId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateIsReportedById(int ratingId) {
        boolean result = false;
        xSql = "UPDATE Ratings SET IsReported = 0 WHERE RatingID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ratingId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
