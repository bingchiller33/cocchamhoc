<%-- 
    Document   : home
    Created on : May 20, 2023, 5:29:26 PM
    Author     : Yui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav>
    <form action="../search" method="get" id="searchForm">
       Search: <input type="text" name="search"/> <input type="submit" value="Search"></br>
       Category: <a href="../search?cid=-1" name="cat">All</a> 
                 <a href="../search?cid=1" name="cat">Programing</a> 
                 <a href="../search?cid=2" name="cat">Design</a> 
                 <a href="../search?cid=3" name="cat">Digital Marketing</a></br>
       Level: <a href="../search?lid=-1" name="level">All</a> 
              <a href="../search?lid=1" name="level">Beginner</a> 
              <a  href="../search?lid=2" name="level">intermediate</a> 
              <a  href="../search?lid=3"name="level">Advanced</a></br>
       Duration: <a href="../search?did=-1" name="duration">All</a> 
                 <a href="../search?did=15:00:00" name="duration">15 Hour</a> 
                 <a  href="../search?did=3:00:00" name="duration">1-2 Weeks</a> 
                 <a  href="../search?did=3:00:00" name="duration">1-3 Months</a></br>
    </form>

</nav>
