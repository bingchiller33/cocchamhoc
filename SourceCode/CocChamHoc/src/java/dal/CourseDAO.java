/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Course;
import model.Level;

/**
 *
 * @author Yui
 */
public class CourseDAO extends MyDAO {

    private static final String searchCourseQuery
            = "from Courses c "
            + "inner join Categories cat on c.CategoryID = cat.CategoryID "
            + "inner join Levels l on c.LevelID = l.LevelID "
            + "where (N'' = ? or c.Title like ?) "
            + "and (-1 = ? or c.CategoryID = ?) "
            + "and (-1 = ? or c.LevelID = ?) "
            + "and (0 = ? or c.DurationInSeconds < ?) ";

    /**
     * This method searches for courses based on various criteria such as search
     * query, category ID, level ID, duration, page number, and page size. It
     * retrieves a list of courses that match the specified criteria.
     *
     * @param searchQuery The search query used to filter courses by their
     * title.
     * @param categoryId The ID of the category to filter courses by. Set to -1
     * to ignore this filter.
     * @param levelId The ID of the level to filter courses by. Set to -1 to
     * ignore this filter.
     * @param duration The maximum duration of the courses to filter by. Specify
     * "00:00:00.00" to ignore this filter.
     * @param page The page number of the results to retrieve.
     * @param pageSize The number of courses per page.
     * @return A list of Course objects that match the specified criteria.
     * @throws SQLException If an error occurs while executing the SQL query
     */
    public List<Course> searchCourses(String searchQuery, int categoryId, int levelId, int durationInSeconds, int page, int pageSize) throws SQLException {
        xSql = "select * " + searchCourseQuery
                + "order by c.CourseID "
                + "offset ? rows fetch next ? rows only";

        int offset = page * pageSize;

        ps = con.prepareStatement(xSql);
        ps.setString(1, searchQuery);
        ps.setString(2, "%" + searchQuery + "%");
        ps.setInt(3, categoryId);
        ps.setInt(4, categoryId);
        ps.setInt(5, levelId);
        ps.setInt(6, levelId);
        ps.setInt(7, durationInSeconds);
        ps.setInt(8, durationInSeconds);
        ps.setInt(9, offset);
        ps.setInt(10, pageSize);
        rs = ps.executeQuery();

        List<Course> results = new ArrayList<>();
        while (rs.next()) {
            Level level = new Level(rs.getInt("LevelID"), rs.getString("LevelDescription"));
            Category cat = new Category(rs.getInt("CategoryID"), rs.getString("CategoryDescription"));
            Course c = new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getString("CourseDescription"), rs.getString("CourseBannerImage"), rs.getDate("PublishDate"), rs.getString("Lecturer"), level, cat, rs.getInt("DurationInSeconds"));
            results.add(c);
        }

        return results;
    }

    public int searchCoursesCount(String searchQuery, int categoryId, int levelId, int duration, int page, int pageSize) throws SQLException {
        xSql = "select count(*) " + searchCourseQuery;
        ps = con.prepareStatement(xSql);
        ps.setString(1, searchQuery);
        ps.setString(2, "%" + searchQuery + "%");
        ps.setInt(3, categoryId);
        ps.setInt(4, categoryId);
        ps.setInt(5, levelId);
        ps.setInt(6, levelId);
        ps.setInt(7, duration);
        ps.setInt(8, duration);
        rs = ps.executeQuery();

        rs.next();
        return rs.getInt(1);
    }

    public void createCourse(String imageURL, String title, String courseDescription, Date publishDate, String lecturer, int duration, int levelId, int categoryId) {
        xSql = "INSERT INTO dbo.Courses\n"
                + "(\n"
                + "    CourseBannerImage,\n"
                + "    Title,\n"
                + "    CourseDescription,\n"
                + "    PublishDate,\n"
                + "    Lecturer,\n"
                + "    DurationInSeconds,\n"
                + "    LevelID,\n"
                + "    CategoryID\n"
                + ")\n"
                + "VALUES\n"
                + "(   ?,        -- CourseBannerImage - varchar(420)\n"
                + "    ?,       -- Title - nvarchar(69)\n"
                + "    ?,        -- CourseDescription - text\n"
                + "    ?, -- PublishDate - date\n"
                + "    ?,       -- Lecturer - nvarchar(69)\n"
                + "    ?,         -- DurationInSeconds - int\n"
                + "    ?,         -- LevelID - int\n"
                + "    ?          -- CategoryID - int\n"
                + "    )";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, imageURL);
            ps.setString(2, title);
            ps.setString(3, courseDescription);
            ps.setDate(4, publishDate);
            ps.setString(5, lecturer);
            ps.setInt(6, duration);
            ps.setInt(7, levelId);
            ps.setInt(8, categoryId);
            
            ps.executeQuery();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
