import java.io.*;
import java.util.*;


/**
 * 중간 계산이 0 이상 20 이하가 되어야한다
 * -,+ 둘 다 0이상 20이하가 된다면 경우의수 더하기 ㄱㄱ
 * i == n-1되면 종료 (base condition)
 * dfs to dp로 가자
 */
public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static long cnt;
	static int[] arr;
	static long[][] dp;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

		// dfs1
//		dfs1(0, arr[0]);

		// dfs2
		dp = new long[28][n]; // dp[i][j] -> i에서 j번째 수를 더하거나 뺐을때 경우의 수
		for (int i=0; i<28; i++) {
			for (int j=0; j<n; j++) dp[i][j] = -1;
		}

		cnt = dfs2(0, arr[0]);

		// result
		System.out.print(cnt);
	}


	static long dfs2(int i, int cur) {

		if (i == n-2) {
			if (cur == arr[n-1]) return 1;
			else return 0;
		}

		if (dp[cur][i] == -1) {

			// plus
			long cnt1 = 0;
			int plus = cur + arr[i+1];
			if (plus >= 0 && plus <= 20) cnt1 = dfs2(i+1, plus);

			// minus
			long cnt2 = 0;
			int minus = cur - arr[i+1];
			if (minus >= 0 && minus <= 20) cnt2 = dfs2(i+1, minus);

			dp[cur][i] = cnt1 + cnt2;
		}

		return dp[cur][i];
	}


	static void dfs1(int i, int cur) {

		if (i == n-2) {
			if (cur == arr[n-1]) cnt++;
			return;
		}

		// plus
		int plus = cur + arr[i+1];
		if (plus >= 0 && plus <= 20) dfs1(i+1, plus);

		// minus
		int minus = cur - arr[i+1];
		if (minus >= 0 && minus <= 20) dfs1(i+1, minus);
	}


}
