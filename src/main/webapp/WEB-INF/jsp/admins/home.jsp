<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-12">
	
		<div class="page-header">
			<h1>Admin Dashboard</h1>
		</div>

		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Manage Users</h3>
			</div>
			<c:url var="formAction" value="/changeUserRole" />
			<form action="${formAction}" method="post">
				<table class="table table-sm table-striped" style="margin-bottom:0;">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">Username</th>
							<th score="col">First Name</th>
							<th score="col">Last Name</th>
							<th score="col">Email Address</th>
							<th scope="col">Phone Number</th>
							<th scope="col">Role</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allUsers}" var="siteUser">
							<tr>
								<td>
									<c:if
										test="${siteUser.userName != currentUser.userName}">
										<input class="form-check-input" type="radio" name="userName"
											value="${siteUser.userName}">
									</c:if>
								</td>
								<td>${siteUser.userName}</td>
								<td>${siteUser.firstName}</td>
								<td>${siteUser.lastName}</td>
								<td>${siteUser.email}</td>
								<td>${siteUser.phoneNumber}</td>
								<td>${siteUser.role}</td>
								<td><c:if
										test="${siteUser.userName != currentUser.userName}">
										<c:url var="deleteUserHref"
											value="/deleteUser?userName=${siteUser.userName}" />
										<a href="${deleteUserHref}" class="btn btn-danger btn-sm">Delete</a>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="panel-footer">
					<div class="form-inline">
						<select name="role" class="form-control" id="changeUserRole">
							<option value="guest">Guest</option>
							<option value="student">Student</option>
							<option value="admin">Administrator</option>
						</select> <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
						<button type="submit" class="btn btn-primary">Change Role</button>
					</div>
				</div>
			</form>
		</div>




	</div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />