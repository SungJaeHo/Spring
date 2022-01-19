package com.lu.boardapp.board.model.dto;

import java.util.Date;

public class BoardVo {

	@Override
	public String toString() {
		return "BoardVo [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regdate="
				+ regdate + "]";
	}

	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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
}