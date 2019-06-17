<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">

<div class="panel panel-info">
	<div class="panel-heading">
		<h2 class="panel-title">Matchmaking Companies</h2>
	</div>

	<table class="table table-sm">
				<thead>
					<tr>
						<th scope="col">Company Name</th>
						<th scope="col">Position Available</th>
						<th scope="col">Representatives</th>
						<th scope="col">Emails</th>
						
					</tr>
				</thead>
				<tbody>
				
				<c:set var="nextCompId" value="-1" />
				
					<c:forEach items="${allCompanies}" var="company">
						<c:if test="${company.companyId != prevCompId }">
							<tr>
								<td><strong>${company.companyName}</strong></td>
								<td>${company.positionAvailable}</td>
								<td>${company.repName}</td>
								<td>${company.repEmail}</td>	
							</tr>
						</c:if>
			
						<c:set var="nextCompId" value="${company.companyId}" />				
					</c:forEach>
				</tbody>
			</table>

</div>




	</div>
	<div class="col-sm-2"></div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />