import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int t, n, cnt;
	static int[] dp;


	public static void main(String[] args) throws IOException {

		// input
		t = Integer.parseInt(br.readLine());

		// search
		for (int test=0; test<t; test++) {

			// set-up
			n = Integer.parseInt(br.readLine());

			dp = new int[n+1]; // n을 만들 수 있는 경우의 수
			for (int i=0; i<=n; i++) dp[i] = -1;

			// dfs
			cnt = dfs(n);

			// result
			sb.append(cnt).append("\n");
		}

		// ans
		System.out.println(sb);
	}



	static int dfs(int cur) {

		// base-condition
		if (cur == 1) return 1;
		else if (cur == 2) return 2;
		else if (cur == 3) return 4;
		else if (cur == 4) return 7;

		// search
		if (dp[cur] == -1) {

			int minus1 = 0, minus2 = 0, minus3 = 0;

			if (cur-1 >=0) minus1 = dfs(cur-1);
			if (cur-2 >=0) minus2 = dfs(cur-2);
			if (cur-3 >=0) minus3 = dfs(cur-3);

			// assign
			dp[cur] = minus1 + minus2 + minus3;
		}

		// result
		return dp[cur];
	}
}