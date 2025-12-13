import java.io.*;
import java.util.*;

public class Main {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		int catR = Integer.parseInt(st.nextToken());
		int catC = Integer.parseInt(st.nextToken());
		Node cat = new Node(catR, catC);

		Node[] arr = new Node[3];
		for (int i=0; i<3; i++) {

			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			arr[i] = new Node(r, c);
		}

		boolean[] visited = new boolean[3];

//		// dfs
//		int min = dfs(0, cat, visited, arr);

		// 3 nested for-loop
		int min = Integer.MAX_VALUE;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (j == i) continue;

				for (int k=0; k<3; k++) {
					if (k == i || k == j) continue;

					Node cur = cat;
					double cost = 0;

					// first
					cost += Math.sqrt(Math.pow(Math.abs(cur.r - arr[i].r), 2) + Math.pow(Math.abs(cur.c - arr[i].c), 2));
					cur = arr[i];

					// second
					cost += Math.sqrt(Math.pow(Math.abs(cur.r - arr[j].r), 2) + Math.pow(Math.abs(cur.c - arr[j].c), 2));
					cur = arr[j];

					// third
					cost += Math.sqrt(Math.pow(Math.abs(cur.r - arr[k].r), 2) + Math.pow(Math.abs(cur.c - arr[k].c), 2));

					// compare
					min = Integer.min(min, (int) cost);
				}
			}
		}

		// result
		System.out.print(min);
	}


	static int dfs(int cnt, Node loc, boolean[] visited, Node[] arr) {

		// base-condition
		if (cnt == 3) return 0;

		// move
		int[] move = { 1000, 1000, 1000 };

		for (int i=0; i<3; i++) {

			if (visited[i]) continue;
			visited[i] = true;
			Node next = arr[i];

			int cost = (int) Math.sqrt(Math.pow(Math.abs(next.r - loc.r), 2) + Math.pow(Math.abs(next.c - loc.c), 2));
			move[i] = dfs(cnt+1, next, visited, arr) + cost;

			visited[i] = false;
		}

		// return min
		return Arrays.stream(move).min().getAsInt();
	}


	static class Node {

		int r,c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}