import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static long max;
	static long[][][][] dp;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		dp = new long[n][3][3][2];
		for (int i=0; i<n; i++) {
			for (int j=0; j<3; j++) {
				for (int k=0; k<3; k++) {
					dp[i][j][k][0] = -1;
					dp[i][j][k][1] = -1;
				}
			}
		}

		// dfs
//		dfs(1, 0, false);
//		max = dfs2(1, 0, 0, false);
		max = dfs3(1, 0, 0, 0);

		// result
		System.out.print(max % 1_000_000_007);
	}


	static void dfs(int size, int seq, boolean hasTwo) {

		if (size == n) {
			if (hasTwo) max ++;
			return;
		}

		if (seq <= 1) {
			dfs(size+1, 0, hasTwo);
			dfs(size+1, seq+1, hasTwo);
			dfs(size+1, seq+2, true);
		}

		else if (seq == 2) {
			dfs(size+1, 0, hasTwo);
			dfs(size+1, seq+1, hasTwo);
		}

		else {
			dfs(size+1, 0, hasTwo);
		}
	}


	static int dfs2(int size, int height, int seq, boolean hasTwo) {

		if (size == n) {
			if (hasTwo) return 1; // 조건을 만족한다
			else return 0; // 조건을 만족하지 못한다
		}

		int c1 = 0;
		int c2 = 0;
		int c3 = 0;

		if (seq == 0) {
			c1 = dfs2(size+1, 0, 0, hasTwo);
			c2 = dfs2(size+1, 1, seq+1, hasTwo);
			c3 = dfs2(size+1, 2, seq+1, true);
		}

		else if (seq == 1) {
			c1 = dfs2(size+1, 0, 0, hasTwo);
			if (height <= 2) c2 = dfs2(size+1, 1, seq+1, hasTwo);
			if (height <= 1) c3 = dfs2(size+1, 2, seq+1, true);
		}

		else {
			c1 = dfs2(size+1, 0, 0, hasTwo);
		}

		return c1 + c2 + c3;
	}


	static long dfs3(int size, int height, int seq, int hasTwo) {

		if (size == n) {
			if (hasTwo > 0) return 1; // 조건을 만족한다
			else return 0; // 조건을 만족하지 못한다
		}

		// memoization
		if (dp[size][height][seq][hasTwo] != -1) return dp[size][height][seq][hasTwo];

		long c1 = 0;
		long c2 = 0;
		long c3 = 0;

		if (seq == 0) {
			c1 = dfs3(size+1, 0, 0, hasTwo);
			c2 = dfs3(size+1, 1, seq+1, hasTwo);
			c3 = dfs3(size+1, 2, seq+1, 1);
		}

		else if (seq == 1) {
			c1 = dfs3(size+1, 0, 0, hasTwo);
			if (height <= 2) c2 = dfs3(size+1, 1, seq+1, hasTwo);
			if (height <= 1) c3 = dfs3(size+1, 2, seq+1, 1);
		}

		else {
			c1 = dfs3(size+1, 0, 0, hasTwo);
		}

		dp[size][height][seq][hasTwo] = (c1 + c2 + c3) % 1_000_000_007;
		return dp[size][height][seq][hasTwo];
	}

}
