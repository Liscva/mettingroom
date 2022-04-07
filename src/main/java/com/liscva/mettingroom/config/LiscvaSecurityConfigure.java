package com.liscva.mettingroom.config;


import com.liscva.framework.core.connect.FailPublicConnect;
import com.liscva.framework.security.context.SecurityHolder;
import com.liscva.framework.security.spring.filter.SecurityServletFilter;
import com.liscva.framework.security.spring.interceptor.SecurityAnnotationInterceptor;
import com.liscva.framework.security.strategy.SecurityInfoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * [权限认证] 配置类
 *
 */
@Configuration
public class LiscvaSecurityConfigure implements WebMvcConfigurer {
	
	/**
	 * 注册拦截器，打开注解式鉴权功能
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册注解拦截器 
		registry.addInterceptor(new SecurityAnnotationInterceptor()).addPathPatterns("/**");
	}
	
	/**
     * 注册 [全局过滤器]
     */
    @Bean
	public SecurityServletFilter getSecurityServletFilter() {
        return new SecurityServletFilter()
        		
        		// 指定 [拦截路由] 与 [放行路由]
        		.addInclude("/**")// .addExclude("/favicon.ico")
        		
        		// 认证函数: 每次请求执行 
        		.setAuth(obj -> {
        			// System.out.println("---------- 全局认证 " + SecurityHolder.getRequest().getRequestPath());
        			
        		})
        		
        		// 异常处理函数：每次认证函数发生异常时执行此函数 
        		.setError(e -> {
        			System.out.println("---------- sa全局异常 ");
        			return FailPublicConnect.defaultError(e.getMessage());
        		})
        		
        		// 前置函数：在每次认证函数之前执行
        		.setBeforeAuth(r -> {
        			// ---------- 设置一些安全响应头 ----------
        			SecurityHolder.getResponse()
        			// 服务器名称 
        			.setServer("liscva-server")
        			// 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以 
        			.setHeader("X-Frame-Options", "SAMEORIGIN")
        			// 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
        			.setHeader("X-XSS-Protection", "1; mode=block")
        			// 禁用浏览器内容嗅探 
        			.setHeader("X-Content-Type-Options", "nosniff");
        		});
    }
    
    /**
     * 重写 内部算法策略
     */
    @Autowired
    public void rewriteSecurityInfoStrategy() {
    	// 重写注解处理器，增加注解合并功能
    	SecurityInfoStrategy.me.getAnnotation = (element, annotationClass) -> {
    		return AnnotatedElementUtils.getMergedAnnotation(element, annotationClass); 
    	};
    }
    
}
