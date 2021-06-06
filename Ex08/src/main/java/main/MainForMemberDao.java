package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.MemberDao;


public class MainForMemberDao {

	private static MemberDao memberDao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		memberDao = ctx.getBean("memberDao",MemberDao.class);
		
		selectAll();
		updateMember();
		insertMember();
		
		ctx.close();
		
	}

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMDDHHmmss");
	private static void insertMember() {
		// TODO Auto-generated method stub
		System.out.println("----- Insert Member");
		
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.com",prefix,prefix,LocalDateTime.now());
		
		memberDao.insert(member);
		
		System.out.println(member.getId() + " 데이터 추가");
		
	}

	private static void updateMember() {
		// TODO Auto-generated method stub
		System.out.println("----- Update Member");
		
		Member member = memberDao.selectByEmail("rlska@naver.com");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw,newPw);
		
		memberDao.update(member);
		
		System.out.println("암호변경: "+oldPw + ">> "+ newPw);
		
	}

	private static void selectAll() {
		// TODO Auto-generated method stub
		System.out.println("----- selectAll");
		
		int total = memberDao.count();
		System.out.println("전체 데이터: "+total);
		
		List<Member> members = memberDao.selectAll();
		
		for (Member m : members) {
			System.out.println(m.getId() + ";" + m.getEmail() + ";" + m.getName());
		}
		
	}

}
