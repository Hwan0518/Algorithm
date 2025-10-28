import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = { -1, 1, 0, 0, -1, 1, 1, -1 };
	static int[] dc = { 0, 0, -1, 1, 1, 1, -1, -1 };


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[19][19];
		for (int i=0; i<19; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[19][19][8];

		// bfs
		Node result = new Node(0, 0, 0, -1);

		loop: for (int r=0; r<19; r++) {
			for (int c=0; c<19; c++) {
				for (int dir=0; dir<8; dir++) {

					if (map[r][c] == 0) continue;
					if (visited[r][c][dir]) continue;

					visited[r][c][dir] = true;

					Node stt = new Node(r, c, 1, dir);
					Node last = bfs(stt);

					// not yet
					if (last.cnt != 5) continue;

					// win condition
					if (stt.c == last.c) {
						if (stt.r < last.r) result = stt;
						else result = last;
					} else if (stt.c < last.c) result = stt;

					else result = last;

					break loop;
				}
			}
		}

		// result
		sb = new StringBuilder();

		if (result.cnt == 0) {
			sb.append(0);
		}
		else {
			sb.append(map[result.r][result.c]).append("\n");
			sb.append(result.r +1).append(" ").append(result.c+1);
		}

		System.out.print(sb);
	}


	static Node bfs(Node cur) {

		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(cur);

		int sttColor = map[cur.r][cur.c];
		int dir = cur.dir;

		Node result = new Node(-1, -1, 0, dir);

		while (!q.isEmpty()) {

			cur = q.removeFirst();

			boolean isValid = true;

			// search
			int nr = cur.r + dr[dir];
			int nc = cur.c + dc[dir];

			// validate
			if (nr < 0 || nr >= 19 || nc < 0 || nc >= 19) isValid = false;
			else if (map[nr][nc] != sttColor) isValid = false;
			else if (visited[nr][nc][dir]) isValid = false;

			// add queue
			if (isValid) {
				visited[nr][nc][dir] = true;
				Node next = new Node(nr, nc, cur.cnt + 1, dir);
				q.addLast(next);
			}

			// update
			if (cur.cnt == 5 && q.isEmpty()) result = cur;
		}

		return result;
	}


	static class Node {

		int r, c, cnt, dir;

		Node(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
	}


}

// 반례1) 6목 있는 경우
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 2 0 0 2 2 2 1 0 0 0 0 0 0 0 0 0 0
//0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 1 1 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

// 반례2: 세로줄
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 2 0 0 2 2 2 1 0 0 0 0 0 0 0 0 0 0
//0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 1 2 0 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 1 2 2 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 1 1 0 1 2 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0


// 반례3: 가로줄
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 1 2 2 2 2 2 1 0 0 0 0 0 0 0 0 0 0
//0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 1 2 0 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 1 2 2 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 1 1 0 1 2 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0


// 반례4: 승부x
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 1 2 1 2 2 2 1 0 0 0 0 0 0 0 0 0 0
//0 0 1 2 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0
//0 0 0 1 2 0 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 1 2 2 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 1 1 0 1 2 0 1 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 1 2 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0


// 반례5) 6목 2 -> 5목도 따로 존재하므로 ok
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 1 1 1 1 2 2 1 0 0 0 0 0 0 0 0 0 0
//0 1 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0
//0 1 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 1 1 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0
//0 1 0 0 0 0 2 1 0 0 0 0 0 0 0 0 0 0 0
//0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0