package test.filter;

import org.frameworkset.security.session.SessionEvent;
import org.frameworkset.security.session.SessionListener;

public class TestSessionListener implements SessionListener {

	public TestSessionListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createSession(SessionEvent event) {
		// TODO Auto-generated method stub
		//event.getSource().setMaxInactiveInterval(100);
	}

	@Override
	public void destroySession(SessionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAttribute(SessionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAttribute(SessionEvent event) {
		// TODO Auto-generated method stub

	}

}
