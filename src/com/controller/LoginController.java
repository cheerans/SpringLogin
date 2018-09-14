package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.model.OurUser;
import com.service.ourinterFace.GeneralSvc;
import com.service.ourinterFace.LoginSvc;
import com.spring.form.AdminForm;
import com.spring.form.ChangePasswordForm;
import com.spring.form.ForgotPasswordForm;
import com.spring.form.LoginForm;
import com.util.FormUtils;
import com.util.RandomPasswordGenerator;
import com.util.SpringStringResource;
import com.util.TextImageGenerator;

@Controller
public class LoginController {
	
	public final String ACCESSDENIEDVIEW = "403";
	public final String LOGIN_ACTION = "redirect:/login.do";
	public final String HOME = LOGIN_ACTION;	
	public final String LOGINVIEW = "Login";
	public final String ADDDELETEADMIN = "AddDeleteAdminUser";
	public final String FORGOTPASSWORD = "forgotPassword";
	public final String CHANGEPASSWORD = "changePassword";

	
	@Autowired
	private LoginSvc loginService;
	
	@Autowired
	private GeneralSvc generalService;
	
	@Autowired
	private RandomPasswordGenerator passGen;
	
	@RequestMapping(value = "/accessDenied.do",method=RequestMethod.GET)
	public String accessDenied(Model model,
							   HttpServletRequest request) {
		return ACCESSDENIEDVIEW; // logical view name
	}
	
	@RequestMapping(value = "/invalidSession.do",method=RequestMethod.GET)
	public String invalidSession(	Model model,
			   						HttpServletRequest request) {
		return LOGIN_ACTION; // logical view name
	}
	
	@RequestMapping(value = "/home.do",method=RequestMethod.GET)
	public String home(	Model model,
			   			HttpServletRequest request) {
		return HOME; // logical view name
	}	
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)	
	public String loadLogin(	Model model,
								HttpServletRequest request) {
		String view = LOGINVIEW;
		model.addAttribute("user", generalService.getLoggedInUserDetails());
		return view;
	}	
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)	
	public String doLogin(	@ModelAttribute("user") @Valid LoginForm formBean,
							BindingResult result, 
							HttpServletRequest request,
							SessionStatus status) {
		String view = LOGINVIEW;
		
		if(result.hasErrors()){
			view = LOGINVIEW;
		}	
		else if(true == loginService.login(formBean.getUserid(),formBean.getPassword(),request)){
			formBean.setLoggedIn(true);
		}else{
			view = LOGINVIEW;
			result.addError(new FieldError(result.getObjectName(),
					"password", formBean.getPassword(), true, null, null,
					SpringStringResource.getString("invalid.password")));			
		}
		return view;
	}		
	
	
	@Secured(value = "ROLE_SUPERUSER")
	@RequestMapping(value="/addNewAdmin.do",method=RequestMethod.GET)	
	public String addNewAdmin(	Model model,
								HttpServletRequest request) {
		String view = ADDDELETEADMIN;
		AdminForm formBean = new AdminForm();
		try {
			formBean.setAdminList(generalService.getAdminList());
			formBean.setUserList(generalService.getAllUserIdList());
			formBean.setSuperuserList(generalService.getSuperUserList());
			model.addAttribute("adminForm", formBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@Secured(value = "ROLE_SUPERUSER")
	@RequestMapping(value="/addNewAdmin.do",method=RequestMethod.POST)	
	public String addNewAdminPost(	@ModelAttribute("adminForm") @Valid AdminForm formBean,
									BindingResult result,
									HttpServletRequest request,
									SessionStatus status) {
		String view = ADDDELETEADMIN;
		
		if(result.hasErrors()){
		}	
		else if(	(null != formBean.getNewAdminUserid()) &&
				 	(false == formBean.getNewAdminUserid().isEmpty())){
			generalService.makeUserAdmin(formBean.getNewAdminUserid());			
			try {
				formBean.setAdminList(generalService.getAdminList());
				formBean.setUserList(generalService.getAllUserIdList());	
				formBean.setSuperuserList(generalService.getSuperUserList());
			} catch (Exception e) {	
			}
		}else{
			view = LOGINVIEW;
			result.addError(new FieldError(result.getObjectName(),
					"newAdminuserid", formBean.getNewAdminUserid(), true, null, null,
					SpringStringResource.getString("required.userid")));			
		}
		return view;
	}	
	
	@Secured(value = "ROLE_SUPERUSER")
	@RequestMapping(value="/deleteAdmin.do",method=RequestMethod.POST)	
	public String deleteAdminPost(	@ModelAttribute("adminForm") @Valid AdminForm formBean,
									BindingResult result, 
									HttpServletRequest request,
									SessionStatus status) {
		String view = ADDDELETEADMIN;
		
		if(result.hasErrors()){
		}	
		else if(	(null != formBean.getDeleteUserid()) &&
				 	(false == formBean.getDeleteUserid().isEmpty())){
			generalService.removeUserAsAdmin(formBean.getDeleteUserid());
			try {
				formBean.setAdminList(generalService.getAdminList());
				formBean.setUserList(generalService.getAllUserIdList());	
				formBean.setSuperuserList(generalService.getSuperUserList());
			} catch (Exception e) {	
			}			
		}else{
			view = LOGINVIEW;
			result.addError(new FieldError(result.getObjectName(),
					"newAdminuserid", formBean.getNewAdminUserid(), true, null, null,
					SpringStringResource.getString("required.userid")));			
		}
		return view;
	}
	
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)	
	public String doLogout(@Valid @ModelAttribute("user") LoginForm user,
			BindingResult result, HttpServletRequest request, SessionStatus status) {
		loginService.logout();
		return LOGINVIEW;
	}
	
	@RequestMapping(value="/forgotPassword.do",method=RequestMethod.GET)
	public String forgotPasswordGet(	Model model,
										HttpServletRequest request) {
		ForgotPasswordForm fpass = new ForgotPasswordForm();
		model.addAttribute("fpass", fpass);
		return FORGOTPASSWORD;
	}

	@RequestMapping(value="/forgotPassword.do",method=RequestMethod.POST)	
	public String forgotPasswordPost(	@Valid @ModelAttribute("fpass") ForgotPasswordForm formBean,
										BindingResult result, 
										HttpServletRequest request,
										SessionStatus status) {
		String view = FORGOTPASSWORD;

		if(null == generalService.getUser(formBean.getUserid())){ 
			result.addError(new FieldError(result.getObjectName(),
							"userid", formBean.getUserid(), true, null, null,
							SpringStringResource.getString("error.userid.dontexist")));
		}else if(false == request.getSession().getAttribute(formBean.getSecretCodeKey()).equals(formBean.getSecretCode())){
			result.addError(new FieldError(result.getObjectName(),
					"secretCode", formBean.getSecretCode(), true, null, null,
					SpringStringResource.getString("error.secretCode.incorrect")));
		}
		if(result.hasErrors()){
			;
		}else{	
			String newPassword = passGen.nextPassword();			
			if(false == generalService.changePassword(formBean.getUserid(),newPassword)){
				result.addError(new FieldError(result.getObjectName(),
						"password", formBean.getPassword(), true, null, null,
						SpringStringResource.getString("error.password.changefailed")));
			}else{
				String[] recipients = {"cheeran@hotmail.com"};
				/*
				boolean success = false;
				try {
					success = generalService.sendEmail(recipients, "New Password is - " + newPassword, "Password Change");
				} catch (Exception e) {
					success = true;
				}
				if(false == success){
					result.addError(new FieldError(result.getObjectName(),
							"password", formBean.getPassword(), true, null, null,
							SpringStringResource.getString("error.password.changefailed")));
				}else{
				*/
					OurUser user = generalService.getUser(formBean.getUserid());
					FormUtils.copyFormFieldsUsingReflection(user, formBean);
					formBean.setPassword(newPassword);
				//}
			}
		}
		return view;
	}
	
	@Secured(value = "ROLE_USER")
	@RequestMapping(value="/changePassword.do",method=RequestMethod.GET)	
	public String changePasswordGet(	Model model,
										HttpServletRequest request) {
		ChangePasswordForm cpass = new ChangePasswordForm();
		FormUtils.copyFormFieldsUsingReflection(generalService.getLoggedInUserDetails(),cpass);		
		model.addAttribute("cpass", cpass);
		return CHANGEPASSWORD;
	}
	
	@Secured(value = "ROLE_USER")
	@RequestMapping(value="/changePassword.do",method=RequestMethod.POST)	
	public String changePasswordPost(	@Valid @ModelAttribute("cpass") ChangePasswordForm formBean,
										BindingResult result, 
										HttpServletRequest request,
										SessionStatus status) {
		String view = CHANGEPASSWORD;

		if (false == formBean.getPassword().equals(formBean.getConfirmPassword())) {
			result.addError(new FieldError(result.getObjectName(),
							"password", formBean.getPassword(), true, null, null,
							SpringStringResource.getString("error.password.mustmatch")));
		}else if(false == request.getSession().getAttribute(formBean.getSecretCodeKey()).equals(formBean.getSecretCode())){
			result.addError(new FieldError(result.getObjectName(),
					"secretCode", formBean.getSecretCode(), true, null, null,
					SpringStringResource.getString("error.secretCode.incorrect")));
		}
		
		if(result.hasErrors()){
			;
		}else{
			if(false == generalService.changePassword(formBean.getUserid(),formBean.getPassword())){
				result.addError(new FieldError(result.getObjectName(),
						"password", formBean.getPassword(), true, null, null,
						SpringStringResource.getString("error.password.changefailed")));
			}else{
				FormUtils.copyFormFieldsUsingReflection(generalService.getUser(formBean.getUserid()), formBean);
				view = HOME;
			}
		}
		return view;
	}	
	
	@RequestMapping(value="/createSecretCode.do",method=RequestMethod.GET)	
	public String createSecretCode(	Model model,
									HttpServletRequest request) {
		final String AJAXSECRETCODEVIEWNAME="ajax_createSecretCode";
		final String IMAGEFILEATTRIB = "imagefileAttrib";
		final String PATHREAL = "secretcodeimages\\";
		final String PATHCONTEXT = "/secretcodeimages/";
		final String fileExt = ".png";
		
		String passcodeImageName = generalService.getLoggedInUser();
		
		passcodeImageName += passGen.nextRandomWord();
		passcodeImageName = "" + passcodeImageName + fileExt;
		String secretCode = passGen.nextRandomWord();
		
		String retImageName = request.getContextPath() + PATHCONTEXT + passcodeImageName;
		passcodeImageName = request.getRealPath("/") + PATHREAL + passcodeImageName;
		
		TextImageGenerator.createTextImage(passcodeImageName,secretCode);
		request.getSession().setAttribute(retImageName, secretCode);
		model.addAttribute(IMAGEFILEATTRIB, retImageName);
		return AJAXSECRETCODEVIEWNAME;
	}
}