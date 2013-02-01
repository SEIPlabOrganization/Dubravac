package project1;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public boolean send(String to, String sub, String cont) {
	
		final String username = "sptoolriteh@gmail.com";
		final String password = "qwerqwer12";
	
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
	
		try {
	
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("SPtoolRiteh@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(sub);
			message.setContent(cont, "text/html");
	
			Transport.send(message);
	
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
