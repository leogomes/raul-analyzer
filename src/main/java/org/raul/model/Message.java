package org.raul.model;

import java.util.ArrayList;
import java.util.List;

public class Message {

	public enum Severity {
		ERROR, WARNING, INFO
	};

	private Severity severity;

	private String title;

	private String details;

	private List<String> tags;

	static final Message create(Severity severity) {
		return new Message(severity);
	}
	
	private Message(Severity severity) {
		this.severity = severity;
		this.tags = new ArrayList<String>();
	}

	public static Message info() {
		return create(Severity.INFO);
	}
	
	public static Message error() {
		return create(Severity.ERROR);
	}

	public static Message warning() {
		return create(Severity.WARNING);
	}

	
	public Message title(String title) {
		this.title = title;
		return this;
	}
	
	public Message details(String details) {
		this.details = details;
		return this;
	}
	
	public Message addTag(String tag) {
		this.tags.add(tag);
		return this;
	}

	public Severity getSeverity() {
		return severity;
	}

	public String getTitle() {
		return title;
	}

	public String getDetails() {
		return details;
	}

	public List<String> getTags() {
		return tags;
	}

	@Override
	public String toString() {
		return "Message [severity=" + severity + ", title=" + title
				+ ", details=" + details + ", tags=" + tags + "]";
	}
	
	
	
}
