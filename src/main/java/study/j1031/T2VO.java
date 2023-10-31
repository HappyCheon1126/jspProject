package study.j1031;

public class T2VO {
	private String name;
	private String hakbun;
	private int kor;
	private int eng;
	private int mat;
	private int soc;
	private int sci;
	private int tot;
	private double avg;
	private char grade;
	
	public T2VO(String name, String hakbun, int kor, int eng, int mat, int soc, int sci, int tot, double avg, char grade) {
		this.name = name;
		this.hakbun = hakbun;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.soc = soc;
		this.sci = sci;
		this.tot = tot;
		this.avg = avg;
		this.grade = grade;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHakbun() {
		return hakbun;
	}
	public void setHakbun(String hakbun) {
		this.hakbun = hakbun;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}
	public int getSoc() {
		return soc;
	}
	public void setSoc(int soc) {
		this.soc = soc;
	}
	public int getSci() {
		return sci;
	}
	public void setSci(int sci) {
		this.sci = sci;
	}
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "T2VO [name=" + name + ", hakbun=" + hakbun + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", soc="
				+ soc + ", sci=" + sci + ", tot=" + tot + ", avg=" + avg + ", grade=" + grade + "]";
	}
	
}
