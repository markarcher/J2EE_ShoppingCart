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
&nbsp;&nbsp;<a href="LogOut">Log Out</a>
<p>Add Products</p>

<p>go to  <a href = "AdminHome">Admin Home</a></p><br>
<p>Add Product</p>

	<Table BORDER="1" CELLSPACING="1" CELLPADDING="1" width="10%">
		<tr><td>
			<FORM ACTION="UpdateProduct" method="get">
  			Product Name: <INPUT TYPE="TEXT" NAME="productName" /><BR>
  			Quantity	  : <INPUT TYPE="TEXT" NAME="quantity" pattern="[0-9]+"/><BR>
  			Price		  : <INPUT TYPE="TEXT" NAME="price" pattern="[0-9]+"/><BR>
    		<p>Choose Category</p>
   				<c:forEach var="item" items="${categoryList}">
						&nbsp;&nbsp;<INPUT TYPE="RADIO" NAME="categoryName"
                    	 VALUE="${item.getCategoryName()}">${item.getCategoryName()}<BR>
				</c:forEach>
		 	</BR> <INPUT TYPE="SUBMIT" value="add">
			</FORM>
	</td></tr>
	</Table>

	<Table BORDER="1" CELLSPACING="1" CELLPADDING="1" width="10%">
		<tr><th>Product Name</th><th>Price</th><th>Quantity</th></tr>
			<c:forEach var="item" items="${productList}"> 
 		<col width="100">
  		<col width="50">
 		<tr>
			<td>${item.getProductName()}</td>
			<td>${item.getPrice()}</td>
			<td>${item.getQuantity()}</td>
			
		</tr>
			</c:forEach>
 	</Table>
<br>
<br>


</body>
</html>