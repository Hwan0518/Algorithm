import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int t;
	static Map<Integer, Set<Integer>> map;
	static int minCnt;
	static Map<Integer, Map<Integer, Boolean>> visited;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1}; // 상하좌우, 상좌, 상우, 하좌, 하우
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1}; // 상하좌우, 상좌, 상우, 하좌, 하우


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new HashMap<>();
		visited = new HashMap<>();
		visited.put(0, new HashMap<>());
		visited.get(0).put(0, true);

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (!map.containsKey(x)) map.put(x, new HashSet<>());
			map.get(x).add(y);

			if (!visited.containsKey(x)) visited.put(x, new HashMap<>());
			visited.get(x).put(y, false);
		}


		// bfs
		bfs();

		// result
		System.out.print(minCnt == 0 ? -1 : minCnt);
	}





	static void bfs() {

		// set-up
		Deque<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 0));

		// search
		while (!q.isEmpty()) {

			Node cur = q.pop();
			int curX = cur.x;
			int curY = cur.y;
			int curM = cur.move;

			if (curY == t) {
				minCnt = curM;
				break;
			}

			for (int i=0; i<8; i++) {
				for (int j=1; j<=2; j++) {
					for (int k=1; k<=2; k++) {

						int nx = curX + dr[i] * j;
						int ny = curY + dc[i] * k;

						// validate
						if (nx < 0 || nx > 1_000_000 || ny < 0 || ny > t) continue;
						if (!map.containsKey(nx) || !map.get(nx).contains(ny)) continue;
						if (Math.abs(curX - nx) > 2 || Math.abs(curY - ny) > 2) continue;
						if (visited.get(nx).get(ny)) continue;
						
						visited.get(nx).put(ny, true);
						q.add(new Node(nx, ny, curM + 1));
					}
				}
			}

		}
	}


	static class Node {

		int x;
		int y;
		int move;

		Node(int x, int y, int m) {
			this.x = x;
			this.y = y;
			this.move = m;
		}

	}

}