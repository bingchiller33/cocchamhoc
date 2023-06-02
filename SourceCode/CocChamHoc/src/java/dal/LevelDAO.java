/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Level;

/**
 *
 * @author Yui
 */
public class LevelDAO extends MyDAO {

    /**
     * Retrieves all levels from the database.
     *
     * @return A list of all levels.
     * @throws SQLException If a database access error occurs.
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
    
    
     public void createLevel(String description) throws SQLException {
        xSql = "INSERT INTO Levels(LevelDescription) values(?)";
        ps = con.prepareStatement(xSql);
        ps.setString(1, description);
        ps.execute();
    }

    public Level getDefaultLevel() throws SQLException {
        xSql = "select * from Levels "
                + "order by LevelId asc "
                + "offset 0 row "
                + "fetch next 1 row only";

        ps = con.prepareStatement(xSql);
        rs = ps.executeQuery();

        if (rs.next()) {
            return fromResultSet(rs);
        } else {
            createLevel("default");
            return getDefaultLevel();
        }
    }
    
    public Level fromDescription(String desc) throws SQLException {
        xSql = "select * from Levels where LevelDescription = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, desc);
        rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        } else {
            createLevel(desc);
            return fromDescription(desc);
        }
    }

    private Level fromResultSet(ResultSet rs) throws SQLException {
        int levelId = rs.getInt("LevelId");
        String description = rs.getString("LevelDescription");
        Level level = new Level(levelId, description);
        
        return level;
    }

    public void createLevel(String level) {
        xSql = "INSERT INTO dbo.Levels\n"
                + "(\n"
                + "    LevelDescription\n"
                + ")\n"
                + "VALUES\n"
                + "(? -- LevelDescription - nvarchar(69)\n"
                + "    )";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, level);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
