<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Welcome ${User} &nbsp;&nbsp;<a href="LogOut">Log Out</a></p>

<a href="ShowCart">Show cart</a>

<br><p>Choose a category</p>
<br>
<form action="ShowProducts">
	<c:forEach var="item" items="${categoryList}">
		&nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="categoryType"
                     VALUE="${item.getCategoryNo()}">${item.getCategoryName()}<BR>
	</c:forEach>
<INPUT TYPE="SUBMIT" VALUE="Submit">
</form>

</body>
</html>