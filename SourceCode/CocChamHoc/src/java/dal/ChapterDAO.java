/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Chapter;
import model.Lesson;

/**
 *
 * @author Yui
 */
public class ChapterDAO extends MyDAO {

    public List<Chapter> getCourseChapters(int courseId) throws SQLException {
        xSql = "select * from Courses c "
                + "inner join Chapters cp on c.CourseID = cp.CourseID "
                + "where c.CourseID = ?";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseId);
        rs = ps.executeQuery();
        return fromResultSetMultiple(rs);
    }

    public Chapter findChapterById(List<Chapter> chapters, int id) {
        for (Chapter c : chapters) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public Map<Chapter, List<Lesson>> getGroupedLesson(List<Chapter> chapters) throws SQLException {
        LessonDAO ldao = new LessonDAO();
        HashMap<Chapter, List<Lesson>> kv = new HashMap<>();
        for (Chapter c : chapters) {
            kv.put(c, ldao.getChapterLesson(c.getId()));
        }

        return kv;
    }

    private Chapter fromResultSet(ResultSet rs) throws SQLException {
        return new Chapter(rs.getInt("ChapterID"), rs.getInt("ChapterNumber"), rs.getInt("CourseID"), rs.getString("ChapterName"));
    }

    private List<Chapter> fromResultSetMultiple(ResultSet rs) throws SQLException {
        ArrayList<Chapter> result = new ArrayList<>();
        while (rs.next()) {
            result.add(fromResultSet(rs));
        }

        return result;
    }
}
