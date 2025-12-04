import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	static int n;
	static Node[] arr;
	static Node[][] dp;

	public static void main(String[] args) throws IOException {

		// input
		int n = Integer.parseInt(br.readLine());

		arr = new Node[n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			arr[i] = new Node(a, b, 0);
		}

		dp = new Node[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) dp[i][j] = new Node(i, j, -1);
		}

		// dfs
		Node min;
//		min = dfs1(0, n-1);
		min = dfs2(0, n-1);

		// result
		System.out.print(min.cnt);
	}


	// dp[stt][end] -> stt부터 end까지의 행렬을 모두 곱할 때, 곱셈 연산 횟수의 최솟값
	static Node dfs2(int stt, int end) {

		// base-condition -> 자기 자신의 곱셈에 필요한 횟수는 0, 바로 옆이라면 곱셈 횟수 return
		if (stt == end) return arr[stt];
		else if (end-stt == 1) return new Node(arr[stt].r, arr[end].c, arr[stt].r * arr[stt].c * arr[end].c);

		// memoization
		if (dp[stt][end].cnt == -1) {

			// 최솟값 구하기
			Node min = new Node(0, 0, Integer.MAX_VALUE);

			for (int mid = stt; mid < end; mid++) {

				Node res1 = dfs2(stt, mid);
				Node res2 = dfs2(mid + 1, end);
				int res3Cnt = res1.r * res1.c * res2.c;
				
				int finalCnt = res1.cnt + res2.cnt + res3Cnt;
				Node finalRes = new Node(res1.r, res2.c, finalCnt);

				if (finalRes.cnt < min.cnt) min = finalRes;
			}

			dp[stt][end] = min;
		}

		// 결과
		return dp[stt][end];

	}



	// dfs(stt, end) -> stt부터 end까지의 행렬을 모두 곱할 때, 곱셈 연산 횟수의 최솟값
	static Node dfs1(int stt, int end) {

		// base-condition -> 자기 자신의 곱셈에 필요한 횟수는 0, 바로 옆이라면 곱셈 횟수 return
		if (stt == end) return arr[stt];
		else if (end-stt == 1) return new Node(arr[stt].r, arr[end].c, arr[stt].r * arr[stt].c * arr[end].c);

		// 최솟값 구하기
		Node min = new Node(0, 0, 1_000_000_000);
		for (int mid=stt; mid<end; mid++) {

			Node res1 = dfs1(stt, mid);
			Node res2 = dfs1(mid+1, end);
			int res3Cnt = res1.r * res1.c * res2.c;

			int finalCnt = res1.cnt + res2.cnt + res3Cnt;
			Node finalRes = new Node(res1.r, res2.c, finalCnt);

			if (finalRes.cnt < min.cnt) min = finalRes;
		}

		// 결과
		return min;
	}




	static class Node {

		int r, c, cnt;

		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

}
