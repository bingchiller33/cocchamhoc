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
    <!--<form action="../home" method="get" id="searchForm">
                <div class="input-wrapper">
                    Search:<input type="text" placeholder="Search" name="search" class="input" value="">
                </div> 
        </form>  -->
        <div class ="search"> 
            <form action="../home" method="get"  class="form">
             <label for="search">
                <input  autocomplete="off" placeholder="search your chats" id="search" type="text" name="search" value="<%=search%>">
                <div class="icon">
                    <svg stroke-width="2" stroke="currentColor" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="swap-on">
                        <path d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" stroke-linejoin="round" stroke-linecap="round"></path>
                    </svg>
                    <svg stroke-width="2" stroke="currentColor" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="swap-off">
                        <path d="M10 19l-7-7m0 0l7-7m-7 7h18" stroke-linejoin="round" stroke-linecap="round"></path>
                    </svg>
                </div>
                <button type="reset" class="close-btn">
                    <svg viewBox="0 0 20 20" class="h-5 w-5" xmlns="http://www.w3.org/2000/svg">
                        <path clip-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" fill-rule="evenodd"></path>
                    </svg>
                </button>
             </label>
           </form>
        </div>
</nav>
