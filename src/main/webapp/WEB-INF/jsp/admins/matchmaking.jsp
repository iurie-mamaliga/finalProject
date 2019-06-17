

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-12">

	<div class="page-header">
		<h1>Admin Dashboard</h1>
	</div>

		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Manage Companies</h3>
			</div>
			<c:url var="formAction" value="/deleteCompany" />
			<form action="${formAction}" method="post">
				<table class="table table-sm table-striped" style="margin-bottom:0;">
					<thead>
						<tr>			
							<th scope="col"></th>			
							<th scope="col">Company Name</th>
							<th scope="col">Position Available</th>
							<th scope="col">Representatives</th>
							<th scope="col">Emails</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${allCompanies}" var="company">
							<tr>
								<td>
									<input class="form-check-input" type="radio" name="rowId" value='${company.companyId}|${company.roleId}|${company.repId}' checked="checked"> </td>	
								<td>${company.companyName}</td>
								<td>${company.positionAvailable}</td>
								<th>${company.repName}</th>
								<th>${company.repEmail}</th>	
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="panel-footer">
					<div class="form-inline">				
				
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
			<button class="btn btn-primary" id="addCompany" name="addCompany" type="button" value="addCompany">Add Company</button>
			<button class="btn btn-primary" id="addRole" name="addRole" type="button" value="addRole">Add Position Available</button>
			<button class="btn btn-primary" id="addRep" name="addRep" type="button" value="addRep">Add Representative</button>
			

					
			<p></p>
			<button class="btn btn-danger" id="delete" name="delete" type="submit" value="deleteCompany">Delete Company</button>
			<button class="btn btn-danger" id="delete" name="delete" type="submit" value="deleteRole">Delete Position Available</button>
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
			<button class="btn btn-danger" id="delete" name="delete" type="submit" value="deleteRep">Delete Representative</button>
			
			</div>
			</div>
			
			<div id="formAddCompany" style="display:none;">
			<label for="companyName">Company Name:</label>
			<input type="text" name="companyName" id="companyName">  
  			<button class="btn btn-primary" name="delete" id="delete" value="saveCompany" type="submit">Save Company</button>
			</div>
			
			<div id="formAddRep" style="display:none;">
			 <b>Representative Name:</b> <input type="text" name="repName" id="repName">
			 <b>Representative Email:</b> <input type="text" name="repEmail" id="repEmail">
  			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
  			<button class="btn btn-primary" name="delete" id="delete" value="saveRep" type="submit">Save Representative</button>
			</div>
			
			<div id="formAddRole" style="display:none;">
			 <b>Job Name:</b> <input type="text" name="roleName" id="roleName">
  			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
  			<button class="btn btn-primary" name="delete" id="delete" value="saveRole" type="submit">Save Job</button>
			</div>
			
			
			
			
			
		</form>
		
			
</div>	
</div>
</div>


<script>
$(document).ready(function(){
    $("#addCompany").click(function(){
        $("#formAddCompany").toggle();
        $("#formAddRep").hide();
        $("#formAddRole").hide();
    });
});
</script>

<script>
$(document).ready(function(){
    $("#addRep").click(function(){
        $("#formAddRep").toggle();
        $("#formAddCompany").hide();
        $("#formAddRole").hide();
    });
});
</script>

<script>
$(document).ready(function(){
    $("#addRole").click(function(){
        $("#formAddRole").toggle();
        $("#formAddCompany").hide();
        $("#formAddRep").hide();
    });
});
</script>





<c:import url="/WEB-INF/jsp/common/footer.jsp" />