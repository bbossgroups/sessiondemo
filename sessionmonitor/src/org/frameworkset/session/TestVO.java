package org.frameworkset.session;

public class TestVO implements java.io.Serializable{
	private String id;
	private TestVO1 testVO1;
	public TestVO() {
		// TODO Auto-generated constructor stub
	}
	public TestVO1 getTestVO1() {
		return testVO1;
	}
	public void setTestVO1(TestVO1 testVO1) {
		this.testVO1 = testVO1;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
