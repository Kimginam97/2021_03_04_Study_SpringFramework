package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import sday04.Calculator;
import sday04.RecCalculator;


@Configuration
@EnableAspectJAutoProxy
public class AppCtx {
	
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	

	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
}
