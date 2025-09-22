import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int n;
	static int[][] map;
	static int[][] dist;
	static int p = 1;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// solution
		n = Integer.parseInt(br.readLine());
		while (n != 0) {
			solution();
			n = Integer.parseInt(br.readLine());
		}

		// result
		System.out.print(sb);

	}


	static void solution() throws IOException {

		// input
		map = new int[n][n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());

		}

		dist = new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) dist[i][j] = Integer.MAX_VALUE;
		}

		// dfs
		dist[0][0] = map[0][0];
		dijkstra(new Node(0,0, map[0][0]));

		// result
		sb.append("Problem ")
			.append(p)
			.append(": ")
			.append(dist[n-1][n-1])
			.append("\n");
		p++;

	}



	static void dijkstra(Node stt) {

		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.v - o2.v);
		q.add(stt);

		while (!q.isEmpty()) {

			Node cur = q.poll();

			if (dist[cur.r][cur.c] < cur.v) continue;

			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=n) continue;

				int nextV = cur.v + map[nr][nc];

				if (nextV < dist[nr][nc]) {

					dist[nr][nc] = nextV;
					q.offer(new Node(nr, nc, nextV));

				}
			}

		}


	}


	static class Node {

		int r;
		int c;
		int v;

		Node(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}

	}



}