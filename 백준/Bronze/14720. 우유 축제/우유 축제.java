import java.io.*;
import java.util.*;

/**
 *딸기->초코->바나나->딸기
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cur = 2;
		int cnt = 0;
		for (int i=0; i<n; i++) {
			int next = Integer.parseInt(st.nextToken());
			switch (cur) {
			case 0 : {
				if (next == 1) {
					cur = 1;
					cnt++; 
				}
				break;
			}
			case 1 : {
				if (next == 2) {
					cur = 2;
					cnt++;
				}
				break;
			}
			case 2 : {
				if (next == 0) {
					cur = 0;
					cnt++;
				}
				break;
				}
			}
		}
		System.out.print(cnt);
	}
	
}