package study2.apiTest;

public class CrimeVO {
	private int idx;
	private String police;
	private int year;
	private int robbery;
	private int murder;
	private int theft;
	private int violence;
	
	private int totRobbery;
	private int totMurder;
	private int totTheft;
	private int totViolence;
	private int avgRobbery;
	private int avgMurder;
	private int avgTheft;
	private int avgViolence;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRobbery() {
		return robbery;
	}
	public void setRobbery(int robbery) {
		this.robbery = robbery;
	}
	public int getMurder() {
		return murder;
	}
	public void setMurder(int murder) {
		this.murder = murder;
	}
	public int getTheft() {
		return theft;
	}
	public void setTheft(int theft) {
		this.theft = theft;
	}
	public int getViolence() {
		return violence;
	}
	public void setViolence(int violence) {
		this.violence = violence;
	}
	public int getTotRobbery() {
		return totRobbery;
	}
	public void setTotRobbery(int totRobbery) {
		this.totRobbery = totRobbery;
	}
	public int getTotMurder() {
		return totMurder;
	}
	public void setTotMurder(int totMurder) {
		this.totMurder = totMurder;
	}
	public int getTotTheft() {
		return totTheft;
	}
	public void setTotTheft(int totTheft) {
		this.totTheft = totTheft;
	}
	public int getTotViolence() {
		return totViolence;
	}
	public void setTotViolence(int totViolence) {
		this.totViolence = totViolence;
	}
	public int getAvgRobbery() {
		return avgRobbery;
	}
	public void setAvgRobbery(int avgRobbery) {
		this.avgRobbery = avgRobbery;
	}
	public int getAvgMurder() {
		return avgMurder;
	}
	public void setAvgMurder(int avgMurder) {
		this.avgMurder = avgMurder;
	}
	public int getAvgTheft() {
		return avgTheft;
	}
	public void setAvgTheft(int avgTheft) {
		this.avgTheft = avgTheft;
	}
	public int getAvgViolence() {
		return avgViolence;
	}
	public void setAvgViolence(int avgViolence) {
		this.avgViolence = avgViolence;
	}
	
	@Override
	public String toString() {
		return "CrimeVO [idx=" + idx + ", police=" + police + ", year=" + year + ", robbery=" + robbery + ", murder="
				+ murder + ", theft=" + theft + ", violence=" + violence + ", totRobbery=" + totRobbery + ", totMurder="
				+ totMurder + ", totTheft=" + totTheft + ", totViolence=" + totViolence + ", avgRobbery=" + avgRobbery
				+ ", avgMurder=" + avgMurder + ", avgTheft=" + avgTheft + ", avgViolence=" + avgViolence + "]";
	}
}
