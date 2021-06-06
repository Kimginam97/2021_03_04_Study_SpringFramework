package model;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import domain.Board;


@Repository
public class BoardDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BoardDao (DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public Board selectByTitle(String title) {
		List<Board> results = jdbcTemplate.query(
				"select * from BOARD where TITLE = ?",
				new RowMapper<Board>() {
					@Override
					public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
						Board board = new Board(
								rs.getString("TITLE"),
								rs.getString("CONTENT"),
								rs.getString("WRITER"),
								rs.getTimestamp("REGDATE").toLocalDateTime());
						board.setId(rs.getLong("ID"));
						return board;
					}
				}, title);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public void insert(final Board board) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		// jdbcTemplate.update(psc, keyHolder)
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement pstmt = con.prepareStatement("insert BOARD(TITLE, CONTENT, WRITER, REGDATE) "+
						" value(?, ?, ?, ?)",new String[] {"ID"});
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setString(3, board.getWriter());
				pstmt.setTimestamp(4,Timestamp.valueOf(board.getRegisterDateTime()));
				
				return pstmt;
			}
		},keyHolder);
		Number keyValue = keyHolder.getKey();
		board.setId(keyValue.longValue());
	}
	
	public void update(Board board) {
		jdbcTemplate.update("update Board set TITLE = ?, CONTENT = ?, WRITER = ? where ID = ?",
				board.getTitle(),board.getContent(),board.getWriter(),board.getId());

	}
	
	public void delete(Long boardId){
	      String SQL = "delete from BOARD where id = ?";
	      jdbcTemplate.update(SQL, boardId);
	   }
	
	
	public List<Board> selectAll(){
		List<Board> results = jdbcTemplate.query("select * from BOARD",new BoardRowMapper());
		
		return results;
	}
	
	public Board selectById(Long boardId) {
		List<Board> results = jdbcTemplate.query(
				"select * from BOARD where ID = ?",
				boardRowMapper, boardId);

		return results.isEmpty() ? null : results.get(0);
	}
	
	private RowMapper<Board> boardRowMapper = 
			new RowMapper<Board>() {
				@Override
				public Board mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Board board = new Board(rs.getString("TITLE"),
							rs.getString("CONTENT"),
							rs.getString("WRITER"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					board.setId(rs.getLong("ID"));
					return board;
				}
			};
}

class BoardRowMapper implements RowMapper<Board>{

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Board board = new Board(
				rs.getString("TITLE"),
				rs.getString("CONTENT"),
				rs.getString("WRITER"),
				rs.getTimestamp("REGDATE").toLocalDateTime()
				);
		board.setId(rs.getLong("ID"));
		return board;
	}	
}
	
	



