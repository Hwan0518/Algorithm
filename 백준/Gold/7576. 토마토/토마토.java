import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int m;
	static int[][] map;
	static List<Node> ripe = new ArrayList<>();
	static int minTime = Integer.MAX_VALUE;
	static int cntNoRipe = 0;
	static int[] dr = { -1, 1, 0, 0};
	static int[] dc = { 0, 0, -1, 1};


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i=0; i<n; i++) {

			int[] row = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			map[i] = row;

			for (int j=0; j<m; j++) {

				if (row[j] == 1) ripe.add(new Node(i, j, 0));
				else if (row[j] == 0) cntNoRipe ++;

			}
		}

		// bfs
		bfs();

		// result
		System.out.print(cntNoRipe != 0 ? -1 : minTime);
	}





	static void bfs() {

		// set-up
		Deque<Node> q = new ArrayDeque<>(ripe);

		// search
		int lastTime = 0;
		while (!q.isEmpty()) {

			Node cur = q.pop();
			int r = cur.r;
			int c = cur.c;
			int t = cur.t;

			lastTime = t;

			for (int i=0; i<4; i++) {

				int nr = r + dr[i];
				int nc = c + dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=m) continue;
				if (map[nr][nc] != 0) continue;

				map[nr][nc] = 1;
				cntNoRipe --;
				q.add(new Node(nr, nc, t+1));

			}
		}

		minTime = Math.min(minTime, lastTime);
	}


	static class Node {

		int r;
		int c;
		int t;

		Node(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

	}

}