import java.io.*;
import java.util.*;

/**
 *딸기->초코->바나나->딸기
 */
public class Main {
	static int[] seq = {0,1,2,0};
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cur = 2;
		for (int i=0; i<n; i++) {
			int next = Integer.parseInt(st.nextToken());
			cur = check(cur, next);
		}
		System.out.print(cnt);
	}
	
	public static int check(int cur, int next) {
		if (next == seq[cur+1]) {
			cnt ++;
			return seq[cur+1];
		}
		return cur;
	}
	
}