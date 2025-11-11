import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, k;
	static List<Node>[][] map, moveResult;
	static ArrayDeque<Node> fireballs;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new List[n][n];
		for (int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) map[i][j] = new ArrayList<>();
		}
		fireballs = new ArrayDeque<>();

		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			Node fireball = new Node(r, c, m, s, d);
			map[r][c].add(fireball);
			fireballs.addLast(fireball);
		}

		// move
		for (int i=0; i<k; i++) move();

		// result
		int result = findTotalFireball();
		System.out.print(result);
	}


	static int findTotalFireball() {

		int total = 0;
		for (Node fb : fireballs) total += fb.m;

		return total;
	}


	static void move() {

		// 모든 파이어볼 이동
		allFireBallMove();

		// 합쳐서 이동
		combineAndMove();
	}


	static void allFireBallMove() {

		moveResult = new List[n][n];
		for (int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) moveResult[i][j] = new ArrayList<>();
		}

		int size = fireballs.size();
		for (int i=0; i<size; i++) {

			Node fb = fireballs.removeFirst();

			int s = fb.s % n;
			int nr = fb.r + dr[fb.d]*s;
			int nc = fb.c + dc[fb.d]*s;

			// validate
			if (nr < 0) nr = (nr + n) %n;
			if (nr >= n) nr = nr % n;
			if (nc < 0) nc = (nc + n) %n;
			if (nc >= n) nc = nc % n;

			// move
			Node moved = new Node(nr, nc, fb.m, fb.s, fb.d);
			moveResult[nr][nc].add(moved);
			fireballs.addLast(moved);
		}
	}


	static void combineAndMove() {

		fireballs.clear();

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {

				map[i][j].clear();

				// 1개 이하
				if (moveResult[i][j].size() <= 1) {
					if (!moveResult[i][j].isEmpty()) {
						Node newFireball = moveResult[i][j].get(0);

						map[i][j].add(newFireball);
						fireballs.addLast(newFireball);
					}
				}

				// 2개 이상
				else {

					int nm = 0;
					int ns = 0;

					int isEven = -1;
					int isOdd = -1;

					for (Node fb : moveResult[i][j]) {

						// m
						nm += fb.m;

						// s
						ns += fb.s;

						// d
						if (fb.d % 2 == 0) isEven = 1;
						else isOdd = 1;
					}

					// next m,s
					nm /= 5;
					ns /= moveResult[i][j].size();

					// 질량 0이라면 소멸
					if (nm == 0) continue;

					// create new fireball
					for (int cnt=0; cnt<4; cnt++) {

						int nd = isEven*isOdd == -1
							? (2*cnt) % 8
							: (2*cnt +1) % 8;

						Node newFireball = new Node(i, j, nm, ns, nd);
						map[i][j].add(newFireball);
						fireballs.addLast(newFireball);
					}
				}
			}
		}

	}


	static class Node {

		int r,c,m,s,d;

		Node(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "[" + r +"," + c +"," + m +"," + s +"," + d + "]";
		}
	}


}