<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">


	<section class="jumbotron">
		<div class="container">

			<div class="page-header">
				<h1 class="text-center text-important">
					Teai <small>Automated chat for bootcampers</small>
				</h1>
			</div>

			<h2 calss="text-center">What is Teai?</h2>

			<p>Teai (Tech Elevator Artificial Intelligence) is an interactive
				chatbot developed by Team Exceptional Handlers, a group of students
				from Tech Elevator Pittsburgh as their final capstone project.</p>
			<p>After a user signs up, the program administrator will promote
				their role to either student (or admin), after which the user will
				have access to chat and an internal dashboard.</p>
			<p>Once a user is approved for full access, they can view the
				upcoming events, companies that are visiting for matchmaking events,
				and find technical and non-technical resources.</p>
			<p>The flagship feature of Teai is its chat capability. The user
				can ask questions to Teai and get relevant answers to specific
				questions. If Teai is unable to answer, it will serve results from a
				search engine. Teai will also send notifications to the users,
				regarding upcoming events.</p>
		</div>
	</section>
	<div class="container">
		<section id="team">
			<h3 class="text-center">Team Exceptional Handlers</h3>
			<div class="container">
				<div class="col-md-4 text-center">
					<img src="img/Caitie.png" alt="Product Owner's picture"
						style="width: 100%">
					<h3>Product Owner</h3>
					<p>Caitie Zajko</p>
				</div>
				<div class="col-md-4 text-center">
					<img src="img/TomAnderson.png" alt="Scrum Master's picture"
						style="width: 100%">
					<h3>Scrum Master</h3>
					<p>Tom Anderson</p>
				</div>
				<div class="col-md-4 text-center">
					<img src="img/placeholder.png" alt="Developer's picture"
						style="width: 100%">
					<h3>Developer</h3>
					<p>Adrian Nevarez</p>
				</div>
			</div>
			<div class="container">
				<div class="col-md-4 text-center">
					<img src="img/placeholder.png" alt="Developer's picture"
						style="width: 100%">
					<h3>Developer</h3>
					<p>Chris Rudzki</p>
				</div>
				<div class="col-md-4 text-center">
					<img src="img/IurieMamaliga.png" alt="Developer's picture"
						style="width: 100%">
					<h3>Developer</h3>
					<p>Iurie Mamaliga</p>
				</div>
				<div class="col-md-4 text-center">
					<img src="img/tekrimal.png" alt="Developer's picture"
						style="width: 100%">
					<h3>Developer</h3>
					<p>Tek Rimal</p>
				</div>
			</div>
		</section>
		<!-- /.row -->
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />