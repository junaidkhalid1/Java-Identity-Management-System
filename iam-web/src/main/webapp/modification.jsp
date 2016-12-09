<%@page import="fr.jkh.iamcore.datamodel.Identity"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>

<% Collection<Identity> idLists = (Collection<Identity>)session.getAttribute("idLists");

  session.removeAttribute("idLists");
%>
<head>
<link xmlns="http://www.w3.org/1999/xhtml" rel="stylesheet"
	href="css/bootstrap.min.css" />
</head>

					   <%
					   if (idLists == null || idLists.isEmpty()){%>
						  <tr>
						      <td colspan="4">No result</td>
						  </tr>
						   
					   <% } else{
					   for (Identity id : idLists){ %>	
<body>
		<div class="container">
		<div>
			<div class="jumbotron">
				<div class="container">
					<h1 class="text-info">Identity Modification</h1>
					<a href="search.jsp">back</a>
				</div>
			</div>
	<div class="container">
		<div xmlns="http://www.w3.org/1999/xhtml" class="bs-example">
			<form role="form" method="post" action="IdUpdate">
				<div class="form-group">
					<label for="exampleInputEmail1">Display Name</label> 
					<input name="displayName" value="<%=id.getDisplayName()%>" type="text" class="form-control" id="exampleInputEmail1"
						placeholder="Enter Display name" />
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Email</label> 
					<input name="email" value="<%=id.getEmail()%>" type="text" class="form-control" id="exampleInputEmail1"
						placeholder="Enter Email" readonly="readonly" />
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">UID</label> 
					<input name="uid" value="<%=id.getUid()%>" type="text" class="form-control" id="exampleInputEmail1"
						placeholder="Enter UID" />
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">BirthDate</label> 
					<input name="birthDate" value="<%=id.getBirthDate()%>" type="date" class="form-control" id="exampleInputEmail1"
						placeholder="Enter Birthdate in this format-> yyyy-MM-dd" />
				</div>
				

				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
	</div>
	</div>
	</div>
</body>
							
							
						</tr>
                        <%} 
                        }%>
                       

</html>
