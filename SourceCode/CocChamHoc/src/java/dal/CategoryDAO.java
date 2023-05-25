/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
            int categoryId = rs.getInt("CategoryID");
            String description = rs.getString("CategoryDescription");
            Category category = new Category(categoryId, description);
            categories.add(category);
        }

        return categories;
    }
}
