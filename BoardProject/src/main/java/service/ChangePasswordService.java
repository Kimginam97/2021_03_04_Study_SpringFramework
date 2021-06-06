package service;


import org.springframework.transaction.annotation.Transactional;

import domain.Member;
import exception.MemberNotFoundException;
import model.MemberDao;


public class ChangePasswordService {


	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		
		if (member == null) {
			throw new MemberNotFoundException();
		}
		member.changePassword(oldPwd,newPwd);
		
		memberDao.update(member);
	}
	
}
