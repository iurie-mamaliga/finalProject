<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">

<%-- 	<form id="frmSearch" class="search2" method="get" action="" />
		<input class="search2" id="search" type="text" name="serach_bar" size="31" maxlength="255" value="" style="left: 396px; top: 153px; width: 293px; height: 26px;" />
		<input class="search1" type="submit" name="submition" value="Search" style=" padding-  
bottom:20px; left: 691px; top: 153px; height: 23px" />
<input class="search2" type="hidden" name="sitesearch" value="search" />
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
</form> --%>

	<div class="page-header">
		<h2>Student Resources</h2>
	</div>
 
	
		<h3>Here are your resources</h3>
		
    <a href="https://drive.google.com/drive/folders/1KAj-LfiKGAofYyh3oinoy_faBi3sR6B7?usp=sharing" target="_blank"><div class="btn btn-primary btn-lg">Click here to view pathway resources </div></a>
		
	<a href="https://drive.google.com/drive/folders/1Lw5iqjabpOAxmp1tQTLV1AbjsDbO9RAk?usp=sharing" target="_blank"><div class="btn btn-success btn-lg">Click here to view technical resources </div></a>
		

		

	</div>
	<div class="col-sm-2"></div>
</div>
<script type="text/javascript">
    document.getElementById('frmSearch').onsubmit = function() {
        window.location = 'http://localhost:8080/final-capstone/users/studentuser/' + document.getElementById('search').value;
        return false;
    }
</script>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />