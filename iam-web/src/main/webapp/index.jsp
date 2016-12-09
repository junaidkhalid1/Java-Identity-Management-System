<html>
<head>
<link xmlns="http://www.w3.org/1999/xhtml" rel="stylesheet"
	href="css/bootstrap.min.css" />
</head>

<body>
<div class="container">
		<div>
			<div class="jumbotron">
				<div class="container">
					<h1 class="text-info">Registration</h1>
				</div>
			</div>
	<div class="container">
		<div xmlns="http://www.w3.org/1999/xhtml" class="bs-example">
			<form role="form" method="post" action="Register">
				<div class="form-group">
					<label for="exampleInputUsername">Username</label> 
					<input name="username" type="text" class="form-control" id="exampleInputUsername"
						placeholder="Enter Username" />
				</div>
				<div class="form-group">
					<label for="exampleInputPassword">Password</label> 
					<input name="password" type="password" class="form-control" id="exampleInputPassword"
						placeholder="Password" />
				</div>

				<button type="submit" class="btn btn-default">Register</button>
				<input type="button" class="btn btn-default" value="Login" onclick="window.location='login.jsp'" >
			</form>
		</div>
	  </div>
	</div>
</div>
</body>
</html>
