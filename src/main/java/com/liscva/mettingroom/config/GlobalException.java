package com.liscva.mettingroom.config;


import com.liscva.framework.core.connect.FailPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.framework.core.exception.CoreException;
import com.liscva.framework.security.exception.DisableLoginException;
import com.liscva.framework.security.exception.NotLoginException;
import com.liscva.framework.security.exception.NotPermissionException;
import com.liscva.framework.security.exception.NotRoleException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理 
 */
@ControllerAdvice
public class GlobalException {

	public static final int CODE_SUCCESS = 200;			// 成功状态码
	public static final int CODE_ERROR = 500;			// 错误状态码
	public static final int CODE_WARNING = 501;			// 警告状态码
	public static final int CODE_NOT_JUR = 403;			// 无权限状态码
	public static final int CODE_NOT_LOGIN = 401;		// 未登录状态码

	// 全局异常拦截（拦截项目中的所有异常）
	@ResponseBody
	@ExceptionHandler
	public FinalConnect handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 打印堆栈，以供调试
		System.out.println("全局异常---------------");
		e.printStackTrace(); 

		// 不同异常返回不同状态码 
		FinalConnect finalConnect = null;
		if (e instanceof NotLoginException) {	// 如果是未登录异常
			NotLoginException ee = (NotLoginException) e;
			finalConnect = FailPublicConnect.error(CODE_NOT_LOGIN,"未登录，请登录后再次访问");
		} 
		else if(e instanceof NotRoleException) {		// 如果是角色异常
			NotRoleException ee = (NotRoleException) e;
			finalConnect = FailPublicConnect.error(CODE_NOT_JUR,"无此角色：" + ee.getRole());
		} 
		else if(e instanceof NotPermissionException) {	// 如果是权限异常
			NotPermissionException ee = (NotPermissionException) e;
			finalConnect = FailPublicConnect.error(CODE_NOT_JUR,"无此角色：" + ee.getCode());
		} 
		else if(e instanceof DisableLoginException) {	// 如果是被封禁异常
			DisableLoginException ee = (DisableLoginException) e;
			finalConnect = FailPublicConnect.error(CODE_NOT_JUR,"账号被封禁：" + ee.getDisableTime() + "秒后解封");
		}
		else {	// 普通异常, 输出：500 + 异常信息 
			finalConnect = FailPublicConnect.defaultError(e.getMessage());
		}
		
		// 返回给前端
		return finalConnect;
	}
	
}
