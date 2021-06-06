package ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainForXml {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. container 객체생성
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:ApplicationContext.xml");
		
		// 2. bean 을 가져와서 우리가 선언하는 객체에 넣어줍니다.
		Student student = ctx.getBean("student",Student.class);
		student.setDept("컴퓨터소프트웨어과");
		student.setsNum("2017100260");
		student.setName("김기남");
		
		System.out.println(student.toString());
		
		ctx.close();
		
	}

}
