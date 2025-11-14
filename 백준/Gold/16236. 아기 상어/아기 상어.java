import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, fish;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		Node shark = null;
		fish = 0;
		map = new int[n][n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {

				int v = Integer.parseInt(st.nextToken());

				map[i][j] = v;

				if (v == 9) shark = new Node(i, j, 0, 0, 2);
				else if (v > 0) fish ++;
			}
		}

		// bfs
		int time = bfs(shark);

		// result
		System.out.print(time);
	}


	static int bfs(Node shark) {

		int eatCnt = 0;

		// 물고기 다 먹을때까지 반복
		while (fish > 0) {

			// set-up
			ArrayDeque<Node> q = new ArrayDeque<>();
			q.addLast(shark);

			visited = new boolean[n][n];
			visited[shark.r][shark.c] = true;

			int minDist = Integer.MAX_VALUE;
			List<Node> nextLoc = new ArrayList<>();

			while (!q.isEmpty()) {

				Node cur = q.removeFirst();

				// 먹을 수 있는 물고기인 경우
				if (map[cur.r][cur.c] > 0 && map[cur.r][cur.c] < cur.size) {

					// 더 가까운 물고기인 경우 -> 갱신
					if (cur.d < minDist) {
						minDist = cur.d;
						nextLoc.clear();
						nextLoc.add(cur);
						continue;
					}

					// 거리가 같은 물고기인 경우 -> nextLoc에 추가
					else if (cur.d == minDist) {
						nextLoc.add(cur);
						continue;
					}
				}

				// 탐색
				for (int i=0; i<4; i++) {

					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];

					// validate
					if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
					if (map[nr][nc] > cur.size) continue;
					if (visited[nr][nc]) continue;

					visited[nr][nc] = true;
					q.addLast(new Node(nr, nc, cur.d+1, cur.t+1, cur.size));
				}
			}

			// 아무 물고기도 먹지 못하는 상황이라면 종료
			if (nextLoc.isEmpty()) break;

			// nextLoc 크기가 1보다 큰 경우, 정렬시킴
			if (nextLoc.size() > 1) Collections.sort(nextLoc,
				(o1, o2) -> o1.r == o2.r
					? o1.c - o2.c
					: o1.r - o2.r);

			// nextLoc의 물고기를 잡아먹음
			Node eat = nextLoc.get(0);
			map[eat.r][eat.c] = 0;
			fish --;
			eatCnt ++;

			// 상어 크기 갱신
			if (eatCnt == shark.size) {
				shark.size ++;
				eatCnt = 0;
			}

			// 잡아먹은 물고기 위치로 이동
            map[shark.r][shark.c] = 0;
			shark = new Node(eat.r, eat.c, 0, eat.t, shark.size);
		}

		return shark.t;
	}


	static class Node {

		int r, c, d, t, size;

		Node(int r, int c, int d, int t, int size) {
			this.r = r;
			this.c = c;
			this.d = d; // 거리는 bfs마다 초기화 되어야함
			this.t = t; // 시간은 계속 유지되어야함
			this.size = size;
		}
	}


}
