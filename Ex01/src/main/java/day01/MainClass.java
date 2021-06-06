package day01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Student student = ctx.getBean("student",Student.class);
		
		student.setDept("컴퓨터 소프트웨어학과");
		student.setsNum("2017100260");
		student.setName("김기남");
		
		System.out.println(student.toString());
		
		ctx.close();
		
	}

}
