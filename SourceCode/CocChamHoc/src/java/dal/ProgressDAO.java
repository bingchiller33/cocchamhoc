/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
/**
 *
 * @author LAPTOP
 */
public class ProgressDAO extends MyDAO {
    
    public static void main(String[] args) throws SQLException {
        ProgressDAO dao = new ProgressDAO();
        System.out.println(dao.getLessonProgress(3, 1, 3));
    }

    public boolean getLessonProgress(int chapterId ,int lessonId, int userId) throws SQLException {
        xSql = "select * from Progress where UserID = ? and LessonNumber = ? and ChapterID = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ps.setInt(3, chapterId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("State");
            } else {
                
                 xSql = "INSERT [dbo].[Progress] ([ChapterID], [LessonNumber], [UserID] , [State]) VALUES (?, ?, ?,?)";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, chapterId);
            ps.setInt(2, lessonId);
            ps.setInt(3, userId);
            ps.setBoolean(4, false);
            ps.execute();
            return false;
            }
       
               
    }

    public boolean setLessonProgress(int chapterId, int lessonId, int userId, boolean isComplete) throws SQLException {
        getLessonProgress(chapterId, lessonId, userId);
        xSql = "update Progress set State=? where UserID = ? and LessonNumber = ? and ChapterID = ?";
        
            ps = con.prepareStatement(xSql);
            ps.setBoolean(1, isComplete);
            ps.setInt(2, chapterId);
            ps.setInt(3, lessonId);
            ps.setInt(4, userId);
            ps.executeUpdate();
            
          
            
        return true;
    }


}
