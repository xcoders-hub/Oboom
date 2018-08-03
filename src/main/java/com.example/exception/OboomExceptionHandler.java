package com.example.exception;


import com.example.utils.JSONResult;
import com.example.utils.urlUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


@ControllerAdvice
public  class OboomExceptionHandler {

	public static final String ERROR_VIEW = "errorPage";

	@ExceptionHandler(value = Exception.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
    public Object errorHandler(HttpServletRequest reqest,
							   HttpServletResponse response, Exception e) throws Exception {
    	e.printStackTrace();
    	if (isAjax(reqest)) {
    		return JSONResult.errorException(e.getMessage());
    	} else {
			ModelAndView mav = new ModelAndView();
			String info = urlUtils.infoByGet("http://api.laifudao.com/open/xiaohua.json");
			JSONArray jsonArray =new JSONArray(new JSONObject(info).getString("msg"));
			JSONObject json  =new JSONObject(jsonArray.get(new Random().nextInt(jsonArray.length())).toString());
			String context = StringEscapeUtils.unescapeJava(json.getString("content").replaceAll("[</br><br>]",""));
			mav.addObject("title", ":( 服务器内部错误-500");
			for(int i = 0;i<10;i++){
				context= context.trim().replaceAll("　","");
			}
                System.out.println(""+context);
			mav.addObject("context",context);
			mav.setViewName("errorPage");
			return mav;
    	}
    }
	public static boolean isAjax(HttpServletRequest httpRequest){
		return  (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest"
				.equals( httpRequest.getHeader("X-Requested-With").toString()) );
	}
	protected static final Log logger = LogFactory.getLog(OboomExceptionHandler.class);

	/*@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
	{
		ModelAndView modelView = null;
		try {
			onException(e);
		} catch (Exception e1) {
			logger.error("ExceptionResolver onException", e1); }
		try {// 404 处理
			if (e instanceof NoHandlerFoundException)
			{

				modelView = onPageNotFoundError(request, response, e);

				return modelView;
			}
		} catch (Exception e1) {
			logger.error("ExceptionResolver onPageNotFoundError", e1);
		}
		// 处理器方法，才会执行异常处理
		if (handler instanceof HandlerMethod)
		{
			HandlerMethod method = (HandlerMethod) handler;
			boolean isApi = (method.hasMethodAnnotation(ResponseBody.class)
					|| method.getBeanType().isAnnotationPresent(RestController.class));
			try
			{	// api
				if (isApi) {

					modelView = onApiError(request, response, e, method);

				} else {

					modelView = onPageError(request, response, e, method);

				}
			} catch (Exception e1) {
				logger.error("ExceptionResolver onError", e1);
			}
		}
		return modelView;
	}




	*//**
	 *
	 * @Title: IMoocExceptionHandler.java
	 * @Package com.imooc.exception
	 * @Description: 判断是否是ajax请求
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 *//*
	public static boolean isAjax(HttpServletRequest httpRequest){
		return  (httpRequest.getHeader("X-Requested-With") != null
					&& "XMLHttpRequest"
						.equals( httpRequest.getHeader("X-Requested-With").toString()) );
	}
	*//*Ajax 的错误吧---------------------猜测*//*
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception
	{
		response.setContentType("application/json;charset=utf-8");

		return null;
	}

	*//**
	 * 出现异常时回调
	 *//*
	protected abstract void onException(Exception e) throws Exception;

	*//**
	 * api请求出错
	 *//*
	protected abstract ModelAndView onApiError(HttpServletRequest request, HttpServletResponse response, Exception e,
											   HandlerMethod method) throws Exception;
	*//**
	 * 页面请求出错
	 *//*
	protected abstract ModelAndView onPageError(HttpServletRequest request, HttpServletResponse response, Exception e,
												HandlerMethod method) throws Exception;
	*//**
	 * 页面未找到
	 *//*
	protected abstract ModelAndView onPageNotFoundError(HttpServletRequest request, HttpServletResponse response,
														Exception e);
*/}
