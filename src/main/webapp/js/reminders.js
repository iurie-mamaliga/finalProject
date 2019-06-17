$(document).ready(setupListeners);

function setupListeners() {
	$(".set-reminder").on("click", queueReminders);	
	
};

function queueReminders(event) {
		let eventId = $(this).attr("data-event-id");
		let userName = $("#currentUser").attr("data-currentusername");

		let sent = {
				eventId: eventId,
				userName: userName
		};
		
		const reminderAPI = "../../setReminder";

		let resp = $.ajax({
			type : "POST",
			dataType : "text",
			url : reminderAPI,
			data : sent
		}).done(
			(resp) => updateWindow(resp, sent)	
		);
		
		$(this).text("Reminder Saved!");
		
}


