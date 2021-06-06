package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public MemberDao (DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("EMAIL"),
								rs.getString("PASSWORD"),
								rs.getString("NAME"),
								rs.getTimestamp("REGDATE").toLocalDateTime());
						member.setId(rs.getLong("ID"));
						return member;
					}
				}, email);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		// jdbcTemplate.update(psc, keyHolder)
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pstmt = con.prepareStatement("insert MEMBER(EMAIL, PASSWORD, NAME, REGDATE) "+
						" value(?, ?, ?, ?)",new String[] {"ID"});
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4,Timestamp.valueOf(member.getRegisterDateTime()));
				
				return pstmt;
			}
		},keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	public void update(Member member) {
		jdbcTemplate.update("update Member set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(),member.getPassword(),member.getEmail());

	}
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query("select * from MEMBER",new MemberRowMapper());
		
		return results;
	}
	
	public int count() { // IncorrectResultSizeDataAccessException
		Integer count = jdbcTemplate.queryForObject("select count(*) from member", Integer.class);
		return count;
	}
	
	private RowMapper<Member> memRowMapper = 
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Member member = new Member(rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
				}
			};
			
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where REGDATE between ? and ? " +
						"order by REGDATE desc",
				memRowMapper,
				from, to);
		return results;
	}

	public Member selectById(Long memId) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where ID = ?",
				memRowMapper, memId);

		return results.isEmpty() ? null : results.get(0);
	}
	
	
}

class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Member member = new Member(
				rs.getString("EMAIL"),
				rs.getString("PASSWORD"),
				rs.getString("NAME"),
				rs.getTimestamp("REGDATE").toLocalDateTime()
				);
		member.setId(rs.getLong("ID"));
		return member;
	}	
}
	



