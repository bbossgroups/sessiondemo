package test;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.frameworkset.security.session.impl.SessionFilter;

public class MyFilter extends SessionFilter {

	public MyFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.doFilter(request, response, fc);
	}

}
