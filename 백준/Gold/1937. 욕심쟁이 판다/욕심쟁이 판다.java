import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[][] dp;
	static int n;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		dp = new int[n][n]; // i,j에서 이동할 수 있는 최대 move count
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) dp[i][j] = -1;
		}

		// dfs
		int result = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				result = Integer.max(result, dfs(i, j));
			}
		}

		// result
		System.out.print(result);

	}


	// r,c에서 이동할 수 있는 최댓값
	static int dfs(int r, int c) {

		// memoization
		if (dp[r][c] == -1) {

			// 상하좌우
			int[] move = new int[4];

			for (int i=0; i<4; i++) {

				int nr = r+dr[i];
				int nc = c+dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=n) {
					move[i] = -1_000_000_000;
					continue;
				}
				if (map[nr][nc] <= map[r][c]) {
					move[i] = 0;
					continue;
				}

				// move
				move[i] = 1 + dfs(nr, nc);
			}

			// dp 업데이트 (본인을 포함해야하므로 최소 1임)
			dp[r][c] = Integer.max(1, Arrays.stream(move).max().getAsInt());
		}

		return dp[r][c];
	}


}

