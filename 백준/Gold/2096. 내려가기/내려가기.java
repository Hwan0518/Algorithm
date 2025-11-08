import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[][] arr;
	static int[][] dp;
	static int MAX = 1_000_000;
	static int MIN = -1_000_000;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n][3];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		int max = MIN;
		int min = MAX;

		// dfs
		resetDp();
		for (int i=0; i<3; i++) max = Integer.max(max, dfs(0, i, true));
		resetDp();
		for (int i=0; i<3; i++) min = Integer.min(min, dfs(0, i, false));

		// result
		System.out.print(max + " " + min);
	}


	static int dfs(int depth, int idx, boolean isMax) {

		if (depth == n) return 0;

		if (dp[depth][idx] == -1) {

			int left = isMax ? MIN : MAX;
			int mid = isMax ? MIN : MAX;
			int right = isMax ? MIN : MAX;

			if (idx-1 >= 0) left = dfs(depth + 1, idx - 1, isMax);
			if (idx+1 < 3) right = dfs(depth + 1, idx + 1, isMax);
			mid = dfs(depth + 1, idx, isMax);

			int v = isMax
				? Integer.max(mid, Integer.max(left, right))
				: Integer.min(mid, Integer.min(left, right));

			dp[depth][idx] = arr[depth][idx] + v;
		}

		return dp[depth][idx];
	}


	static void resetDp() {
		dp = new int[n][3];
		for (int i=0; i<n; i++) {
			dp[i][0] = -1;
			dp[i][1] = -1;
			dp[i][2] = -1;
		}
	}


}