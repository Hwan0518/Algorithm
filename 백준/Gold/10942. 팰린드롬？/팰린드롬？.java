import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[][] dp;
	static int n;
	static int[] arr;



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[n+1];
		for (int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());

		dp = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) dp[i][j] = -1;
		}

		// dp search
		for (int len=1; len<=n; len++) {

			int stt = 1;
			int end = stt+len-1;

			while (end <= n) {
				validate(stt, end);
				stt ++;
				end ++;
			}
		}

		// question
		int q = Integer.parseInt(br.readLine());
		for (int i=0; i<q; i++) {

			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			sb.append(dp[s][e]).append("\n");
		}

		// result
		System.out.print(sb);
	}


	static void validate(int s, int e) {

		// memoization
		if (dp[s][e] == -1) {

			// check palindrome
			boolean valid = true;

			for (int i=0; i<(e-s+1)/2; i++) {
				if (arr[s+i] != arr[e-i]) {
					valid = false;
					break;
				}
			}

			dp[s][e] = valid ? 1 : 0;
		}
	}

}

