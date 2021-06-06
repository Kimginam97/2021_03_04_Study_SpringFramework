package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ys.myapp.RegisterController;
import spring.MemberRegisterService;
import surveyController.SurveyController;

@Configuration
public class ControllerConfig {

	@Autowired
	private MemberRegisterService memberRegSvc;

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
	
}
