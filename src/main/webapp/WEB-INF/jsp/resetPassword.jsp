<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<script type="text/javascript">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("form").validate({
			
			rules: {
				newPassword: {
					required: true,
					minlength: 15,
					capitals: true,
				},
				confirmPassword: {
					required: true,		
					equalTo: "#newPassword"  
				}
			},
			messages: {			
				newPassword: {
					minlength: "Password too short, make it at least 15 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword: {
					equalTo: "Passwords do not match"
				}
			},
			errorClass: "error"
		});
	});
</script>

<div class="page-header">
<h2>Reset Password</h2>
</div>

<c:url var="formAction" value="/submitResetPassword" />
<form method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
		
	<input type="hidden" name="userName" value="${userName}" />
			<input type="hidden" name="resetKey" value="${resetKey}" />
			
			<div class="form-group">
				<label for="password">New Password: </label>
				<input type="password" id="newPassword" name="newPassword" placeHolder="Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password: </label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
			</div>
			<button type="submit" class="btn btn-primary">Update Password</button>
		</div>
		<div class="col-sm-4"></div>
	</div>
</form>
		
<c:import url="/WEB-INF/jsp/common/footer.jsp" />