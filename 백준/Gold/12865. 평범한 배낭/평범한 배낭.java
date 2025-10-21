import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int k;
	static int[] w;
	static int[] v;
	static int max;
	static int[][] dp;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		w = new int[n];
		v = new int[n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[n][k+1];
		for (int i=0; i<n; i++) {
			for (int j=0; j<k+1; j++) dp[i][j] = -1;
		}

		// dfs
//		dfs(0, 0, 0);
//		max = dfs2(0, 0);
		max = dfs3(0, 0);

		// result
		System.out.print(max);
	}


//	static void dfs(int size, int curW, int curV) {
//
//		if (size == n) {
//			max = Integer.max(max, curV);
//			return;
//		}
//
//		// 선택
//		if (curW + w[size] <= k) dfs(size+1, curW+w[size], curV+v[size]);
//
//		// 비선택
//		dfs(size+1, curW, curV);
//	}


	static int dfs2(int size, int curW) {

		if (size == n) {
			if (curW > k) return -10_000_000;
			else return 0;
		}

		// 선택/비선택
		int select = v[size] + dfs2(size+1, w[size] + curW);
		int nonSelect = dfs2(size+1, curW);

		// 결과
		return Integer.max(select, nonSelect);
	}


	static int dfs3(int size, int curW) {

		if (size == n) {
			if (curW > k) return -10_000_000;
			else return 0;
		}

		if (dp[size][curW] == -1) {

			int select = w[size] + curW <= k ? v[size] + dfs3(size+1, w[size] + curW) : -10_000_000;
			int nonSelect = dfs3(size+1, curW);

			dp[size][curW] = Integer.max(select, nonSelect);
		}

		return dp[size][curW];
	}

}
