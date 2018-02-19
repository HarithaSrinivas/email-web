package com.srirama.utility;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(value = { "classpath:emailproject.properties" })
public class AppConstants {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${email.kid.body}")
	private String emailBody;

	@Value("${email.kid.subject}")
	private String emailSubject;

	@Value("${email.kid.fromaddress}")
	private String emailFromAddress;

	@Value("${email.kid.smtpserver}")
	private String EMAIL_KID_SMTPSERVER;

	@Value("${email.kid.smtpport}")
	private String EMAIL_KID_SMTPPORT;

	@Value("${email.excel.kid.start}")
	private String excelKidStart;

	@Value("${email.excel.kid.end}")
	private String excelKidEnd;

	@Value("${email.excel.worksheetname}")
	private String excelWorkSheetName;

	@Value("${email.excel.columnheadername.emailid}")
	private String excelColHeaderEmailId;

	@Value("${email.excel.columnheadername.kalyanamidnumber}")
	private String excelColnHeaderKid;

	@Value("${email.velocitytemplate.devoteename}")
	private String velocityPlaceHolderDevoteeName;

	@Value("${email.velocitytemplate.gothram.spouse}")
	private String velocityPlaceHolderGothramSpouse;

	@Value("${email.velocitytemplate.kalyanamidnumber}")
	private String velocityPlaceHolderKid;

	@Value("${email.excel.columnheadername.gothram}")
	private String excelColHeaderGothram;

	@Value("${email.excel.columnheadername.devoteename}")
	private String excelColHeaderDevoteeName;

	@Value("${email.excel.columnheadername.lastname}")
	private String excelColHeaderLastName;

	@Value("${email.excel.columnheadervalue.gothram.spouse}")
	private String excelColumnHeaderGothramSpouse;

	@Value("${email.kid.masterfile}")
	private String masterFilePath;

	@Value("${email.name.backgroundImage}")
	private String backgroundImage;

	@Value("${email.kid.username}")
	private String emailUserName;

	@Value("${email.kid.password}")
	private String password;

	public String getEmailUserName() {
		return emailUserName;
	}

	public void setEmailUserName(String emailUserName) {
		this.emailUserName = emailUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExcelColumnHeaderGothramSpouse() {
		return excelColumnHeaderGothramSpouse;
	}

	public void setExcelColumnHeaderGothramSpouse(String excelColumnHeaderGothramSpouse) {
		this.excelColumnHeaderGothramSpouse = excelColumnHeaderGothramSpouse;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getEMAIL_KID_SMTPSERVER() {
		return EMAIL_KID_SMTPSERVER;
	}

	public void setEMAIL_KID_SMTPSERVER(String eMAIL_KID_SMTPSERVER) {
		EMAIL_KID_SMTPSERVER = eMAIL_KID_SMTPSERVER;
	}

	public String getEMAIL_KID_SMTPPORT() {
		return EMAIL_KID_SMTPPORT;
	}

	public void setEMAIL_KID_SMTPPORT(String eMAIL_KID_SMTPPORT) {
		EMAIL_KID_SMTPPORT = eMAIL_KID_SMTPPORT;
	}

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	@Bean
	public VelocityEngine getVelocityEngine() {
		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
		return ve;
	}

	public String getMasterFilePath() {
		return masterFilePath;
	}

	public void setMasterFilePath(String masterFilePath) {
		this.masterFilePath = masterFilePath;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailFromAddress() {
		return emailFromAddress;
	}

	public void setEmailFromAddress(String emailFromAddress) {
		this.emailFromAddress = emailFromAddress;
	}

	public String getVelocityPlaceHolderDevoteeName() {
		return velocityPlaceHolderDevoteeName;
	}

	public void setVelocityPlaceHolderDevoteeName(String velocityPlaceHolderDevoteeName) {
		this.velocityPlaceHolderDevoteeName = velocityPlaceHolderDevoteeName;
	}

	public String getVelocityPlaceHolderGothramSpouse() {
		return velocityPlaceHolderGothramSpouse;
	}

	public void setVelocityPlaceHolderGothramSpouse(String velocityPlaceHolderGothramSpouse) {
		this.velocityPlaceHolderGothramSpouse = velocityPlaceHolderGothramSpouse;
	}

	public String getVelocityPlaceHolderKid() {
		return velocityPlaceHolderKid;
	}

	public void setVelocityPlaceHolderKid(String velocityPlaceHolderKid) {
		this.velocityPlaceHolderKid = velocityPlaceHolderKid;
	}

	public String getExcelColHeaderGothram() {
		return excelColHeaderGothram;
	}

	public void setExcelColHeaderGothram(String excelColHeaderGothram) {
		this.excelColHeaderGothram = excelColHeaderGothram;
	}

	public String getExcelColHeaderLastName() {
		return excelColHeaderLastName;
	}

	public void setExcelColHeaderLastName(String excelColHeaderLastName) {
		this.excelColHeaderLastName = excelColHeaderLastName;
	}

	public String getExcelKidEnd() {
		return excelKidEnd;
	}

	public void setExcelKidEnd(String excelKidEnd) {
		this.excelKidEnd = excelKidEnd;
	}

	public String getExcelKidStart() {
		return excelKidStart;
	}

	public void setExcelKidStart(String excelKidStart) {
		this.excelKidStart = excelKidStart;
	}

	public String getExcelColnHeaderKid() {
		return excelColnHeaderKid;
	}

	public void setExcelColnHeaderKid(String excelColnHeaderKid) {
		this.excelColnHeaderKid = excelColnHeaderKid;
	}

	public String getExcelColHeaderDevoteeName() {
		return excelColHeaderDevoteeName;
	}

	public void setExcelColHeaderDevoteeName(String excelColHeaderDevoteeName) {
		this.excelColHeaderDevoteeName = excelColHeaderDevoteeName;
	}

	public String getExcelColHeaderEmailId() {
		return excelColHeaderEmailId;
	}

	public void setExcelColHeaderEmailId(String excelColHeaderEmailId) {
		this.excelColHeaderEmailId = excelColHeaderEmailId;
	}

	public String getExcelWorkSheetName() {
		return excelWorkSheetName;
	}

	public void setExcelWorkSheetName(String excelWorkSheetName) {
		this.excelWorkSheetName = excelWorkSheetName;
	}

}
