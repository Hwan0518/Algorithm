import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] arr;
	static int[][] dp;

	/*
	다음의 두 가지 경우의 수로 구한다
		- 한 칸 떨어진 계단을 밟는 경우
		- 두 칸 떨어진 계단을 밟는 경우
	다음칸으로 이동할 때, 현재 얼마나 연속적인지를 같이 넘겨준다
	현재칸 > 마지막칸 -> return -3_000_001;
	현재칸 == 마지막칸 -> return 0;

	dp[i][j] -> i번에 도착했을때, j번 연속일때의 점수 최댓값
	 */

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		arr = new int[1+ n];
		for (int i=1; i<=n; i++) arr[i] = Integer.parseInt(br.readLine());

		// set-up
		dp = new int[1+n][4];
		for (int i=0; i<=n; i++) {
			for (int j=0; j<4; j++) dp[i][j] = -1;
		}

		// dfs
		int max = dfs(0, 0);

		// result
		System.out.print(max);
	}


	// dfs -> i만큼 이동해서 j번에 도착했을때, k번 연속인 경우의 최댓값을 return
	static int dfs(int stair, int cnt) {

		// base-condition
		if (cnt == 3) return -3_000_001;
		if (stair == n) return 0;

		// dp
		if (dp[stair][cnt] == -1) {

			int max = -3_000_001;

			// 한 계단만 이동
			if (stair+1 <= n) max = Integer.max(max, dfs( stair+1, cnt+1) + arr[stair+1]);

			// 두 계단 이동
			if (stair+2 <= n) max = Integer.max(max, dfs(stair+2, 1) + arr[stair+2]);

			// assign
			dp[stair][cnt] = max;
		}

		// return max
		return dp[stair][cnt];
	}
}
