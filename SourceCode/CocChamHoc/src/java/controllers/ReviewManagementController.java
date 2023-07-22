/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.RateDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Rate;
import utils.ParseUtils;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ReviewManagement", urlPatterns = {"/admin/reviews"})
public class ReviewManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = ParseUtils.parseIntWithDefault(request.getParameter("page"), 1 - 1);
        displayRatingList(request, page);
        request.getRequestDispatcher("/admin/reviewManagement.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        int ratingId = Integer.parseInt(request.getParameter("ratingId"));

        RateDAO rateDao = new RateDAO();
        if ("/admin/approveReport".equals(action)) {
            if (rateDao.deleteRatingById(ratingId)) {
                request.getSession().setAttribute("DeleteMSG", "Deleted succesfully!");
            }
        } else if ("/admin/declineReport".equals(action)) {
            if (rateDao.updateIsReportedById(ratingId)) {
                request.getSession().setAttribute("UpdateMSG", "Updated succesfully!");
            }
        }

        int page = ParseUtils.parseIntWithDefault(request.getParameter("page"), 1 - 1);
        displayRatingList(request, page);

        response.sendRedirect(request.getContextPath() + "/admin/reviews");
    }

    private void displayRatingList(HttpServletRequest request, int page) {
        int size = 5;

        RateDAO rateDao = new RateDAO();

        List<Rate> rateList = rateDao.getReportedList(page, size);

        int totalCount = rateDao.getQuantityReport();
        int pageCount = (int) Math.ceil((double) totalCount / size);

        request.setAttribute("pageCount", pageCount);
        request.setAttribute("listCount", size);
        request.setAttribute("reviews", rateList);
    }

}
