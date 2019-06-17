$(document).ready(setupListeners);

function setupListeners() {
	$("#send-message").on("click", sendMessage);
	$("#message-input").on("keypress", sendMessage);
};

function sendMessage(event) {
	
	if (event.keyCode == 13 || event.type == "click") {
		
		let userMessage = $("#message-input").val();
		let userName = $("#currentUser").attr("data-currentusername");

		let sent = {
				userName,
				userMessage
		};
		
		const chatAPI = "../../chat/sendUserMessage";

		let resp = $.ajax({
			type : "GET",
			dataType : "text",
			url : chatAPI,
			data : sent
		}).done(
			(resp) => updateWindow(resp, sent)	
		);
		
	}
}

function updateWindow(resp, sent) {
	
	let humanReply = () => {
		$("#chat-messages").append(
			"<pre><span class=\"label label-success\"><strong>"+
			sent.userName+
			"</strong></span> "+
			sent.userMessage+
			"</pre>"
		);
	};
	
	let botReply = () => {
		$("#chat-messages").append(
				"<pre><span class=\"label label-primary\"><strong>"+
				"Teai"+
				"</strong></span> "+
				resp+
				"</pre>"
		);
	};
	
	// clear input box after submission
	$("#message-input").val("");
	
	// delay replies a bit; less jarring than instantly appending them
	setTimeout(humanReply, 150);
	setTimeout(botReply, 600); 
}

