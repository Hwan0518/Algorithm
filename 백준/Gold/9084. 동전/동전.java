import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, cnt, target;
	static int[] coin;
	static int[][] dp;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(br.readLine());
		for (int t=0; t<test; t++) {
			int cnt = sol();
			sb.append(cnt).append("\n");
		}

		// result
		System.out.print(sb);
	}


	static int sol() throws IOException {

		// set up
		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		coin = new int[n];
		for (int i=0; i<n; i++) coin[i] = Integer.parseInt(st.nextToken());

		target = Integer.parseInt(br.readLine());

		dp = new int[target+1][n+1];
		for (int i=0; i<=target; i++) {
			for (int j=0; j<=n; j++) dp[i][j] = -1;
		}

//		// dfs
//		cnt = 0;
//		dfs(0, 0);

		// dp
		return dfs2(0, 0);
	}


	static int dfs2(int cur, int idx) {

		if (idx == n || cur >= target) {
			if (cur == target) return 1;
			else return 0;
		}

		if (dp[cur][idx] == -1) {

			int select = dfs2(cur + coin[idx], idx); // 해당 코인 계속 사용하는 경우의 수
			int nonSelect = dfs2(cur, idx+1); // 다음 코인 사용하는 경우의 수

			dp[cur][idx] = select + nonSelect;
		}

		return dp[cur][idx];
	}


	static void dfs(int idx, int cur) {

		if (idx == n || cur >= target) {
			if (cur == target) cnt ++;
			return;
		}

		// 해당 코인 계속 사용
		dfs(idx, cur + coin[idx]);

		// 다음 코인 사용
		dfs(idx+1, cur);
	}

}
