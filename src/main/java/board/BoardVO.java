package board;

public class BoardVO {
  private int idx;
  private String mid;
  private String nickName;
  private String title;
  private String email;
  private String homePage;
  private String content;
  private int readNum;
  private String hostIp;
  private String openSw;
  private String wDate;
  private int good;
  
  private int hour_diff;	 // 게시글 24시간 동안 new.gif 이미지 표시를 위한 변수
  private int date_diff;   // 게시글이 1일 이전인지를 체크하기위한 변수
  
  private int replyCnt;		// 댓글의갯수를 저장하는 변수
  
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getOpenSw() {
		return openSw;
	}
	public void setOpenSw(String openSw) {
		this.openSw = openSw;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getHour_diff() {
		return hour_diff;
	}
	public void setHour_diff(int hour_diff) {
		this.hour_diff = hour_diff;
	}
	public int getDate_diff() {
		return date_diff;
	}
	public void setDate_diff(int date_diff) {
		this.date_diff = date_diff;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	
	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", mid=" + mid + ", nickName=" + nickName + ", title=" + title + ", email=" + email
				+ ", homePage=" + homePage + ", content=" + content + ", readNum=" + readNum + ", hostIp=" + hostIp
				+ ", openSw=" + openSw + ", wDate=" + wDate + ", good=" + good + ", hour_diff=" + hour_diff + ", date_diff="
				+ date_diff + ", replyCnt=" + replyCnt + "]";
	}
}
