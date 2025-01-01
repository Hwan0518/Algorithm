import java.io.*;
import java.util.*;

/**
 * 100을 기준으로 앞,뒤 점수만 보면 된다
 * 따라서 100을 넘겼을 때를 trigger로 보자
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = 0;
		for (int i=0; i<10; i++) {
			int cur = Integer.parseInt(br.readLine()); 
			s += cur;
			if (s >=100) {
				int right = s-100;
				int left = 100-(s-cur);
				if (right == left) {
					System.out.print(s);
					System.exit(0);
				} else {
					System.out.print(right>left ? s-cur : s);
					System.exit(0);
				}	
			}
		}
		System.out.print(s);
	}
	
}