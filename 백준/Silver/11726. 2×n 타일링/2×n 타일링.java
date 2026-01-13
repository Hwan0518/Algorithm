import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] dp;
	static int MOD = 10_007;


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		// set-up
		dp = new int[n+1]; // i번째를 1*2로 채우거나 2*1로 채울 수 있는 경우의 수
		for (int i=0; i<=n; i++) dp[i] = -1;

		// dfs
		int res = dfs(n);

		// result
		System.out.print(res);
	}


	// dfs -> size번째를 type으로 채웠을 때의 경우의 수
	static int dfs(int size) {

		// base-condition
		if (size == 1 || size == 0) return 1;

		// memoization
		if (dp[size] == -1) {

			int res1 = 0;
			int res2 = 0;

			// 1*2로 채우는 경우의 수
			res1 = dfs(size-1) % MOD;

			// 2*1로 채우는 경우의 수
			res2 = dfs(size-2) % MOD;

			// assign
			dp[size] = (res1+res2) % MOD;
		}

		// res
		return dp[size];
	}

}