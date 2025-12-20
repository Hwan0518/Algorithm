import java.beans.PropertyEditorManager;
import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws IOException {

		// input
		int n = Integer.parseInt(br.readLine());

		// backtracking
//		int min = dfs(n);

		// dp
		int[] dp = new int[n+1];
		for (int i=0; i<=n; i++) dp[i] = -1;

		int min = dfs2(n, dp);

		// result
		System.out.print(min);
	}


	// dfs : n을 제곱수들의 합으로 표현할 때, 그 항의 최소 갯수를 return
	static int dfs2(int n, int[] dp) {

		// base-condition
		if (n == 0) return 0;
		else if (n == 1) return 1;

		// memoization
		if (dp[n] == -1) {

			// search
			int maxV = (int) Math.sqrt(n);
			int minCnt = 100_000;

			for (int i=maxV; i>0; i--) {

				int remain = n-(i*i);
				if (remain < 0) break; // i는 점점 커지는 방향이므로, 한번 작아지면 탐색을 종료

				minCnt = Integer.min(minCnt, 1 + dfs2(remain, dp));
			}

			// assign
			dp[n] = minCnt;
		}

		// return min
		return dp[n];
	}


	// dfs : n을 제곱수들의 합으로 표현할 때, 그 항의 최소 갯수를 return
	static int dfs(int n) {

		// base-condition
		if (n == 0) return 0;
		if (n == 1) return 1;

		// search
		int maxV = (int) Math.sqrt(n);
		int[] res = new int[maxV+1];
		for (int i=0; i<=maxV; i++) res[i] = 100_000;

		for (int i=1; i<=maxV; i++) {

			int remain = n-(i*i);
			if (remain < 0) break; // i는 점점 커지는 방향이므로, 한번 작아지면 탐색을 종료

			res[i] = 1 + dfs(remain);
		}

		// return min
		return Arrays.stream(res).min().getAsInt();
	}


}