package com.techblog.entities;

public class Message {

	private String content;
	private String type;
	private String cssClass;
	public Message(String content, String type, String cssClass) {
		super();
		this.content = content;
		this.type = type;
		this.cssClass = cssClass;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCssClass() {
		return cssClass;
	}
	
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
}
