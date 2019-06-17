<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-8">
	<div class="page-header">
		<h2>Chat with Teai</h2>
	</div>
		<div id="chat">
		<figure id="chat-messages" class="highlight">
		</figure>
		<div class="input-group">
			<input type="text" class="form-control" id="message-input" placeholder="How can I help?">
			<span class="input-group-btn">
				<button class="btn btn-primary" id="send-message" type="button">Send</button>
			</span>
		</div>
		</div>
		<!-- /input-group -->
	</div>
	<div class="col-sm-4">
		<h4>A few things you can ask me</h4>
		<ul>
			<li>When's the next event?</li>
			<li>What events are coming up?</li>
			<li>Who's coming to matchmaking?</li>
			<li>What's an abstract class?</li>
		</ul>
	
	</div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />