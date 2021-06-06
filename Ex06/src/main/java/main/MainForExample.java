package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Client;

public class MainForExample {

	public static void main(String[] args) {
		
		// 1. 컨테이너 객체 생성(no memory)
		AnnotationConfigApplicationContext ctx;
		
		// 2. 컨테이너 객체 초기화(with memory)
		// 2.1 Bean 생성
		// 2.2 Bean 활용
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		// 3. 컨테이너 활용
		Client client1 = ctx.getBean("client",Client.class);
		client1.send();
		
		// 4. 컨테이너 소멸
		// 4.1 Bean 소멸
		ctx.close();
		
	}

}
