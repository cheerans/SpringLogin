package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import com.model.OurUser;
import com.model.SaltedUser;
import com.service.ourinterFace.GeneralSvc;
import com.service.ourinterFace.LoginSvc;
import com.spring.form.NewUserForm;
import com.spring.form.UserForm;
import com.util.Cruda;
import com.util.FormUtils;
import com.util.SpringStringResource;

@Controller
public class AddEditUserProfileController {
	

	public final String DISPLAYUSERPROFILE = "DisplayUserProfile";	
	public final String EDITUSERPROFILEVIEW = "EditUserProfile";
	public final String LOGINVIEW = "Login";
	public final String NEWUSERPROFILEVIEW = "NewUserProfile";
	
	
	@Autowired
	private LoginSvc loginService;
	
	@Autowired
	private GeneralSvc generalService;
	
	@Value("${storeid}")
	private int storeid;

	public AddEditUserProfileController() {
	}
		
	@Secured(value = "ROLE_USER")
	@RequestMapping(value="/displayUserProfile.do",method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		UserForm user = new UserForm();
		user.setAction(Cruda.list); 
		SaltedUser salteduser = (SaltedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = salteduser.getUsername();
		OurUser dbuser = generalService.getUser(userId);
		FormUtils.copyFormFieldsUsingReflection(dbuser, user);
		// command object
		model.addAttribute("user", user);
		// return form view
		return DISPLAYUSERPROFILE;
	}
	
	@Secured(value = "ROLE_USER")
	@RequestMapping(value="/editUserProfile.do",method = RequestMethod.POST)
	public String processEdituserProfile(	@Valid @ModelAttribute("user") UserForm formBean,
											BindingResult result, 
											HttpServletRequest request,
											SessionStatus status) {

		String view = EDITUSERPROFILEVIEW;
		if (result.hasErrors()) {
			// do nothing
			;
		} else {
			if(formBean.getAction().equals(Cruda.update)){						
				if (loginService.isLoggedIn(request)) {
					OurUser userDBObj = generalService.getUser(formBean.getUserid());
					FormUtils.copyFormFieldsUsingReflection(formBean, userDBObj);
					userDBObj.setEncrypted(false);
					if(false == generalService.saveUser(userDBObj)){
						result.addError(new FieldError(result.getObjectName(),
								"userid", formBean.getUserid(), true, null, null,
								SpringStringResource.getString("error.saveprofile.failed")));
					}else{
						status.setComplete();
						view = DISPLAYUSERPROFILE;
					}
				} else {
					view = LOGINVIEW;
				}
			}else if(formBean.getAction().equals(Cruda.list)){
				view = DISPLAYUSERPROFILE;
			}
		}
		return view;
	}

	@Secured(value = "ROLE_USER")
	@RequestMapping(value="/editUserProfile.do",method = RequestMethod.GET)
	public String initAddEditUserForm(ModelMap model) {
		UserForm user = new UserForm();
		SaltedUser salteduser = (SaltedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userId = salteduser.getUsername();
		OurUser dbuser = generalService.getUser(userId);
		FormUtils.copyFormFieldsUsingReflection(dbuser, user);
		user.setAction(Cruda.update);
		// command object
		model.addAttribute("user", user);
		// return form view
		return EDITUSERPROFILEVIEW;
	}
	
	@RequestMapping(value="/newUserProfile.do",method = RequestMethod.GET)
	public String initAddNewUserForm(ModelMap model) {
		NewUserForm user = new NewUserForm();
		user.setAction(Cruda.add);
		// command object
		model.addAttribute("user", user);
		// return form view
		return NEWUSERPROFILEVIEW;
	}
	
	@RequestMapping(value="/newUserProfile.do",method = RequestMethod.POST)
	public String processNewUserProfile(	@Valid @ModelAttribute("user") NewUserForm formBean,
											BindingResult result, 
											HttpServletRequest request,
											SessionStatus status) {

		String view = NEWUSERPROFILEVIEW;
		if(formBean.getAction().equals(Cruda.add)){
			if(null != generalService.getUser(formBean.getUserid())){				
				result.addError(new FieldError(result.getObjectName(),
						"userid", formBean.getUserid(), true, null, null,
						SpringStringResource.getString("error.createnewuser.failed")));				
			}else if(false == request.getSession().getAttribute(formBean.getSecretCodeKey()).equals(formBean.getSecretCode())){
				result.addError(new FieldError(result.getObjectName(),
						"secretCode", formBean.getSecretCode(), true, null, null,
						SpringStringResource.getString("error.secretCode.incorrect")));
			}
			if (result.hasErrors()) {
				// do nothing
				;
			}else{
				formBean.setStoreid(storeid);				
				OurUser userDBObj = new OurUser();
				FormUtils.copyFormFieldsUsingReflection(formBean, userDBObj);
				userDBObj.setEncrypted(false);
				if(false == generalService.saveNewUser(userDBObj)){
					result.addError(new FieldError(result.getObjectName(),
							"userid", formBean.getUserid(), true, null, null,
							"Save failed for this user"));
				}else{
					status.setComplete();
					view = DISPLAYUSERPROFILE;
				}					
			}	
		}
		return view;
	}
}