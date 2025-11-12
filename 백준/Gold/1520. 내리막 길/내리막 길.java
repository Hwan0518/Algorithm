import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[][] dp, map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		dp = new int[n][m];
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) dp[i][j] = -1;
		}

		// dfs
		int result = dfs(0, 0);

		// result
		System.out.print(result);
	}


	static int dfs(int r, int c) {

		// base condition
		if (r==n-1 && c==m-1) return 1;

		// memoization
		if (dp[r][c] == -1) {

			int curV = map[r][c];
			int[] cntPerDir = new int[4];

			// search
			for (int i=0; i<4; i++) {

				int nr = r + dr[i];
				int nc = c + dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (map[nr][nc] >= curV) continue;

				cntPerDir[i] = dfs(nr, nc);
			}

			// assign
			dp[r][c] = Arrays.stream(cntPerDir).sum();
		}

		return dp[r][c];
	}


}