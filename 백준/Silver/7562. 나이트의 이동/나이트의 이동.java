import java.io.*;
import java.util.*;




public class Main {

	static int n;
	static int t;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int cnt;
	static boolean[][] visited;
	static int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };



	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());

		// solution
		for (int i=0; i<t; i++) solution();

		// result
		System.out.print(sb);

	}



	static void solution() throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		Node stt = new Node(
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken()),
			0);

		st = new StringTokenizer(br.readLine());
		Node target = new Node(
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken()),
			0);

		// bfs
		cnt = 0;
		visited = new boolean[n][n];
		bfs(stt, target);

		// result
		sb.append(cnt).append("\n");
	}



	static void bfs(Node stt, Node target) {

		Deque<Node> q = new ArrayDeque<>();
		q.add(stt);
		visited[stt.r][stt.c] = true;

		while (!q.isEmpty()) {

			Node cur = q.pop();

			if (cur.r == target.r && cur.c == target.c) {
				cnt = cur.move;
				return;
			}

			for (int i=0; i<8; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
				if (visited[nr][nc]) continue;
				visited[nr][nc] = true;

				q.add(new Node(nr, nc, cur.move + 1));
			}
		}
	}



	static class Node {

		int r;
		int c;
		int move;

		Node(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}

	}


}
