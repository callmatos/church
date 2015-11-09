package org.church.ws.member;

import org.church.ws.common.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.church.model.entity.member.Member;

/**
 * This class is to validator all request to WS '/member*'.
 * 
 * This class have the @Component of spring inside the CommonValidation class.
 * @author charles.ma
 * 
 */
@Component
public class MemberWSValidator extends CommonValidation<Member> {

	// Logger.
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberWSValidator.class);
	
	/**
	 * The constructor.
	 */
	public MemberWSValidator(){
		super(Member.class);
	}
	
	/**
	 * Mandatory parameters name,Address,password and login.
	 */
	@Override
	public boolean isCreateValidator(Member d, Errors errors) {
		
		LOGGER.info("Method MemberWSValidator:isCreateValidator called");
		
		ValidationUtils.rejectIfEmpty(errors, "member", null);
		ValidationUtils.rejectIfEmpty(errors, "member.name", null);
		ValidationUtils.rejectIfEmpty(errors, "member.userName", null);
		ValidationUtils.rejectIfEmpty(errors, "member.password", null);
		ValidationUtils.rejectIfEmpty(errors, "member.address", null);
		
		if(d != null && d.getAddress() == null)
			errors.rejectValue("member.address", null);
		
		return errors.hasErrors();
	}

	@Override
	public boolean isUpdateValidator(Member d, Errors errors) {
		
		LOGGER.info("Method MemberWSValidator:isUpdateValidator called");
		
		ValidationUtils.rejectIfEmpty(errors, "member", null);
		ValidationUtils.rejectIfEmpty(errors, "member.userName", null);
		ValidationUtils.rejectIfEmpty(errors, "member.name", null);
		ValidationUtils.rejectIfEmpty(errors, "member.password", null);
		ValidationUtils.rejectIfEmpty(errors, "member.address", null);
		
		return errors.hasErrors();
	}

	@Override
	public boolean isDeleteValidator(Member d, Errors errors) {
		
		LOGGER.info("Method isDeleteValidator called");
		
		ValidationUtils.rejectIfEmpty(errors, "member", null);
		
		if(d == null)
			errors.rejectValue("member", null);
		
		return errors.hasErrors();
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		LOGGER.info("Method validate called");
		
		Member mm = (Member) arg0;
		
		LOGGER.info("Member information: "+mm.toString());
		
		ValidationUtils.rejectIfEmpty(errors, "member", "obje.required");
		ValidationUtils.rejectIfEmpty(errors, "member.userName", "useName.required");
		
		
	}
	
}
