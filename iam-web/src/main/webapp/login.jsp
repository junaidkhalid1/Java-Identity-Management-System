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
					<h1 class="text-info">Login</h1>
				</div>
			</div>
	<div class="container">
		<div xmlns="http://www.w3.org/1999/xhtml" class="bs-example">
			<form role="form" method="post" action="Login">
				<div class="form-group">
					<label for="exampleInputEmail1">Login</label> 
					<input name="username" type="text" class="form-control" id="exampleInputEmail1"
						placeholder="Enter Login" />
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> 
					<input name="password" type="password" class="form-control" id="exampleInputPassword1"
						placeholder="Password" />
				</div>

				<button type="submit" class="btn btn-default">Login</button>
				<input type="button" class="btn btn-default" value="Register" onclick="window.location='index.jsp'" >
			</form>
		</div>
	</div>
  </div>
</div>
</body>
</html>
