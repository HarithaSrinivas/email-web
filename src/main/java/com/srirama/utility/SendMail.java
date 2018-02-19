package com.srirama.utility;

import java.io.IOException;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class SendMail {

	@Autowired
	private AppConstants objAppConstants;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(objAppConstants.getEMAIL_KID_SMTPSERVER());
		mailSender.setPort(Integer.valueOf(objAppConstants.getEMAIL_KID_SMTPPORT()));
		mailSender.setUsername(objAppConstants.getEmailUserName());
		mailSender.setPassword(objAppConstants.getPassword());
		mailSender.setProtocol("smtp");
		java.util.Properties props = mailSender.getJavaMailProperties();
		// props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.debug", "true");
		props.put("mail.smtp.quitwait", "false");

		mailSender.setJavaMailProperties(props);
		return mailSender;
	}


	public void sendEmail(Mail mail, Map<String, Object> model) throws IOException, MessagingException {

		JavaMailSender mailSender1 = getJavaMailSender();
		MimeMessage mimeMessage = mailSender1.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setSubject(mail.getMailSubject());
		mimeMessageHelper.setFrom(mail.getMailFrom());
		if (StringUtils.isNotBlank(mail.getMailTo()))
			mimeMessageHelper.setTo(mail.getMailTo());
		if (StringUtils.isNotBlank(mail.getMailCc()))
			mimeMessageHelper.addCc(mail.getMailCc());

		MimeMultipart multipart = new MimeMultipart("related");
		// first part (the html)
		BodyPart messageBodyPart = new MimeBodyPart();
		VelocityEngine vEngine = objAppConstants.getVelocityEngine();
		String text = VelocityEngineUtils.mergeTemplateIntoString(objAppConstants.getVelocityEngine(),
				objAppConstants.getEmailBody(), model);
		messageBodyPart.setContent(text, "text/html");
		multipart.addBodyPart(messageBodyPart);
		// second part (the image)
		messageBodyPart = new MimeBodyPart();
		DataSource fds = new FileDataSource(
				"C:/Users/uselo/eclipse-workspace/email-web/src/main/resources/images/Shankchakranama.png");
		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<Shankchakranama>");
		// add image to the multipart
		multipart.addBodyPart(messageBodyPart);
		

		// Third part Attachment files
		messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource("C:/Users/uselo/eclipse-workspace/email-web/src/main/resources/test1.pptx");
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName("Patrika.pptx");
		multipart.addBodyPart(messageBodyPart);
		// Send the complete message parts
		mimeMessage.setContent(multipart);
		mailSender1.send(mimeMessage);

	}
}
