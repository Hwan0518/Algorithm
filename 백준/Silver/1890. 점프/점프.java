import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[][] map;
	static long[][] dp;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// set dp
		dp = new long[n+1][n+1]; // [i,j]에서 [n,n]까지 이동할 수 있는 경로의 수
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) dp[i][j] = -1;
		}


		// dfs
//		long totalRoute = dfs(0,0);
		long totalRoute = dfs2(0,0);

		// result
		System.out.print(totalRoute);
	}


	static long dfs2(int r, int c) {

		if (r==n-1 && c==n-1) return 1; // map[r][c]==0 으로 판단하면 안됨!!!
		if (map[r][c] == 0) return 0; // move가 0이면 자기자신을 호출하므로, 무한재귀 방지를 위한 조건

		// memoization
		if (dp[r][c] == -1) {

			long route = 0;
			int move = map[r][c]; // move가 0이면 자기 자신을 호출하게됨

			// down
			if (r+move < n) route += dfs2(r+move, c);
			// right
			if (c+move < n) route += dfs2(r, c+move);

			// update
			dp[r][c] = route;
		}

		// return dp
		return dp[r][c];
	}


	// 선택지 2개 -> 최악의 경우 모든 칸이1이고 N이100 -> O(2^100) -> dp 필요
	static long dfs(int r, int c) {

		if (map[r][c] == 0) return 1; // 이건 잘못된풀이!!!

		long route = 0;
		int move = map[r][c];

		// right
		if (r+move < n) route += dfs(r+move, c);
		// left
		if (c+move < n) route += dfs(r, c+move);

		// return route
		return route;
	}

}
