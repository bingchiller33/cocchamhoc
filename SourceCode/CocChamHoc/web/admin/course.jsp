<%-- Document : course Created on : May 30, 2023, 9:45:23 AM Author : Viet --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>JSP Page</title>

            </head>

            <body>
                <table>
                    <form action="/admin/course" method="post">
                        <tr>
                            <td>Banner Image</td>
                            <td><input type="text" name="bannerImage" required></td>
                        </tr>
                        <tr>
                            <td>Course Name</td>
                            <td><input type="text" name="courseName" required></td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td><input type="text" name="description" required></td>
                        </tr>
                        <tr>
                            <td>Publish Date</td>
                            <td><input type="date" name="publishDate" required></td>
                        </tr>
                        <tr>
                            <td>Lecturer</td>
                            <td><input type="text" name="lecturer" required></td>
                        </tr>
                        <tr>
                            <td>Duration</td>
                            <td><input id="duration-input" type="text" name="duration" required
                                    pattern="[0-9]+:[0-9]{2}:[0-9]{2}" value="00:00:00"></td>
                        </tr>
                        <tr>
                            <td>Level</td>
                            <td><select name="levelId">
                                    <c:forEach items="${levelData}" var="level">
                                        <option value="${level.id}">
                                            ${level.description}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Category</td>
                            <td><select name="categoryId">
                                    <c:forEach items="${categoryData}" var="category">
                                        <option value="${category.id}">
                                            ${category.description}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr><td><input type="submit"></td></tr>
                    </form>
                </table>
                <script>
                    {
                        let durationIn = document.getElementById("duration-input");
                        let resultP = document.getElementById("output");

                        durationIn.addEventListener("change", function (e) {
                            resultP.textContent = "";
                            durationIn.checkValidity();
                        });

                        durationIn.addEventListener("invalid", function (e) {
                            resultP.textContent = "Invalid input";
                        });
                    }
                </script>
            </body>

            </html>