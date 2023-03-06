<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>

</style>
</head>
<body>
<%@include file="navbar.jsp" %>
<main class="primary-background  banner-background"style="padding-bottom:80px">
<div class="container">
<div class="col-md-6 offset-md-3">
<div class="card">
<div class="card-header primary-background">
<span class="fa fa-user-circle fa-3x "></span>

Register here
</div>
<div class="card-body">
<form id="reg_form" action="register" method="post">
<div class="form-group">
    <label for="user_name">User Name</label>
    <input name="user_name" type="text" class="form-control" id="user_name" aria-describedby="emailHelp" placeholder="Enter name">
    
  </div>
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input name="user_email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  
  
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input name="user_password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
  
  <div class="form-group">
    <label for="gender">Select Gender</label>
    <br>
    <input type="radio"  id="gender" name="gender" value="male">Male
    <input type="radio"  id="gender"name="gender"  value="female">Female
    
  </div>
  <div class="form-group">
  <textarea name="about"class="form-control" id="about"  rows="5" placeholder="something about yourself"></textarea>
  </div>
  <div class="form-check">
    <input name="check" type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">agree terms and condition</label>
  </div>
  <div class="container text-center" id="loader" style="display:none;">
  <span class="fa fa-refresh fa-spin"></span>
<h4>Please wait...</h4>
  </div>
  <button id="sumbimt-btn" type="submit" class="btn btn-primary">Submit</button>
</form>
</div>


</div>
</div>
</div>
</main>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js
"></script>
 <script>
$(document).ready(function(){
	console.log("loaded.....")
	$('#reg_form').on('submit',function(event){
		event.preventDefault();
		let form=new FormData(this);
		$("#sumbimt-btn").hide();
		$("#loader").show();
		$.ajax({
			url:"register",
			type:"post",
			data:form,
			success:function(data,textstatus,jqXHR){
				console.log(data)
				$("#sumbimt-btn").show();
				$("#loader").hide();
			if(data.trim()=='done'){
				swal("register successfully.. we are going to redirect to login page")
				.then((value) => {
				  window.location="login_page.jsp"});
				}else{
					swal(data);
				}
				},
			error:function(jqXHR,textstatus,errorThrow){
				$("#sumbimt-btn").show();
				$("#loader").hide();
				swal("Something went wrong try again")
				.then((value) => {
				  swal(`The returned value is: ${value}`);
				});
			},
			processData:false,
			contentType:false
		});
	})
	
})
</script>
</body>
</html>