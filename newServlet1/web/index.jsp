<%@page import="ibsappline.logic.Model"%>
  Created by IntelliJ IDEA.
  User: rusta
  Date: 19.04.2023
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Home page</h1>
  Please enter user id(or enter 0 to see all of the users)
  <br/>
  Available:<%
    Model model = Model.getInstance();
    out.print(model.getFromList().size());
  %>
  <form method="get" action="get">
    <label>ID:
  <input type="text" name="id"><br/>
    </label>
    <button type="submit">Search</button>
  </form>

  <a href="addUser.html">Create a new user</a>
  </body>
</html>
