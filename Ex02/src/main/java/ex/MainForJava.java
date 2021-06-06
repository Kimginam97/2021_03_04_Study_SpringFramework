package ex;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		Student student = ctx.getBean("student",Student.class);
		student.setDept("컴퓨터소프트웨어과");
		student.setsNum("2017100260");
		student.setName("김기남");
		
		
		System.out.println(student.toString());
		
		ctx.close();
	}

}
