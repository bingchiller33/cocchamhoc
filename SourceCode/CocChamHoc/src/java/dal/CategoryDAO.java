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

/**
 *
 * @author Yui
 */
public class CategoryDAO extends MyDAO {

    /**
     * Retrieves all categories from the database.
     *
     * @return A list of all categories.
     * @throws SQLException If a database access error occurs.
     */
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        xSql = "SELECT * FROM Categories";
        ps = con.prepareStatement(xSql);
        rs = ps.executeQuery();
        while (rs.next()) {
            categories.add(fromResultSet(rs));
        }

        return categories;
    }

    public void createCategory(String description) throws SQLException {
        xSql = "INSERT INTO Categories(CategoryDescription) values(?)";
        ps = con.prepareStatement(xSql);
        ps.setString(1, description);
        ps.execute();
    }

    public Category getDefaultCategory() throws SQLException {
        xSql = "select * from Categories "
                + "order by CategoryID asc "
                + "offset 0 row "
                + "fetch next 1 row only";

        ps = con.prepareStatement(xSql);
        rs = ps.executeQuery();

        if (rs.next()) {
            return fromResultSet(rs);
        } else {
            createCategory("default");
            return getDefaultCategory();
        }
    }
    
    public Category fromDescription(String desc) throws SQLException {
        xSql = "select * from Categories where CategoryDescription = ?";
        ps = con.prepareStatement(xSql);
        ps.setString(1, desc);
        rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        } else {
            createCategory(desc);
            return fromDescription(desc);
        }
    }

    private Category fromResultSet(ResultSet rs) throws SQLException {
        int categoryId = rs.getInt("CategoryID");
        String description = rs.getString("CategoryDescription");
        Category category = new Category(categoryId, description);
        
        return category;
    }
}
