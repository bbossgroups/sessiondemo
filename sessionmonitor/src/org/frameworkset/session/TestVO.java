package org.frameworkset.session;

import java.util.Random;

public class TestVO implements java.io.Serializable{
	private String id;
	private TestVO1 testVO1;
	public static int random;
	public TestVO() {

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
	public static void main(String[] args){
		Random random = new Random();
		System.out.println(random.nextInt(2));
		System.out.println(random.nextInt(2));
		System.out.println(random.nextInt(2));
		System.out.println(random.nextInt(2));
		System.out.println(random.nextInt(2));
		System.out.println(random.nextInt(2));
	}

}
