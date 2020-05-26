package cn.project.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

//@Component
public class CusomCsrfMatcher implements RequestMatcher {
	public static final String HEADER_NAME = "remoteHost";
	private final HashSet<String> allowedMethods = new HashSet<>(Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));
	@Value("${csrf.allowed.server.url}")
	private String url;
	
	@Override
	public boolean matches(HttpServletRequest request) {
		boolean returnValue = !this.allowedMethods.contains(request.getMethod());
		if(returnValue) {
			String remoteHost = request.getHeader(HEADER_NAME);
			if(remoteHost!=null) {
				returnValue = !url.equals(remoteHost);
			}
		}
		System.out.println(returnValue);
		return returnValue;
	}

	public void setUrls(String url) {
		this.url = url;
	}
}
