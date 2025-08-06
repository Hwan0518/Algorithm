
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int s;
	static int[] arr;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// dfs
		for (int stt=0; stt<n; stt++) {
			dfs(stt, arr[stt]);
		}

		// result
		System.out.print(cnt);
	}
	
	public static void dfs(int i, int curSum) {
		if (curSum == s) cnt ++;
		if (i == n) {
			return;
		}
		for (int j=i+1; j<n; j++) {
			dfs(j, curSum+arr[j]);
		}
	}
		
}