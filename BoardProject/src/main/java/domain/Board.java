package domain;

import java.time.LocalDateTime;

public class Board {
	private Long id;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime registerDateTime;
	
	public Board(String title, String content, String writer, LocalDateTime registerDateTime) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.registerDateTime = registerDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}

	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	
	
	

}
