package member;

public class MemberChatVO {
	private int idx;
	private String nickName;
	private String chat;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
	
	@Override
	public String toString() {
		return "MemberChatVO [idx=" + idx + ", nickName=" + nickName + ", chat=" + chat + "]";
	}
}
