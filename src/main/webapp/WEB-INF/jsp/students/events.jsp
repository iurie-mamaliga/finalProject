<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">

		
<div class="page-header">
		<h2>Student Events</h2>
		</div>
	<div>
	<c:forEach var="event" items="${next7}">
	<div class="card" style="margin-bottom:35px;">
		<div class="card-header" style="font-weight:bold; font-size:20px;">${event.getEvent_name()}</div>
		<div class="card-body" style="margin-left:10px; maring-right:10px;">
		<h6 class="card-title" style="margin-top:15px; font-size:17px; font-weight:normal;">${event.getDescription()}</h6> <!-- set to absolut? --> 
		<p class="card-text">Event starts:		${event.getStartOf()}</p>
		<p class="duration card-text">Duration:		${event.getDuration()}</p>
		
		<c:choose>
		<c:when test="${event.isMandatory() eq true}">
		<p class="card-text">This event is mandatory, please try your best to be there.</p>
		</c:when>
		
		<c:otherwise>
		<p class="card-text">This event isn't mandatory, but we believe it will be great!</p>
		</c:otherwise>
		</c:choose>
		<button data-event-id="${event.event_id}" class="btn btn-primary set-reminder" style="margin-bottom:10px;">Set a reminder!</button>
		</div>
	
	</div>
	
	</c:forEach>
	

	
	</div>
	</div>
	<div class="col-sm-2"></div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />