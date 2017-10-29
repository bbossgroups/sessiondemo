package test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * 基于session共享的sso filter伪代码过滤器
 *
 */
public class SessionSSOFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		boolean exclude = excludeRequest(request);
		if(exclude){//不需要检测，直接允许访问
			fc.doFilter(request, response);
			return;
		}
		//判断是否已经登陆
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession(false);
		if(session == null){//session不存在，没有登陆，直接跳转到没有登陆提示页面或者登陆页，对于集成sso的系统，最好跳转到没有登陆的提示页
			request.getRequestDispatcher("notloginurl").forward(request, response);
		}
		else
		{
			Object principal = session.getAttribute("principal");//代表当前系统的登陆会话对象，一般是私有的session属性，每个系统登录成功后，都会在session中存放登陆会话对象
			
			//获取共享属性
			String userAccount = (String)session.getAttribute("userAccount");
			//检查登陆会话对象是否存在
			if(principal == null){//不存在当前系统的登陆会话对象，说明没有登陆当前系统
				//检查是否存在共享属性userAccount，
				if(userAccount != null){//如果存在共享属性userAccount，说明在其他系统已经登陆过，则利用共享属性userAccount来一次本地自动登陆操作
					//1.利用userAccount获取登陆所需要的其他数据
					
					//2.自动登陆
					
					//3.登陆成功后，跳转到系统首页或者跳转到正在访问的url，下面的两条语句二选一					
					request.getRequestDispatcher("indexurl").forward(request, response);//跳转到系统首页
					
					fc.doFilter(request, response);//跳转到正在访问的url
					
				}
				else//共享属性userAccount不存在，则跳转到登陆页
				{
					request.getRequestDispatcher("loginurl").forward(request, response);
				}
			}
			else{//登陆会话对象存在，继续访问系统；
				 //比较合理的做法应该还需要检测一下共享属性对应的用户和登陆会话对象中对应的用户是否是同一个用户，如果不是同一个用户，就要用共享属性对应的用户重新模拟登陆
				fc.doFilter(request, response);
			}
			
		}

	}
	/**
	 * 检查地址是否不需要认证检测，如果不需要则返回true，否则返回false
	 * @param request
	 * @return
	 */
	private boolean excludeRequest(ServletRequest request){
		//检测逻辑
		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
