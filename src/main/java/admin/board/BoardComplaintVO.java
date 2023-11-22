package admin.board;

public class BoardComplaintVO {
	private int idx;
	private String part;
	private int partIdx;
	private String cpMid;
	private String cpContent;
	private String cpDate;
	
	private String name;
	private String title;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public int getPartIdx() {
		return partIdx;
	}
	public void setPartIdx(int partIdx) {
		this.partIdx = partIdx;
	}
	public String getCpMid() {
		return cpMid;
	}
	public void setCpMid(String cpMid) {
		this.cpMid = cpMid;
	}
	public String getCpContent() {
		return cpContent;
	}
	public void setCpContent(String cpContent) {
		this.cpContent = cpContent;
	}
	public String getCpDate() {
		return cpDate;
	}
	public void setCpDate(String cpDate) {
		this.cpDate = cpDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "BoardComplaintVO [idx=" + idx + ", part=" + part + ", partIdx=" + partIdx + ", cpMid=" + cpMid
				+ ", cpContent=" + cpContent + ", cpDate=" + cpDate + ", name=" + name + ", title=" + title + "]";
	}
}
