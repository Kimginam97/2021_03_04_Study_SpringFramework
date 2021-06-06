package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
public class AppCtx {

	/*
	 * class 는 메모리를 참조한다 , 4바이트를 준다
	 * 
	 * 에러
	 * NoSuchBeanDefinition : 타자오류
	 * NullPointerException : 메모리를 할당하지않은 상태에서 메모리를 쓴다고하면 생긴다
	 * 
	 */
	@Bean	// 객체   메소드 자체 이름이 빈의 이름이 된다
	public MemberDao memberDao() {	// 아무 클래스도 의존하지 않는 객체 생성시
		return new MemberDao();
	}
	
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());	// memberDao 객체를 넣어준다
	}
	
	@Bean	// setter에 의해서 구성되는 bean
	public ChangePasswordService changePwdSvc() {	// memberDao 객체에 의존하고 있는 bean 생성시
		ChangePasswordService pwdSvc = new ChangePasswordService();
		//pwdSvc.setMemberDao(memberDao());
		return pwdSvc;	// 리턴된 참조이름으로 간다
	}
	
	//생성자 주입
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberPrinter memberPrinter2() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
	
	// setter 주입
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setMemberPrinter(memberPrinter());
		return infoPrinter;
	}
	
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		//versionPrinter.setMajorVersion(5);
		//versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
	
	
}
