/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
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
    //
    public int getNextChapterNumber(int courseId) throws SQLException {
           xSql = "select ChapterNumber from Chapters\n"
                   + "where CourseID = ?\n"
                   + "order by ChapterNumber desc\n"
                   + "offset 0 row\n"
                   + "fetch first 1 row only";

           ps = con.prepareStatement(xSql);
           ps.setInt(1, courseId);
           rs = ps.executeQuery();

           if(rs.next()) {
               return rs.getInt("ChapterNumber") + 1;
           }

           return 1;
       }
    public int createDefaulChapter(int courseId) throws SQLException{
        int nextChapter = getNextChapterNumber(courseId);
        xSql = "Insert into Chapters(ChapterNumber,CourseID,ChapterName)\n"
                + "values(?,?,?)";
        ps = con.prepareStatement(xSql);
        ps.setInt(1,nextChapter);
        ps.setInt(2,courseId);
        ps.setString(3,"Untitled Chapter"+ nextChapter);
        ps.execute();        
        return  nextChapter;        
    }
      
    public Chapter getChapterByID(int courseId,int chapterNumber){
           xSql = "select * from Chapters where CourseID = ? and ChapterNumber = ? ";
           try {
            con = new DBContext().connection;
            ps = con.prepareStatement(xSql);
            ps.setInt(1,courseId);
            ps.setInt(2,chapterNumber);
            rs = ps.executeQuery();
               while (rs.next()) {                   
                   return new Chapter(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4));
               }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return null;
    }
   public void updateChapter(String chapterName, int chapterNumber, int courseId) throws SQLException{
          xSql = "update Chapters set ChapterName = ?  where ChapterNumber = ?  and CourseID = ?";
          ps = con.prepareStatement(xSql);
          ps.setString(1,chapterName);
          ps.setInt(2,chapterNumber);
          ps.setInt(3,courseId);
          ps.execute();
   }
   public void deleteChapter(int chapterNumber,int courseId) throws SQLException{
      xSql = "delete Chapters where ChapterNumber = ? and CourseID = ?";
      ps = con.prepareStatement(xSql);
      ps.setInt(1, chapterNumber);
      ps.setInt(2, courseId);
      ps.execute();
   }
   
}
