package com.srirama.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.srirama.utility.Mail;
import com.srirama.utility.SendMail;
import com.srirama.utility.AppConstants;
import com.srirama.utility.DevoteeDetails;
import com.srirama.utility.ExcelRead;

@Controller
public class HelloWorldController {
	@Autowired
	private AppConstants objAppConstants;
	@Autowired
	private ExcelRead objReadExcel;
	@Autowired
	private SendMail objSendMail;

   
   @RequestMapping(path={"/"},method=RequestMethod.GET)
   public String sayHello(Model model,HttpServletRequest request) {

	   //Java 8 LocalDate
     DateTimeFormatter formatter=DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
     LocalDate date=LocalDate.now();
     model.addAttribute("date", date.format(formatter));
     
 	List<DevoteeDetails> devoteeDetailsList=objReadExcel.readExcel(objAppConstants);
 	System.out.println("devoteeDetailsList"+devoteeDetailsList.size());
 	
  	for(DevoteeDetails objDevoteeDetails: devoteeDetailsList)
  	{
  		Mail objMail=new Mail();
  		Map<String, Object> objMap=new HashMap<String, Object>();
		try {
			objMap.put(objAppConstants.getVelocityPlaceHolderDevoteeName(), objDevoteeDetails.getDevoteeName());
			objMap.put(objAppConstants.getVelocityPlaceHolderGothramSpouse(), objDevoteeDetails.getSpouseName());
			objMap.put(objAppConstants.getVelocityPlaceHolderKid(), objDevoteeDetails.getKidNumber());
			objMap.put("projectPath", request.getContextPath());
			// Replaced by DevoteeDetails Object 
			objMail.setMailFrom(objAppConstants.getEmailFromAddress());
			objMail.setMailTo(objDevoteeDetails.getPrimaryEmailID());
			objMail.setMailCc(objDevoteeDetails.getSecondaryEmailID());
			objMail.setMailSubject(objAppConstants.getEmailSubject());
			if(StringUtils.isNotBlank(objMail.getMailTo())|| StringUtils.isNotBlank(objMail.getMailCc()))
			{
				objSendMail.sendEmail(objMail, objMap);
				System.out.println("Sent email to: " + objDevoteeDetails.getPrimaryEmailID() + objDevoteeDetails.getSecondaryEmailID());
			}
			else 
				System.out.println("Both the Primary EmailID and Secondary Email Id are not present for "+objDevoteeDetails.getDevoteeName()+"with KID"+objDevoteeDetails.getKidNumber());
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
   }
  	System.out.println("Done");
     return "index";
   }
}