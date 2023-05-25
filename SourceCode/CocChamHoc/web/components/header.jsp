<%-- 
    Document   : home
    Created on : May 20, 2023, 5:29:26 PM
    Author     : Yui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav>
    <% 
                String search= request.getAttribute("search")+"";	
		search = (search.equals("null"))?"":search;
    %>
    <form action="../home" method="get" id="searchForm">
        Search: <input type="text" name="search" value="<%=search%>"/> <input type="submit" value="Search"></br>
    </form>

</nav>
