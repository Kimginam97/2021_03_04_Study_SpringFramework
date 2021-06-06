package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;
import spring.WrongIdPasswordException;

public class MainForSpring {

	private static ApplicationContext ctx = null;	// AnnotationConfigApplicationContext 부모클래스
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);	// ApplicationContext 자식클래스
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("명령어를 입력하세요: ");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if (command.equals("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}

	}
	
	private static void processVersionCommand() {
		// TODO Auto-generated method stub
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter",VersionPrinter.class);
		versionPrinter.print();
	}

	// 이메일 단건조회
	private static void processInfoCommand(String[] arg) {
		// TODO Auto-generated method stub
		if(arg.length != 2) {
			printHelp();
			return;
		}
		
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		infoPrinter.printMemberInfor(arg[1]);
		
	}

	// 모든 맴버 조회
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}

	// 맴버 등록
	private static void processNewCommand(String[] arg) {
		// TODO Auto-generated method stub
		if(arg.length !=5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 암호확인이 일치하지 않습니다.");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.");
		}catch (DuplicateMemberException e) {
			// TODO: handle exception
			System.out.println("이미 존재하는 이메일입니다.");
		}
		
		
	}

	// 맴버 비밀번호 변경
	private static void processChangeCommand(String[] arg) {
		// TODO Auto-generated method stub
		if(arg.length !=4) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다.");
		}catch (MemberNotFoundException e) {
			// TODO: handle exception
			System.out.println("존재하지 않는 이메일 입니다.");
		}catch (WrongIdPasswordException e) {
			// TODO: handle exception
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
		
	}
	
	// 도움말
	private static void printHelp() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("잘못된 명령어입니다. 아래 명령어 사용법을 확인하세요");
		System.out.println("명령어 사용법:");
		System.out.println("new 이메일, 이름, 암호, 암호확인");
		System.out.println("change 이메일, 현재비번, 변경비번");
		System.out.println("list");
		System.out.println("info 이메일");
		System.out.println("version");
		System.out.println();
	}

}
