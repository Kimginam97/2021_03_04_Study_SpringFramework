package service;

import domain.Member;
import domain.dto.AuthInfo;
import exception.WrongIdPasswordException;
import model.MemberDao;

public class AuthService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public AuthInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if (member == null) {
			throw new WrongIdPasswordException();
		}
		if (!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		return new AuthInfo(member.getId(),member.getEmail(),member.getName());
	}
}
