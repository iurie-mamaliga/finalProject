<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div class="page-header">
			<h2>Welcome!</h2>
		</div>

		<div>${quote.getQuote()}</div>
		<div style="font-style:italic;">${quote.getQuote_source()}</div>

		

	</div>
	<div class="col-sm-2"></div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />