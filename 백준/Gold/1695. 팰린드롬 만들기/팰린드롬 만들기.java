import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[] arr;
	static int[][] dp;



	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

		dp = new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				dp[i][j] = -1;
			}
		}

		// twoPointer
		int min = dfs(0, n-1);

		// result
		System.out.print(min);
	}

	
	static int dfs(int p1, int p2) {

		if (p1 >= p2) return 0;

		if (dp[p1][p2] == -1) {

			int v1 = arr[p1];
			int v2 = arr[p2];

			int res1 = 5_000;
			int res2 = 5_000;
			int res3 = 5_000;

			if (v1 == v2) {
				res1 = dfs(p1+1, p2-1);
			}
			else {
				res2 = dfs(p1, p2-1) +1;
				res3 = dfs(p1+1, p2) +1;
			}

			dp[p1][p2] = Integer.min(res1, Integer.min(res2, res3));
		}

		return dp[p1][p2];
	}

}