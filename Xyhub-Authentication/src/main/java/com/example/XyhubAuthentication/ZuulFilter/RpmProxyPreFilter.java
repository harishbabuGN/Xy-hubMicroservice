package com.example.XyhubAuthentication.ZuulFilter;



import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RpmProxyPreFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(RpmProxyPreFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
//    logger.info("==========================================================================================");
//    logger.info(String.format("|| %s request to %s ||", request.getMethod(), request.getRequestURL().toString()));
//    logger.info("==========================================================================================");

		String[] logInfo = getDecoratedLog(request);
		logger.info(logInfo[0]);
		logger.info(logInfo[1]);
		logger.info(logInfo[0]);

		return null;
	}

	private String[] getDecoratedLog(HttpServletRequest request) {

		StringBuilder decorator = new StringBuilder();
		String requestInfo = request.getMethod() + " request to " + request.getRequestURL().toString();
		int length = requestInfo.length() < 100 ? requestInfo.length() : 100;

		for (int i = 0; i < length; i++)
			decorator.append("=");

		String[] decoratedLog = new String[2];
		decoratedLog[0] = decorator.toString();
		decoratedLog[1] = requestInfo;
		return decoratedLog;
	}

}