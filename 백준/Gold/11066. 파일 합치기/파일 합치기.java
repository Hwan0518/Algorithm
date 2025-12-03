import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	static int k;
	static int[] arr, prefixSum;
	static int[][] dp;

	public static void main(String[] args) throws IOException {

		// test case
		int testCase = Integer.parseInt(br.readLine());

		for (int t=0; t<testCase; t++) {

			// input
			k = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			arr = new int[k];
			for (int i=0; i<k; i++) arr[i] = Integer.parseInt(st.nextToken());

			dp = new int[k][k];
			for (int i=0; i<k; i++) {
				for (int j=0; j<k; j++) dp[i][j] = -1;
			}

			// prefix sum
			findPrefixSum();

			// dfs
			int min = dfs(0, k-1);

			// result
			sb.append(min).append("\n");
		}

		// ans
		System.out.print(sb);
	}


	// dp[stt][end] -> stt file부터 end file까지 최소합
	static int dfs(int stt, int end) {

		// base condition -> 단일 파일은 합칠 필요 없음
		if (stt==end) return 0;

		// memoization
		if (dp[stt][end] == -1) {

			int min = Integer.MAX_VALUE;
			for (int mid = stt; mid < end; mid++) {

				// 구간 별 합
				int sum1 = dfs(stt, mid);
				int sum2 = dfs(mid + 1, end);

				// 전체 비용
				int cost = sum1 + sum2 + sum(stt, end); // stt~end파일까지 모두 합치는 비용

				// 최솟값 갱신
				min = Integer.min(min, cost);
			}

			// dp 갱신
			dp[stt][end] = min;
		}

		return dp[stt][end];
	}


	static int sum(int stt, int end) {
		return prefixSum[end]-prefixSum[stt]+arr[stt];
	}


	static void findPrefixSum() {

		prefixSum = new int[k];

		// calc
		prefixSum[0] = arr[0];
		for (int i=1; i<k; i++) prefixSum[i] = prefixSum[i-1] + arr[i];
	}
}
