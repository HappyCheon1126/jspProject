package admin.review;

public class ReviewVO {
	private int idx;
	private String part;
	private int partIdx;
	private String mid;
	private int star;
	private String content;
	private String rDate;
	
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
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [idx=" + idx + ", part=" + part + ", partIdx=" + partIdx + ", mid=" + mid + ", star=" + star
				+ ", content=" + content + ", rDate=" + rDate + "]";
	}
}
