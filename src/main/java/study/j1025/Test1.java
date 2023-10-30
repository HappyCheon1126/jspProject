package study.j1025;

public class Test1 {
	public static void main(String[] args) {
		int i=0, tot=0;
		
		while(i<100) {
			i++;
			tot += i;
			System.out.println("1~100까지의 합은? " + tot + " 입니다.");
		}
	}
}
