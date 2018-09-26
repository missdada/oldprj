function getMessage(message_id, para1, para2, para3, para4, para5) {
	var msg = eval(message_id);
	msg = replaceMessage(msg, para1, 0);
	msg = replaceMessage(msg, para2, 1);
	msg = replaceMessage(msg, para3, 2);
	msg = replaceMessage(msg, para4, 3);
	msg = replaceMessage(msg, para5, 4);
	return msg;
}

function replaceMessage(message, rep, index) {
	var result = message;
	if (message == null || rep == null) return result;
	var old = "{" + index + "}";
	var begin = message.indexOf(old);
	if (begin != -1) {
		result = message.substring(0, begin) + rep + message.substring(begin + old.length, message.length);
	}
	return result;
}

MSG_COMMON_I0001 = "未选择记录。";
MSG_COMMON_I0002 = "确定要删除吗?";
MSG_COMMON_I0003 = "{0}不能为空";
MSG_COMMON_I0004 = "{0}不匹配";
MSG_COMMON_I0005 = "{0}成功";
MSG_COMMON_I0006 = "{0}失败。原因：{1}";
MSG_COMMON_I0007 = "确认发布吗？";
MSG_COMMON_I0008 = "确认下架吗？";
MSG_COMMON_I0009 = "将此人加入黑名单？";
MSG_COMMON_I0010 = "将此人移出黑名单？";