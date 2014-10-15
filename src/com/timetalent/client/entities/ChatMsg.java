
package com.timetalent.client.entities;

public class ChatMsg {
    private static final String TAG = ChatMsg.class.getSimpleName();

    private String name;

    private String date;

    private String text;
    
    private String time;
    
    

    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private boolean isComMeg = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getMsgType() {
        return isComMeg;
    }

    public void setMsgType(boolean isComMsg) {
    	isComMeg = isComMsg;
    }

    public ChatMsg() {
    }

    public ChatMsg(String name, String date, String text, boolean isComMsg) {
        super();
        this.name = name;
        this.date = date;
        this.text = text;
        this.isComMeg = isComMsg;
    }

}
