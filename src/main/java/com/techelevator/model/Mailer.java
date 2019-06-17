package com.techelevator.model;
import com.sendgrid.*;
import java.io.IOException;

public class Mailer {
	
	private String apiKey = "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";
	private String userName;
	private String userEmail;
	private String subjectLine;
	private String messageBody;
	private String resetKey;
	private String resetUrl;
	
  public Mailer(String userName, String userEmail) {
	  this.userName = userName;
	  this.userEmail = userEmail;
  }
  
  public void passwordResetTemplate(String resetKey) {
	  this.resetKey = resetKey;
	  this.resetUrl = "http://localhost:8080/final-capstone/resetPassword?userName=" + this.userName + "&resetKey=" + this.resetKey;
	  this.subjectLine = "[Teai] Reset your password";
	  this.messageBody = "Use this link to reset your password: " + this.resetUrl;
	  
  }
  
  public void send() throws IOException {	  
    Email from = new Email("test@example.com");
    String subject = subjectLine;
    Email to = new Email(userEmail);
    
    Content content = new Content("text/plain", messageBody);
    Mail mail = new Mail(from, subject, to, content);

//    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    SendGrid sg = new SendGrid(apiKey);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw ex;
    }
  }
}