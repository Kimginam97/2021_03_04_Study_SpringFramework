package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ys.myapp.ChangePwdController;
import com.ys.myapp.LoginController;
import com.ys.myapp.LogoutController;
import com.ys.myapp.MemberDetailController;
import com.ys.myapp.MemberListController;
import com.ys.myapp.RegisterController;

import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
import surveyController.SurveyController;

@Configuration
public class ControllerConfig {

	@Autowired
	private MemberRegisterService memberRegSvc;
	@Autowired
	private AuthService authService;
	@Autowired
	private ChangePasswordService changePasswordService;
	@Autowired
	private MemberDao memberDao;

	@Bean
	public RegisterController registerController() {
		RegisterController controller = new RegisterController();
		controller.setMemberRegisterService(memberRegSvc);
		return controller;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
	
	@Bean
	public LoginController loginController() {
		LoginController controller = new LoginController();
		controller.setAuthService(authService);
		return controller;
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean
	public ChangePwdController changePwdController() {
		ChangePwdController controller = new ChangePwdController();
		controller.setChangePasswordService(changePasswordService);
		return controller;
	}
	
	@Bean
	public MemberListController memberListController() {
		MemberListController controller = new MemberListController();
		controller.setMemberDao(memberDao);
		return controller;
	}
	
	@Bean
	public MemberDetailController memberDetailController() {
		MemberDetailController controller = new MemberDetailController();
		controller.setMemberDao(memberDao);
		return controller;
	}
	
}