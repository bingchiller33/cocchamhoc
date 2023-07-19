<%-- 
    Document   : userManagement
    Created on : May 30, 2023, 9:21:58 AM
    Author     : Quan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utils.*" %>
<c:set var="role" value="${empty param.role ? -1 : param.role}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/components/headCommon.jspf" %>
        <link rel="stylesheet" href="/assets/css/admin.css">
        <title>User management</title>
    </head>
    <body>
        <%@include file="/components/header.jspf" %>
        <div class="article">
            <div class="row">
                <%@include file="/components/adminNavBar.jspf" %>
                <div class="admin-content" style="padding: 1rem">
                    <h1>User Details</h1>
                    <h2 style="margin-top: 1rem">User profile</h2>
                    <form method="POST">
                        <div class="field-list">
                            <label>User ID </label>
                            <input type="text" name="useridprofile" value="${userd.userID}" disabled/>
                            <label>Full name</label>
                            <input type="text" name="fullName" value="${userd.fullName}"/>
                            <label>Email</label>
                            <input type="email" name="email" value="${userd.email}"/>
                            <label>Date of Birth</label>
                            <input type="date" name="dob" value="${userd.dob}"/>
                            <label>Gender</label>
                            <select id="profile-gender" name="gender" class="small-input" value="${userd.gender}">
                                <option value="1" ${userd.gender == 'true' ? 'selected' : ''}>Male</option>
                                <option value="0" ${userd.gender == 'false' ? 'selected' : ''}>Female</option>
                            </select>
                            <label>Phone number</label>
                            <input type="tel" name="phone" value="${userd.phoneNumber}"/>
                        </div>
                        <p style="color: red">${profileStatus}</p>
                        <div>
                            <input class="btn-save" type="submit" name="action" value="Save Profile"/>
                        </div>
                    </form>
                    <form method="POST" action="/admin/userDetail" id="formRevork">
                        <h2 style="margin-top: 1rem">Course Enrolled (${courses.size()})</h2>
                        <table id="user-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Publish date</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="x" items="${courses}">
                                    <tr class="courseRow">
                                        <th class="course_id">${x.id}</th>
                                        <th>${x.title}</th>
                                        <th>${x.publishDate}</th>
                                        <th>${statusMap[x.id]}</th>
                                        <th>
                                            <c:if test="${statusMap[x.id]=='Complete'}" >
                                                <c:forEach var="s" items="${statusCer}">   
                                                    <c:if test="${s.status=='Revork'&& s.courseId == x.id }">
                                                        <span onclick="handleClick(event)" class="btn-del">Revorked</span> 
                                                    </c:if>
                                                    <c:if test="${s.status=='Normal'&& s.courseId == x.id }">
                                                        <span onclick="handleClick(event)" class="btn-del">Revork</span> 
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </th>
                                    </tr> 
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" value="" class="cId" name="cId"/>
                        <input type="hidden" value="${id}" class="id" name="id"/> 
                        <input type="hidden" name="uId" value="${userd.userID}"/>
                    </form>
                    <h2 style="margin-top: 1rem">Role assignment</h2>
                    <form method="POST">
                        <p>Account <strong style="color: red">${userd.email}</strong> is currently <strong style="color: red">${UserUtils.getRoleName(userd.role)}</strong>:</p>
                        <div class="field-list">
                            <label>New Role</label>
                            <select name="role" class="small-input" value="${userd.role}">
                                <option value="1" ${userd.role == 1 ? 'selected' : ''}>User</option>
                                <option value="2" ${userd.role == 2  ? 'selected' : ''}>Lecturer</option>
                                <option value="3" ${userd.role == 3  ? 'selected' : ''}>Admin</option>
                            </select>
                        </div>
                        <p style="color: red">${grantRoleStatus}</p>
                        <div>
                            <input class="btn-save" type="submit" name="action" value="Grant Role"/>
                        </div>
                    </form>    

                    <h2 style="margin-top: 1rem">Restrict User</h2>
                    <em>User will no longer have access to the website if you ban the user. </em>
                    <form method="POST">
                        <div class="field-list">
                            <label>Restrict Until </label>
                            <input type="date" name="banUntil" value="${userd.restrictUntil}"/>
                            <label>Reason</label>
                            <input type="text" name="banReason" value="${userd.restrictReason}"/>
                        </div>
                        <p style="color: red">${restrictStatus}</p>
                        <div>
                            <input class="btn-save" type="submit" name="action" value="Restrict"/>                            
                            <input class="btn-save" type="submit" name="action" value="Appeal"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="/components/footer.jspf" %>
        <<script src="../assets/js/revork.js"></script>
    </body>
    <style>
        #user-table {
            border-collapse: collapse;
            width: 100%;
        }

        #user-table td, #user-table th {
            padding: 1rem;
            border: 1px solid #ddd;
        }

        #user-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #user-table thead {
            background-color: #1C1E53;
            color: white;
        }
    </style> 
</html>
