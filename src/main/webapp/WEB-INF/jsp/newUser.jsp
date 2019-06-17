<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<script type="text/javascript">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("form").validate({
			
			rules: {
				firstName: {
					required: true,
					onlyAlpha: true,
		        	minlength: 2,
		        	maxlength: 36,
				},
				lastName: {
					required: true,
					onlyAlpha: true,
		        	minlength: 2,
		        	maxlength: 36,
				},
				email: {
					required: true,
					email: true,
				},
				userName: {
					required: true,
				},
				phoneNumber: {
					required: true,
					phoneUS: true,
				},
				password: {
					required: true,
					minlength: 15,
					capitals: true,
				},
				confirmPassword: {
					required: true,		
					equalTo: "#password" , 
				}
			},
			messages: {			
				password: {
					minlength: "Password too short, make it at least 15 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword: {
					equalTo: "Passwords do not match"
				},
				firstName: {
					required: "First name is required"
				},
				lastName: {
					required: "Last name is required"
				},
				email: {
					required: "A valid email is required"
				}
			},
			errorClass: "error"
		});
	});
</script>

<c:url var="formAction" value="/users" />
<form method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<div class="form-group">
				<label for="firstName">First Name: </label>
				<input type="text" id="firstName" name="firstName" placeHolder="First Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="lastName">Last Name: </label>
				<input type="text" id="lastName" name="lastName" placeHolder="Last Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="email">E-mail: </label>
				<input type="text" id="email" name="email" placeHolder="A valid e-mail address" class="form-control" />
			</div>
				<div class="form-group">
				<label for="phoneNumber">Phone Number: </label>
				<input type="text" id="phoneNumber" name="phoneNumber" placeHolder="Phone Number" class="form-control" />
			</div>
			<div class="form-group">
				<label for="userName">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password: </label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
			</div>
			<button type="submit" class="btn btn-primary">Create User</button>
		</div>
		<div class="col-sm-4"></div>
	</div>
</form>
		
<c:import url="/WEB-INF/jsp/common/footer.jsp" />