/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.fjw.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.fjw.domain.User;
import com.fjw.service.UserService;
import com.fjw.struts.form.ChangePasswordForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-13-2016
 * 
 * XDoclet definition:
 * @struts.action path="/changePassword" name="changePasswordForm" scope="request"
 */
public class ChangePasswordAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ChangePasswordForm changePasswordForm = (ChangePasswordForm) form;// 
		
		User user=(User) request.getSession().getAttribute("user");
		if(user==null) {
			return mapping.findForward("goHome");
		}
		if(validate(changePasswordForm,request,response)==false) {
			return mapping.findForward("rechange");
		} else {
			User newUser=new User();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(changePasswordForm.getNewPassword1());
			if((newUser=UserService.changePassword(newUser))!=null) {
				request.setAttribute("info", "修改密码成功");
				request.getSession().setAttribute("user", newUser);
				return mapping.findForward("result");
			} else {
				request.setAttribute("errorNewPassword2", "修改密码失败");
				return mapping.findForward("rechange");
			}
		}
	}

	private boolean validate(ChangePasswordForm changePasswordForm,
			HttpServletRequest request, HttpServletResponse response) {
		if(changePasswordForm.getNewPassword1().equals(changePasswordForm.getNewPassword2())==false) {
			request.setAttribute("errorNewPassword2", "两次输入不一致");
			return false;
		} else if(changePasswordForm.getNewPassword1().length()<6) {
			request.setAttribute("errorNewPassword1", "新密码的长度必须大于6位");
			return false;
		} else if(changePasswordForm.getOldPassword().equals(((User)request.getSession().getAttribute("user")).getPassword())==false) {
			request.setAttribute("errorOldPassword", "旧密码不正确");
			return false;
		}
		return true;
	}
	
	
}