<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Teai : Automated chat for bootcampers</title>
				
		<c:url var="bsHref" value="/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${bsHref}">
		
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "></script>
	    <script src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
   
	    <c:url var="cssHref" value="/site.css" />
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    
	    <c:url var="cssHref" value="/css/site.css" />
		<link rel="stylesheet" type="text/css" href="${cssHref}">
		
		<c:url value="/js" var="jsHref" />
		<script src="${jsHref}/collapse.js"></script>
		<script src="${jsHref}/moment.js"></script>
		<script src="${jsHref}/transition.js"></script>
		<script src="${jsHref}/event.js"></script> 
		<script src="${jsHref}/reminders.js"></script> 
		<script type="text/javascript">
			$(document).ready(function() {
				$("time.timeago").timeago();
				
				$("#logoutLink").click(function(event){
					$("#logoutForm").submit();
				});
				
				var pathname = window.location.pathname;
				$("nav a[href='"+pathname+"']").parent().addClass("active");
				
			});
			
			
		</script>
		<style type="text/css">
			header {
				background-color: #FD4344;
			}
			.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th {
				vertical-align: middle;
			}
			
			.navbar-inverse .navbar-nav>.active>a, .navbar-inverse .navbar-nav>.active>a:focus, .navbar-inverse .navbar-nav>.active>a:hover {
				background-color: #285f8f;
			}
			
			.navbar-inverse .navbar-text {
				padding-left: 15px;
			}
			
			.navbar-inverse .navbar-text,
			.navbar-inverse .navbar-nav>li>a {
				color: white;
			}
			.navbar, .navbar-inverse {
				background-color:#337ab7 !important;
				border: 0 !important;
				border-radius: 0 !important;
			}
			
			.navbar {
				margin-bottom: 0;
			}
		</style>

</head>
<body>
	<header>
		<c:url var="homePageHref" value="/" />
		<c:url var="imgSrc" value="/img/logo.png" />
		<a href="${homePageHref}"><img src="${imgSrc}"
			class="img-responsive" /></a>
	</header>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">

			<ul class="nav navbar-nav">
				<c:url var="homePageHref" value="/" />
				<li><a href="${homePageHref}">Home</a></li>
				<c:if test="${currentUser.role == 'admin' or currentUser.role == 'student'}">
					<c:url var="dashboardHref" value="/users/${currentUser.userName}" />
					<li>
						<c:if test="${currentUser.role == 'admin'}">
							<a href="${dashboardHref}">Admin Dashboard</a>
						</c:if>
						<c:if test="${currentUser.role == 'student'}">
							<a href="${dashboardHref}">Dashboard</a>
						</c:if>
					</li>
					<c:url var="eventsHref"
						value="/users/${currentUser.userName}/events" />
					<li><a href="${eventsHref}">Events</a></li>
					<c:url var="matchmakingHref"
						value="/users/${currentUser.userName}/matchmaking" />
					<li><a href="${matchmakingHref}">Matchmaking</a></li>
					<c:url var="resourcesHref"
						value="/users/${currentUser.userName}/resources" />
					<li><a href="${resourcesHref}">Resources</a></li>
					<c:url var="chatHref" value="/users/${currentUser.userName}/chat" />
					<li><a href="${chatHref}">Chat</a></li>
				</c:if>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty currentUser}">
						<c:url var="newUserHref" value="/users/new" />
						<li><a href="${newUserHref}">Sign Up</a></li>
						<c:url var="loginHref" value="/login" />
						<li><a href="${loginHref}">Log In</a></li>
					</c:when>
					<c:otherwise>
						<c:url var="logoutAction" value="/logout" />
						<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
						</form>
						<c:url var="howdyHref" value="/users/${currentUser.userName}" />
						<li class="navbar-text">Howdy ${currentUser.userName} :)</li>
						<li><a id="logoutLink" href="#">Log Out</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	
	<c:if test="${not empty currentUser}">
		<div id="currentUser" data-currentusername="${currentUser.userName}" style="display:none;"></div>
	</c:if>
	
	<div class="container-fluid">