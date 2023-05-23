/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import model.Category;

import model.Course;
import model.Level;

/**
 *
 * @author Yui
 */
public class CourseDAO extends MyDAO {
    /**
     * This method searches for courses based on various criteria such as search
     * query, category ID, level ID, duration, page number, and page size. It 
     * retrieves a list of courses that match the specified criteria.
     * @param searchQuery The search query used to filter courses by their title.
     * @param categoryId The ID of the category to filter courses by. Set to -1 to ignore this filter.
     * @param levelId The ID of the level to filter courses by. Set to -1 to ignore this filter.
     * @param duration The maximum duration of the courses to filter by. Specify "00:00:00.00" to ignore this filter.
     * @param page The page number of the results to retrieve.
     * @param pageSize The number of courses per page.
     * @return A list of Course objects that match the specified criteria.
     * @throws SQLException If an error occurs while executing the SQL query
     */
    public List<Course> searchCourses(String searchQuery, int categoryId, int levelId, Time duration, int page, int pageSize) throws SQLException {
        xSql = "select * from Courses c "
                + "inner join Categories cat on c.CategoryID = cat.CategoryID "
                + "inner join Levels l on c.LevelID = l.LevelID "
                + "where (N'' = ? or c.Title like ?) "
                + "and (-1 = ? or c.CategoryID = ?) "
                + "and (-1 = ? or c.LevelID = ?) "
                + "and ('00:00:00.00' = ? or c.Duration < ?) "
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
        ps.setTime(7, duration);
        ps.setTime(8, duration);
        ps.setInt(9, offset);
        ps.setInt(10, pageSize);
        rs = ps.executeQuery();
        
        List<Course> results = new ArrayList<>();
        while(rs.next()) {
            Level level = new Level(rs.getInt("LevelID"), rs.getString("LevelDescription"));
            Category cat = new Category(rs.getInt("CategoryID"), rs.getString("CategoryDescription"));
            Course c = new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getString("CourseDescription"), rs.getString("CourseBannerImage"), rs.getDate("PublishDate"), rs.getString("Lecturer"), level, cat, rs.getTime("Duration"));
            results.add(c);
        }
        
        
        return results;
    }
}

