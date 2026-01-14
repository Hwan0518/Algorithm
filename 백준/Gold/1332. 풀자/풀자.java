import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,v;
	static int[] arr;
	static boolean[][][] visited;


	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

		// bfs
		int res = bfs();

		// result
		System.out.print(res);
	}


	static int bfs() {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(0, arr[0], arr[0], 1));

		visited = new boolean[n][1000 +1][1000 +1]; // i를 풀었을 때 min=j, max=k인 경우

		int minCnt = n;

		// search
		while (!q.isEmpty()) {

			Node solved = q.removeFirst();

			// base condition
			if (solved.max - solved.min >= v) {
				minCnt = Integer.min(minCnt, solved.cnt);
				continue;
			}

			// select
			for (int i=1; i<=2; i++) {

				if (solved.cnt >= minCnt) continue;

				int next = solved.cur +i;
				if (next >= n) continue;

				int nMin = Integer.min(solved.min, arr[next]);
				int nMax = Integer.max(solved.max, arr[next]);

				if (visited[next][nMin][nMax]) continue;
				visited[next][nMin][nMax] = true;

				q.add(new Node(next, nMin, nMax, solved.cnt +1));
			}
		}

		// result
		return minCnt;
	}


	static class Node {

		int cur, min, max, cnt;

		Node(int cur, int min, int max, int cnt) {
			this.cur = cur;
			this.min = min;
			this.max = max;
			this.cnt = cnt;
		}
	}

}