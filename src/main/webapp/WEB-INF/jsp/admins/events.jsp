<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-12">

		<div class="page-header">
			<h1>
				Admin Events
			</h1>
		</div>
		<div id="topFilter" style="display: inline-block;">
			<div style="display: inline-block; padding-bottom: 15px;">
				<button type="button" class="btn btn-info btn-sm" id="showAllEvents">
					Show All Events
				</button>
				<button type="button" class="btn btn-info btn-sm" id="showUpcoming">
					Show Upcoming Events
				</button>
			</div>
		</div>

		<div class="panel panel-info">
			<div class="panel-heading">
				<h2 class="panel-title">Event Management</h2>
			</div>
			<c:url var="formAction" value="/deleteEvent" />
			
			
			<form action="${formAction}" method="post">
				<table class="table table-sm table-striped showEvents">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col">Event Name</th>
							<th scope="col">Description</th>
							<th scope="col" style="width: 9.50%">Start Date/Hour</th>
							<th scope="col" style="width: 9.50%">End Date/Hour</th>
							<th scope="col">Mandatory</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${upcomingEvents}" var="event">
							<tr id="${event.getStart_date()}">
								<td><input class="form-check-input" type="radio" name="id"
									value="${event.getEvent_id()}"></td>
								<td id="${event.getEvent_id()}">${event.getEvent_name()}</td>
								<td>${event.getDescription()}</td>
								<td>${event.getStart_date()}</td>
								<td>${event.getEnd_date()}</td>
								<td>${event.isMandatory()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
				
							
		<div class="addAndDelete panel-footer"> 
			<button class="btn btn-primary" id="editEvent" value="true" type="button">Edit Event</button>
			<button class="btn btn-success" id="newEvent" value="true" type="reset">Add Event</button>
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
			<button class="btn btn-danger" id="delete" name="delete" type="submit" value="true">Delete Event</button>
		</div>
				
				
			</div>
	</div>
</div>



<!--  action buttons -->

<%-- <div class="row">
	<div class="col-sm-12">
		<div class="addAndDelete">
			<button class="btn btn-primary" id="editEvent" value="true" type="button">Edit Event</button>
			<button class="btn btn-success" id="newEvent" value="true" type="reset">Add Event</button>
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
			<button class="btn btn-danger" id="delete" name="delete" type="submit" value="true">Delete Event</button>
		</div>
	</div>
</div>
	 --%>
<!-- form -->	
	
<div class="row">
	<div class="col-sm-6">
	<div class="editEvent" style="display: none;">
		<div class="panel panel-info panel-default">
		
		
			<div class="panel-heading">
				<h2 class="panel-title">Event Selected:</h2>
			</div>
			
			
			<div id="editingEvent" class="panel-body">
						<div class="form-group">
							<label for="event_name" class="col-sm-2 control-label">Name</label>
							<input type="text" name="event_name" id="event_name"
								class="form-control" />
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-2 control-label">Description</label>
							<input type="text" name="description" id="description"
								class="form-control" />
						</div>
						<div class="form-group">
							<label for="start_date" class="col-sm-2 control-label">Start
								Date/Time</label> <input type="text" name="start_date" id="start_date"
								class="form-control" />YYYY-MM-DD HH:MM:SS

						</div>
						<div class="form-group">
							<label for="end_date" class="col-sm-2 control-label">End
								Date/Time</label> <input type="text" name="end_date" id="end_date"
								class="form-control" />YYYY-MM-DD HH:MM:SS
						</div>
						<div class="form-group">
							<label for="mandatory" class="col-sm-2 control-label">Is
								it mandatory?</label> <select name="mandatory" id="mandatory"
								class="form-control">
								<option value="true">Yes</option>
								<option value="false">No</option>
							</select>
						</div>
						<div class="form-group">

							<button class="btn btn-success" type="submit">Update</button>
							</form>
						</div>
				</div>
					
					
				
					
			</div>
		</div>
	</div>
</div>

<!--  form -->

<c:import url="/WEB-INF/jsp/common/footer.jsp" />