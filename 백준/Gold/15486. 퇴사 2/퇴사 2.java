import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int[] t;
	static int[] p;
	static int max;
	static int[] dp;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		t = new int[n + 1];
		p = new int[n + 1];

		for (int i=1; i<n+1; i++) {

			st = new StringTokenizer(br.readLine());

			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[n + 1];
		for (int i=0; i<n+1; i++) dp[i] = -1;

		// 재귀
//		max = returnVersion(1);
//		backtrackingVersion(1, 0);
		max = dfs(1);

		// result
		System.out.print(max);
	}


	static int dfs(int day) {

		if (day > n+1) return Integer.MIN_VALUE;
		if (day == n+1) return 0;

		if (dp[day] == -1) {
			dp[day] = Integer.max(dfs(day + 1), dfs(day + t[day]) + p[day]);
		}

		return dp[day];
	}


	static void backtrackingVersion(int day, int totalIncome) {

		if (day > n+1) return;
		if (day == n+1) {
			max = Math.max(max, totalIncome);
			return;
		}

		// 선택
		backtrackingVersion(day + t[day], totalIncome + p[day]);
		// 비선택
		backtrackingVersion(day + 1, totalIncome);

	}

	static int returnVersion(int day) {

		if (day > n+1) return Integer.MIN_VALUE;
		if (day == n+1) return 0;

		return Math.max(returnVersion(day+1), returnVersion(day+t[day]) + p[day]);

	}

}
