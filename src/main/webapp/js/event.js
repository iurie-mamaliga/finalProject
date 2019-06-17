$(document).ready(function() {


	/*/DATE TIME SELECTOR
        $('#datetimepicker6').datetimepicker();
        $('#datetimepicker7').datetimepicker({
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });

*/
	
//	$("#fileUploadButton").on("click", upload);
	$("#showAllEvents").on("click", showAll);
	$("#showUpcoming").on("click", filter);
	$("#editEvent").on("click", clickShowEdit);
	$("#newEvent").on("click", clickShowNew);
	var table = $("#editingEvent");
	var mand = $("#mandatory").parent().prev();
	//var table = $("#event_name").parent().prev();
	//table.on("click", showFormEdit);
	table.on("click", "td", showFormEdit);
	mand.on("click", showMand);
	
	function clickShowNew(e){
		$(".editEvent h2").text("ADDING NEW EVENT");
		$("#event_name").show();
		$("#description").show();
		$("#start_date").show();
		$("#end_date").show();
		$("#mandatory").show();
		
		return $(".editEvent").show();
	}
	
	function clickShowEdit(e){
		if($(".form-check-input").is(':checked')){
		//$("#editingEvent tr td:first-child").css({	'border' : '1px solid blue', 'padding' : '20px' });	
		//$("#editingEvent tr td:first").css({	'border' : 'none', 'padding' : 'none' });	
			var tr = $(".form-check-input:checked").val();
			var tr = "#" + tr;
			$(".editEvent h2").text("EVENT SELECTED:  " + $(tr).text());
			$("#event_name").hide();
			$("#description").hide();
			$("#start_date").hide();
			$("#end_date").hide();
			$("#mandatory").hide();
			return $(".editEvent").show();
			
		}else {
			return alert("Can't edit if you don't select an event!");
		}
		
	
	}
	
	function showFormEdit(e){
		$(this).next().find('input').show();
	}

	function showMand(e){
		$(this).next().find('select').show();
	}
	
	function filter(e){
		var rows = $(".showEvents tr");
		var now = new Date;
		for(var i = 0; i < rows.length; i++){
			var event = new Date(rows[i].id);
			if(now > event){
				rows.eq(i).hide();
			}
		}
	}
	
	function showAll(e){
		
		var rows = $(".showEvents tr");
		for(var i = 0; i < rows.length; i++){
			rows.eq(i).show();
		}
	
		
	}
	
	
	
});