/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Level;

/**
 *
 * @author Phuoc
 */
public class LevelDAO extends MyDAO{
       /**
        * 
        * @return
        * @throws SQLException 
        */
    public List<Level> getAllLevels() throws SQLException {
        List<Level> levels = new ArrayList<>();

        xSql = "SELECT LevelID, LevelDescription FROM Levels";

        ps = con.prepareStatement(xSql);
        rs = ps.executeQuery();
        while (rs.next()) {
            int levelId = rs.getInt("LevelID");
            String description = rs.getString("LevelDescription");
            Level level = new Level(levelId, description);
            levels.add(level);
        }

        return levels;
    }
}
