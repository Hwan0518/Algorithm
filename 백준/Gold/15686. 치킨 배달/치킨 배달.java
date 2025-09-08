import java.io.*;
	import java.util.*;

public class Main {

	static int n;
	static int m;
	static List<Node> chicken;
	static List<Node> home;
	static int chickenCnt;
	static int homeCnt;
	static boolean[] select;
	static int cityDist = Integer.MAX_VALUE;
	static int[] homeDist;
	static int[][] dist;



	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		home = new ArrayList<>();
		chicken = new ArrayList<>();
		for (int i=0; i<n; i++) {

			int[] row = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			for (int j=0; j<n; j++) {
				if (row[j] == 1) home.add(new Node(i, j));
				else if (row[j] == 2) chicken.add(new Node(i, j));

			}
		}
		homeCnt = home.size();
		chickenCnt = chicken.size();

		dist = new int[homeCnt][chickenCnt];
		for (int i=0; i<homeCnt; i++) {
			for (int j=0; j<chickenCnt; j++) {

				Node h = home.get(i);
				Node c = chicken.get(j);
				dist[i][j] = Math.abs(h.r - c.r) + Math.abs(h.c - c.c);

			}
		}

		// dfs
		select = new boolean[chickenCnt];
		dfs(0, 0);

		// result
		System.out.print(cityDist);
	}




	static void dfs(int size, int selectCnt) {

		if (size == chickenCnt || selectCnt == m) {
			if (selectCnt == m) calcCityDist();
			return;
		}

		// non-select
		dfs(size+1, selectCnt);

		// select
		select[size] = true;
		dfs(size+1, selectCnt+1);
		select[size] = false;

	}



	static void calcCityDist() {

		homeDist = new int[homeCnt];
		for (int i=0; i<homeCnt; i++) homeDist[i] = Integer.MAX_VALUE;

		int curCost = 0;
		for (int i=0; i<homeCnt; i++) {

			int minCost = Integer.MAX_VALUE;
			for (int j = 0; j<chickenCnt; j++) {

				if (!select[j]) continue;
				minCost = Math.min(minCost, dist[i][j]);

			}

			curCost += minCost;
			if (curCost >= cityDist) return;
		}

		cityDist = curCost;
	}



	static class Node {

		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}