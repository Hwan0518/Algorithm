import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, l, r;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// search
		int day = search();

		// result
		System.out.print(day);
	}


	static int search() {

		int day = 0;
		boolean move = true;

		while (move) {

			move = false;
			List<Node> unionList = new ArrayList<>();
			visited = new boolean[n][n];

			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {

					// validate1
					if (visited[i][j]) continue;
					visited[i][j] = true;

					// find union
					int totalPopulation = bfs(i, j, unionList);

					// update Map -> 이러면 안됨. 모든 union을 찾은다음 한번에 updateMap을 진행해야함. 근본적인 로직오류
					if (unionList.size() > 1) {
						updateMap(unionList, totalPopulation);
						move = true;
					}

					// reset
					unionList = new ArrayList<>();
				}
			}
			if (move) day ++;
		}

		return day;
	}


	static int bfs(int sttR, int sttC, List<Node> unionList) {

		// set-up
		ArrayDeque<Node> q = new ArrayDeque<>();
		Node stt = new Node(sttR, sttC);
		q.addLast(stt);

		int totalPopulation = map[sttR][sttC];
		unionList.add(stt);

		// bfs
		while (!q.isEmpty()) {

			Node cur =  q.removeFirst();

			// neighbor
			for (int i=0; i<4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// validate
				if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
				if (visited[nr][nc]) continue;

				int diff = Math.abs(map[cur.r][cur.c] - map[nr][nc]);
				if (diff < l || diff > r) continue;

				// add union
				visited[nr][nc] = true;

				Node next = new Node(nr, nc);
				q.addLast(next);
				unionList.add(next);
				totalPopulation += map[nr][nc];
			}
		}

		return totalPopulation;
	}


	static void updateMap(List<Node> unionList, int totalPopulation) {

		int avg = totalPopulation / unionList.size();
		for (Node union : unionList) map[union.r][union.c] = avg;
	}


	static class Node {

		int r,c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}


}