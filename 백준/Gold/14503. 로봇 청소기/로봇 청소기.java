import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 }; // 북, 동, 남, 서
	static int[] dc = { 0, 1, 0, -1 }; // 북, 동, 남, 서


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int sttR = Integer.parseInt(st.nextToken());
		int sttC = Integer.parseInt(st.nextToken());
		int sttD = Integer.parseInt(st.nextToken());
		Node stt = new Node(sttR, sttC, sttD);

		map = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// bfs
		int result = bfs(stt);

		// result
		System.out.print(result);
	}


	static int bfs(Node stt) {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(stt);

		// search
		int cnt = 0;
		while (!q.isEmpty()) {

			Node cur = q.removeLast();

			// 1. 청소할 수 있는 칸이라면 -> 현재칸 청소
			if (map[cur.r][cur.c] == 0) {
				map[cur.r][cur.c] = 2;
				cnt ++;
			}

			// 2. 상하좌우 청소할 칸 여부 탐색
			boolean allClean = isAllClean(cur);

			// 3-1. 전부 청소된 경우 -> 후진
			if (allClean) {
				boolean possible = goBack(q, cur);
				if (!possible) break;
			}

			// 3-2. 청소할 칸이 있는 경우 -> 이동
			else move(q, cur);
		}

		return cnt;
	}


	static boolean isAllClean(Node cur) {

		for (int i=0; i<4; i++) {

			int nr = cur.r + dr[i];
			int nc = cur.c + dc[i];

			// validate
			if (!validate(nr, nc)) continue;
			if (map[nr][nc] == 2) continue;

			return false;
		}

		return true;
	}


	static void move(ArrayDeque<Node> q, Node cur) {

		// 3-1. 반시계로 90도 회전 -> index -1 이동하고 +4 %4
		int nDir = (cur.dir -1 +4) %4;

		// 3-2. 청소할 수 있는 칸을 찾을때까지 회전
		int nr = cur.r + dr[nDir];
		int nc = cur.c + dc[nDir];

		while (map[nr][nc] != 0) {
			
			nDir = (nDir -1 +4) %4;
			nr = cur.r + dr[nDir];
			nc = cur.c + dc[nDir];
		}

		// 3-2. 앞쪽이 청소할 수 있는 칸이라면 전진
		q.addLast(new Node(nr, nc, nDir));
	}


	static boolean goBack(ArrayDeque<Node> q, Node cur) {

		int opposite = (cur.dir +2) % 4; // index 2개 이동하고 % 4
		int nr = cur.r + dr[opposite];
		int nc = cur.c + dc[opposite];

		// 후진할 수 있는 경우 -> 방향 유지하고 + 후진
		if (validate(nr, nc)) {
			q.addLast(new Node(nr, nc, cur.dir));
			return true;
		}

		// 후진 불가능 -> 작동 멈춤
		else return false;
	}


	static boolean validate(int nr, int nc) {

		if (nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
		if (map[nr][nc] == 1) return false;

		return true;
	}


	static class Node {

		int r, c, dir;

		Node(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}


}
