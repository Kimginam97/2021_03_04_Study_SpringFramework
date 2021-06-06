package config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import controller.BoardController;
import model.BoardDao;
import model.MemberDao;
import service.AuthService;
import service.BoardService;
import service.ChangePasswordService;
import service.MemberRegisterService;


@Configuration
@EnableTransactionManagement
public class BoardConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?serverTimezone=UTC&characterEncoding=utf8");
		ds.setUsername("root");
		ds.setPassword("1234");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000*3);
		ds.setTimeBetweenEvictionRunsMillis(10*1000);
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}

	@Bean
	public BoardDao boardDao() {
		return new BoardDao(dataSource());
	}
	
	@Bean
	public BoardService boardService() {
		return new BoardService(boardDao());
	}
	
	@Bean
	public BoardController boardController() {
		return new BoardController(boardService());
	}

	
}
