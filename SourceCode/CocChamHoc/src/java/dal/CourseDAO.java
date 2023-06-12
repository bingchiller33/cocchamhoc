/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.ResultSet;
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
            + "and (0 = ? or c.DurationInSeconds > ?) "
            + "and (0 = ? or c.DurationInSeconds <= ?) ";

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
    public List<Course> searchCourses(String searchQuery, int categoryId, int levelId, int durationLow, int durationHigh, int page,
            int pageSize) throws SQLException {
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
        ps.setInt(7, durationLow);
        ps.setInt(8, durationLow);
        ps.setInt(9, durationHigh);
        ps.setInt(10, durationHigh);
        ps.setInt(11, offset);
        ps.setInt(12, pageSize);
        rs = ps.executeQuery();

        List<Course> results = new ArrayList<>();
        while (rs.next()) {
            results.add(fromResultSet(rs));
        }
        return results;
    }

    public ArrayList<Course> getCourse() {
        ArrayList<Course> list = new ArrayList<>();
        xSql = "SELECT * FROM dbo.Courses JOIN dbo.Levels ON Levels.LevelID = Courses.LevelID JOIN dbo.Categories ON Categories.CategoryID = Courses.CategoryID";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level level = new Level(rs.getInt("LevelID"), rs.getString("LevelDescription"));
                Category cat = new Category(rs.getInt("CategoryID"), rs.getString("CategoryDescription"));
                Course c = new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getString("CourseDescription"), rs.getString("CourseBannerImage"), rs.getDate("PublishDate"), rs.getString("Lecturer"), level, cat, rs.getInt("DurationInSeconds"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method get the count of Searches the count of courses based on the
     * provided search query, category ID, level ID, duration,
     *
     * @param searchQuery The search query used to filter courses by their
     * title.
     * @param categoryId The ID of the category to filter courses by. Set to -1
     * to ignore this filter.
     * @param levelId The ID of the level to filter courses by. Set to -1 to
     * ignore this filter.
     * @param durationLow The maximum duration of the courses to filter by. Specify
     * "00:00:00.00" to ignore this filter.
     * @return
     * @throws SQLException
     */
    public int searchCoursesCount(String searchQuery, int categoryId, int levelId, int durationLow, int durationHigh)
            throws SQLException {
        xSql = "select count(*) " + searchCourseQuery;
        ps = con.prepareStatement(xSql);
        ps.setString(1, searchQuery);
        ps.setString(2, "%" + searchQuery + "%");
        ps.setInt(3, categoryId);
        ps.setInt(4, categoryId);
        ps.setInt(5, levelId);
        ps.setInt(6, levelId);
        ps.setInt(7, durationLow);
        ps.setInt(8, durationLow);
        ps.setInt(9, durationHigh);
        ps.setInt(10, durationHigh);
        rs = ps.executeQuery();

        rs.next();
        return rs.getInt(1);
    }

    public Course getCourseById(int id) throws SQLException {
        xSql = "Select * from Courses c "
                + "inner join Categories cat on c.CategoryID = cat.CategoryID "
                + "inner join Levels l on c.LevelID = l.LevelID "
                + " where CourseID = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (!rs.next()) {
            return null;
        }
        return fromResultSet(rs);
    }

    public int createDefaultCourse() throws SQLException {
        CategoryDAO categoryDAO = new CategoryDAO();
        LevelDAO levelDAO = new LevelDAO();

        Category cat = categoryDAO.getDefaultCategory();
        Level lv = levelDAO.getDefaultLevel();

        xSql = "insert into Courses(CourseBannerImage, Title, CourseDescription, Lecturer, DurationInSeconds, LevelID, CategoryID) "
                + "values (?, ?, ?, ?, ?, ?, ?)";

        ps = con.prepareStatement(xSql);
        ps.setString(1, "https://images.unsplash.com/photo-1516397281156-ca07cf9746fc");
        ps.setString(2, "Untitled course");
        ps.setString(3, "Course description");
        ps.setString(4, "Unnamed Lecturer");
        ps.setInt(5, 3600);
        ps.setInt(6, lv.getId());
        ps.setInt(7, cat.getId());
        ps.execute();

        xSql = "select CourseId from Courses order by CourseId desc offset 0 row fetch next 1 row only";
        ps = con.prepareStatement(xSql);
        rs = ps.executeQuery();

        rs.next();
        return rs.getInt("CourseId");
    }

    public void updateCourse(int courseId, String name, int categoryId, int levelId, String lecturer, String imgUrl, String description, Date publishDate) throws SQLException {
        xSql = "update Courses set Title = ?, CategoryID = ?, LevelID = ?, Lecturer = ?, CourseBannerImage = ?, CourseDescription = ?, PublishDate = ? "
                + "where CourseId = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, name);
        ps.setInt(2, categoryId);
        ps.setInt(3, levelId);
        ps.setString(4, lecturer);
        ps.setString(5, imgUrl);
        ps.setString(6, description);
        ps.setDate(7, publishDate);
        ps.setInt(8, courseId);

        ps.execute();
    }

    public void deleteCourse(int courseId) throws SQLException {
        xSql = "delete Courses where courseId = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseId);

        ps.execute();
    }

    public Course fromResultSet(ResultSet rs) throws SQLException {
        // Must join with category and level
        Level level = new Level(rs.getInt("LevelID"), rs.getString("LevelDescription"));
        Category cat = new Category(rs.getInt("CategoryID"), rs.getString("CategoryDescription"));
        Course c = new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getString("CourseDescription"), rs.getString("CourseBannerImage"), rs.getDate("PublishDate"), rs.getString("Lecturer"), level, cat, rs.getInt("DurationInSeconds"));
        return c;
    }
    
    public ArrayList<Course> getNewestCoursesInfo(int limit) {
        ArrayList<Course> courses = new ArrayList<>();
        xSql = "SELECT TOP " + limit +" * "
                + "FROM Courses "
                + "ORDER BY Courses.PublishDate DESC";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Level    level = new Level(rs.getInt(1), rs.getString(2));
                Category cat   = new Category(rs.getInt(1), rs.getString(2));
                Course   c     = new Course(rs.getInt("CourseID"), 
                        rs.getString("Title"), 
                        rs.getString("CourseDescription"), 
                        rs.getString("CourseBannerImage"),
                        rs.getDate("PublishDate"), 
                        rs.getString("Lecturer"), 
                        level, 
                        cat, 
                        rs.getInt("DurationInSeconds"));
                courses.add(c);
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
    
}
