
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int[] t;
	static int[] p;
	static int maxP = 0;
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		t = new int[n+1];
		p = new int[n+1];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		// dfs
		for (int i=0; i<n; i++) {
			dfs(i, 0, 0);
		}
		
		//
		System.out.print(maxP);
	}
	
	
	static void dfs(int day, int before, int curP) {
		if (day >= n) {
			if (day > n) {
				curP -= p[before];
			}
			maxP = Math.max(maxP, curP);
			return;
		}
		for (int d=day; day<=n; day++) {
			int next = day + t[day];
			dfs(next, day, curP+p[day]);
		}
	}
		
}