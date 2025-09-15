import java.io.*;
import java.util.*;
import java.util.function.Function;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int m;
	static int min = Integer.MAX_VALUE;
	static int blind;
	static List<Node> CCTV;
	static int[][] map;
	static int[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int[][] cctv1 = { {0}, {1}, {2}, {3} };
	static int[][] cctv2 = { {0,1}, {2,3} };
	static int[][] cctv3 = { {0,3}, {3,1}, {1,2}, {2,0} };
	static int[][] cctv4 = { {0,1,2}, {1,2,3}, {2,3,0}, {3,0,1} };
	static int[][] cctv5 = { {0,1,2,3} };

	// Node.type이 index
	static int[][][] cctvs = { null, cctv1, cctv2, cctv3, cctv4, cctv5 };




	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		blind = 0;
		CCTV = new ArrayList<>();
		visited = new int[n][m];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {

				int v = Integer.parseInt(st.nextToken());

				if (v == 0) {
					blind ++;
				}
				else if (v < 6) {
					blind ++;
					CCTV.add(new Node(i, j, v));
					visited[i][j] ++;
				}

				map[i][j] = v;
			}
		}

		// dfs
		dfs(0, CCTV.size());

		// result
		System.out.print(min);
	}



	static void dfs(int size, int cnt) {

		if (size == CCTV.size()) {
			min = Math.min(min, blind - cnt);
			return;
		}

		Node cur = CCTV.get(size);
		int[][] cctv = cctvs[cur.type];

		// cctv 타입별로, 가능한 방향 케이스에 대해 모두 탐색
		for (int caseIdx=0; caseIdx < cctv.length; caseIdx++) {

			int temp = 0; // 각 케이스별로 새롭게 감시할 수 있는 칸의 수
			int[] dCase = cctv[caseIdx];

			// 해당 케이스의 모든 방향에 대해 탐색
			for (int dIdx=0; dIdx<dCase.length; dIdx++) {

				int d = dCase[dIdx];
				temp += search(cur, d);

			}

			// 하나의 케이스에 대해 탐색 완료헀다면, 다음 cctv로 이동
			dfs(size+1, cnt + temp);

			// 해당 케이스에서 방문했던 곳을 초기화
			resetVisited(cur, caseIdx);
		}

	}


	static void resetVisited(Node cur, int caseIdx) {

		int[][] cctv = cctvs[cur.type];
		int[] dCase = cctv[caseIdx];

		// 해당 케이스의 모든 방향 visited를 -1
		for (int d : dCase) {

			int nr = cur.r + dr[d];
			int nc = cur.c + dc[d];

			while (validate(nr, nc)) {
				visited[nr][nc]--;
				nr += dr[d];
				nc += dc[d];
			}
		}
	}


	static int search(Node cur, int dir) {

		int temp = 0;

		// 다음칸
		int nr = cur.r + dr[dir];
		int nc = cur.c + dc[dir];

		// map을 벗어나거나 벽에 막혔다면 temp를 return
		if (!validate(nr, nc)) return temp;

		// 한번도 감시한적이 없는 구역이라면 temp ++
		if (visited[nr][nc] == 0) temp ++;
		visited[nr][nc] ++;

		// 같은 방향으로 계속 탐색
		temp += search(new Node(nr, nc, cur.type), dir);
		return temp;
	}


	static boolean validate(int r, int c) {

		if (r<0 || r>=n || c<0 || c>=m) return false;
		if (map[r][c] == 6) return false;

		return true;

	}


	static class Node {

		int r;
		int c;
		int type;

		Node(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}

	}

}