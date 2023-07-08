/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.Chapter;
import model.Lesson;

/**
 *
 * @author Yui
 */
public class LessonDAO extends MyDAO {

    public List<Lesson> getChapterLesson(int chapterId) throws SQLException {
        xSql = "select * from Chapters c "
                + "inner join Lessons l on c.ChapterID = l.ChapterID "
                + "where c.ChapterID = ? "
                + "order by l.LessonNumber asc";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, chapterId);
        rs = ps.executeQuery();
        return fromResultSetMultiple(rs);
    }

    public int getNextLessonNumber(int chapterId) throws SQLException {
        xSql = "select LessonNumber from Lessons where ChapterId = ?  "
                + "order by LessonNumber desc "
                + "offset 0 row "
                + "fetch first 1 rows only ";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, chapterId);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("LessonNumber") + 1;
        }

        return 1;
    }

    public Lesson getPrevLesson(int courseId, int chapterNumber, int lessonNumber) throws SQLException {
        xSql = "select * from Chapters c inner join Lessons l on c.ChapterID = l.ChapterID\n"
                + "where c.CourseID = ?\n"
                + "and (c.ChapterNumber = ? and l.LessonNumber < ?) or (c.ChapterNumber < ?)\n"
                + "order by l.ChapterID desc, l.LessonNumber desc\n"
                + "offset 0 row fetch next 1 row only";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseId);
        ps.setInt(2, chapterNumber);
        ps.setInt(3, lessonNumber);
        ps.setInt(4, chapterNumber);

        rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        }

        return null;
    }

    public Lesson getNextLesson(int courseId, int chapterNumber, int lessonNumber) throws SQLException {
        xSql = "select * from Chapters c inner join Lessons l on c.ChapterID = l.ChapterID\n"
                + "where c.CourseID = ? \n"
                + "and (c.ChapterNumber = ? and l.LessonNumber > ?) or (c.ChapterNumber > ?)\n"
                + "order by l.ChapterID asc, l.LessonNumber asc\n"
                + "offset 0 row fetch next 1 row only\n";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, courseId);
        ps.setInt(2, chapterNumber);
        ps.setInt(3, lessonNumber);
        ps.setInt(4, chapterNumber);

        rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        }

        return null;
    }

    public int createDefaultLesson(int chapterId) throws SQLException {
        int nextNumber = getNextLessonNumber(chapterId);
        xSql = "INSERT INTO Lessons (LessonNumber, ChapterID, LessonName, LessonVideo) "
                + "VALUES (?, ?, ?, 'https://www.youtube.com/watch?v=dQw4w9WgXcQ') ";

        ps = con.prepareStatement(xSql);
        ps.setInt(1, nextNumber);
        ps.setInt(2, chapterId);
        ps.setString(3, "Untitled Lesson " + nextNumber);
        ps.execute();

        return nextNumber;
    }

    public void updateLesson(int chapterId, int lessonNumber, String name, String video, String description) throws SQLException {
        xSql = "update Lessons set LessonName = ?, LessonVideo = ?, LessonDescription = ? where ChapterID = ? and LessonNumber = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, name);
        ps.setString(2, video);
        ps.setString(3, description);
        ps.setInt(4, chapterId);
        ps.setInt(5, lessonNumber);

        ps.execute();
    }

    public void reorderLesson(int chapterId, int lessonNumber, int prevLessonNumber) throws SQLException {
        updateLessonNumber(chapterId, lessonNumber, -1);

        for (int i = lessonNumber - 1; i >= prevLessonNumber + 1; i--) {
            updateLessonNumber(chapterId, i, i + 1);
        }
        for (int i = lessonNumber; i <= prevLessonNumber; i++) {
            updateLessonNumber(chapterId, i, i - 1);
        }

        int newNum = lessonNumber > prevLessonNumber ? prevLessonNumber + 1 : prevLessonNumber;

        updateLessonNumber(chapterId, -1, newNum);
    }

    private void updateLessonNumber(int chapterId, int lessonNumber, int newLessonNumber) throws SQLException {
        xSql = "update Lessons set LessonNumber = ? where ChapterID = ? and lessonNumber = ? ";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, newLessonNumber);
        ps.setInt(2, chapterId);
        ps.setInt(3, lessonNumber);

        ps.execute();
    }

    public void deleteLession(int chapterId, int lessonNumber) throws SQLException {
        xSql = "delete Lessons where ChapterId = ? and LessonNumber = ?";
        ps = con.prepareStatement(xSql);
        ps.setInt(1, chapterId);
        ps.setInt(2, lessonNumber);

        ps.execute();

        List<Lesson> lessons = getChapterLesson(chapterId);
        for (Lesson l : lessons) {
            if (l.getLessonNumber() > lessonNumber) {
                updateLessonNumber(chapterId, l.getLessonNumber(), l.getLessonNumber() - 1);
            }
        }
    }

    public List<Lesson> findLessons(Map<Chapter, List<Lesson>> map, int chapterId) {
        for (Entry<Chapter, List<Lesson>> entry : map.entrySet()) {
            if (entry.getKey().getId() == chapterId) {
                return entry.getValue();
            }
        }

        return new ArrayList<>();
    }

    public Lesson findLesson(List<Lesson> lessons, int lessonNumber) {
        for (Lesson l : lessons) {
            if (l.getLessonNumber() == lessonNumber) {
                return l;
            }
        }
        return null;
    }

    public Lesson findPrevLesson(List<Lesson> lessons, Lesson lesson) {
        Lesson rs = null;
        for (Lesson l : lessons) {
            if (lesson == l) {
                continue;
            }

            int rsNum = rs == null ? -1 : rs.getLessonNumber();
            if (rsNum < l.getLessonNumber() && l.getLessonNumber() < lesson.getLessonNumber()) {
                rs = l;
            }
        }

        return rs;
    }

    public Lesson findNextLesson(List<Lesson> lessons, Lesson lesson) {
        Lesson rs = null;
        for (Lesson l : lessons) {
            if (lesson == l) {
                continue;
            }

            int rsNum = rs == null ? -1 : rs.getLessonNumber();
            if (rsNum > l.getLessonNumber() && l.getLessonNumber() > lesson.getLessonNumber()) {
                rs = l;
            }
        }

        return rs;
    }

    private Lesson fromResultSet(ResultSet rs) throws SQLException {
        return new Lesson(rs.getInt("ChapterID"), rs.getInt("LessonNumber"), rs.getString("LessonName"), rs.getString("LessonDescription"), rs.getString("LessonVideo"));
    }

    private List<Lesson> fromResultSetMultiple(ResultSet rs) throws SQLException {
        ArrayList<Lesson> result = new ArrayList<>();
        while (rs.next()) {
            result.add(fromResultSet(rs));
        }

        return result;
    }

    public List<Lesson> getLessonData(int courseId) throws SQLException {
        ArrayList<Lesson> lessons = new ArrayList<>();
        xSql = "SELECT *\n"
                + "FROM Lessons l\n"
                + "JOIN Chapters c ON l.ChapterID = c.ChapterID\n"
                + "JOIN Courses cr ON c.CourseID = cr.CourseID\n"
                + "WHERE cr.CourseID = ?\n"
                + "ORDER BY c.ChapterID ASC, l.LessonNumber;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson(
                        rs.getInt("ChapterID"),
                        rs.getInt("LessonNumber"),
                        rs.getString("LessonName"),
                        rs.getString("LessonDescription"),
                        rs.getString("LessonVideo"));
                lessons.add(l);
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }
}
