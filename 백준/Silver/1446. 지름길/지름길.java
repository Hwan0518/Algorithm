import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, d;
	static Node[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		arr = new Node[n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			arr[i] = new Node(stt, end, cost);
		}

		// setup
		dp = new int[d+1]; // i번째 행에서 사자 위치(3)일때 경우의 수 -> 0: 사자가 없는경우, 1: 왼쪽에있는경우, 2: 오른쪽에 있는경우
		for (int i=0; i<=d; i++) dp[i] = -1;

		// dfs
		int loc = 0;
		int dist = 0;
//		int min = dfs(loc, dist);
		int min = dfs2(loc);

		// result
		System.out.print(min);
	}


	// -> loc에서 D까지 도착하는데 걸리는 최소 거리를 반환
	static int dfs2(int loc) {

		// base-condition
		if (loc == d) return 0;

		// memoization
		if (dp[loc] == -1) {

			// 무조건 +1 증가시켜봄
			int min = dfs2(loc+1) +1; // 거리 1 증가

			// 가능한 지름길 파악
			for (int i=0; i<n; i++) {

				Node shortCut = arr[i];
				if (shortCut.stt != loc || shortCut.end > d) continue;

				min = Integer.min(min, dfs2(shortCut.end)+shortCut.cost); // 거리 cost만큼 증가
			}

			// update
			dp[loc] = min;
		}


		return dp[loc];
	}


	static int dfs(int loc, int dist) {

		// base-condition
		if (loc == d) return dist;

		// search
		int min = Integer.min(10_000, dfs(loc+1, dist+1));

		for (int i=0; i<n; i++) {

			Node shortCut = arr[i];
			if (shortCut.stt != loc || shortCut.end > d) continue;

			min = Integer.min(min, dfs(shortCut.end, dist+shortCut.cost));
		}

		// return min
		return min;
	}


	static class Node {

		int stt, end, cost;

		Node(int stt, int end, int cost) {
			this.stt = stt;
			this.end = end;
			this.cost = cost;
		}
	}

}
