<%@page import="org.frameworkset.security.session.SessionUtil"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="org.frameworkset.session.TestVO,org.frameworkset.session.TestVO1"%>
<%
String value = (String)session.getAttribute("$a.b.c");
if(value == null)
{
	session.setMaxInactiveInterval(10);//手动设置会话有效期，单位：秒
	session.setAttribute("$a.b.c", "a");
}
out.println("session id:"+session.getId());
out.println("<br>");
value = (String)session.getAttribute("$a.b.c");
out.println("$a.b.c:"+value);
out.println("<br>");

session.setAttribute("local", java.util.Locale.CHINESE);
out.println("session.getServletContext():"+session.getServletContext());
out.println("<br>");
out.println("local:"+session.getAttribute("local"));

//下面的功能演示存储一个复杂对象（包含引用关系）到session中，然后读取出来验证对象关系是否正确还原
TestVO testVO = new TestVO();
testVO.setId("testvoid");
TestVO1 testVO1 = new TestVO1();
testVO1.setName("hello,test vo1");
testVO.setTestVO1(testVO1);
session.setAttribute("testVO", testVO);
testVO = (TestVO)session.getAttribute("testVO");
//修改testVO中属性的值
testVO.setId("testvoidaaaaa");
//需要将修改后的对象重新设置到session中否则无法存储最新的testVO到mongodb中
session.setAttribute("testVO", testVO);
String userAccount = (String)session.getAttribute("userAccount");//session应用设置的共享会话属性
out.println("shared attribute userAccount before setAttribute:"+userAccount+"<br>");
session.setAttribute("userAccount","john");//跨应用共享属性
out.println("<br>");
String privateAttr = (String)session.getAttribute("privateAttr");//session应用设置的私有会话属性
userAccount = (String)session.getAttribute("userAccount");//session应用设置的共享会话属性
out.println("sessionmonitor's private attribute:"+privateAttr+"<br>");
out.println("shared attribute userAccount after setAttribute:"+userAccount+"<br>");
testVO = (TestVO)session.getAttribute("testVO");
out.println("attribute testVO:"+testVO.getId()+"<br>");
String sessionId = session.getId();
out.println(sessionId);

String sid = SessionUtil.signParameterSessionID(request); 
sid = SessionUtil.signParameterSessionID(request); //重复签名将返回上次签名的sessionid
//SessionUtil.removeSession(sessionId, request);
//out.print("request.getSession(false):"+request.getSession(false));
String params = SessionUtil.getSessionManager().getCookiename()+"="+sid ;
 %>
 
 <a href="http://127.0.0.1:8082/sessionmonitor/sessiontest.jsp" target="demo">session跨域测试</a>
 <br>
 <a href="http://127.0.0.1:8082/sessionmonitor/sessiontest.jsp?<%=params %>" target="demo">sessionid跨域测试</a>
 <br>
 <a href="http://127.0.0.1:8082/sessionmonitor/session/sessionManager/sessionManager.page" target="demomonitor">session监控</a>