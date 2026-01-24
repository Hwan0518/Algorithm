import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[][] dp;
	static final int MOD = 9901;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		// setup
		dp = new int[n+1][3]; // i번째 행에서 사자 위치(3)일때 경우의 수 -> 0: 사자가 없는경우, 1: 왼쪽에있는경우, 2: 오른쪽에 있는경우
		for (int i=0; i<=n; i++) {
			for (int j=0; j<3; j++) dp[i][j] = -1;
		}

		// dfs
		int max = 0;
		for (int i=0; i<3; i++) {
			max += dfs(n, i);
			max = (max % MOD);
		}

		// result
		System.out.print(max);
	}


	static int dfs(int row, int state) {

		// base-condition
		if (row == 1) return 1;

		// memoization
		if (dp[row][state] == -1) {

			int cnt = 0;

			//공통
			cnt = (cnt + dfs(row-1, 0)) % MOD;

			if (state == 0) {
				cnt = (cnt + dfs(row-1, 1)) % MOD;
				cnt = (cnt + dfs(row-1, 2)) % MOD;
			}

			else if (state == 1) {
				cnt = (cnt + dfs(row-1, 2)) % MOD;
			}

			else {
				cnt = (cnt + dfs(row-1, 1)) % MOD;
			}

			dp[row][state] = (cnt%MOD);
		}

		// result
		return dp[row][state];
	}


}
