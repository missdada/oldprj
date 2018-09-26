package manytag.framework.dispatch.base;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;

public class Message {
	public static final String MESSAGE_TYPE_INFO = "INFO";
	public static final String MESSAGE_TYPE_WARNING = "WARNING";
	public static final String MESSAGE_TYPE_ERROR = "ERROR";
	public static final String MESSAGE_TYPE_SUCCESS = "SUCCESS";

	private String messageType;
	private String messageId = "";
	private String message = "";

	public Message(String message) {
		this.message = message;
	}

	public Message(String msgtype, String msgId, String[] param) {
		MessageSource ms = ApplicationContext.getBean("message-resources", MessageSource.class);
		String msg = ms.getMessage(msgId, param, Locale.getDefault());
		this.messageType = msgtype;
		this.messageId = msgId;
		this.message = msg;
	}

	public Message(String msgtype, String msgId, String[] param, String causeId) {
		MessageSource ms = ApplicationContext.getBean("message-resources", MessageSource.class);
		String msg = ms.getMessage(msgId, param, Locale.getDefault());
		String cause = ms.getMessage(causeId, param, Locale.getDefault());
		msg = MessageFormat.format(msg, cause);
		this.messageType = msgtype;
		this.messageId = msgId;
		this.message = msg;
	}

	public Message(String msgType, String msgId, String msg) {
		this.messageType = msgType;
		this.messageId = msgId;
		this.message = msg;
	}

	public Message(String msgType, String msgId) {
		this.messageType = msgType;
		this.messageId = msgId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.messageId + ": " + this.message;
	}
}