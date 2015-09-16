<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
&nbsp;&nbsp;<a href="LogOut">Log Out</a>
<p>Add Category</p>

<p>go to  <a href = "AdminHome">Admin Home</a></p><br>

<form action="AddCategory" method="get">
	<p>Add a Category</p>
	<input type="text" name="catName">
	<input type="submit" name="submit" value="Add">
</form>

${categoryAdded} <br><br>
<Table BORDER="1" CELLSPACING="1" CELLPADDING="1" width="10%">
	<tr><th>Category Name</th></tr>
		<c:forEach var="item" items="${categoryList}"> 
 <col width="100">
 <col width="50">
 	<tr>
		<td>${item.getCategoryName()}</td>
	</tr>
	</c:forEach>
</Table><br>


</body>
</html>