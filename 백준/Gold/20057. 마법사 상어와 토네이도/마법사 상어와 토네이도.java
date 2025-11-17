import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, total;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		// rotate
		rotate();

		// result
		System.out.print(total);
	}



	static void rotate() {

		Node tornado = new Node(n/2, n/2);

		int dist = 1;
		int dir = 3; // 왼쪽부터 움직인다
		while (tornado.r != 0 || tornado.c != 0) {

			// dist만큼 두 번 움직인다
			loop: for (int i=0; i<2; i++) {
				for (int j=0; j<dist; j++) {
					tornado = move(tornado, dir);
					if (tornado.r == 0 && tornado.c == 0) break loop;
				}

				dir = (dir - 1) % 4; // 한번 움직이고 나면 반시계방향으로 회전
				if (dir < 0) dir = (dir + 4) % 4;
			}

			dist ++;
		}
	}


	static Node move(Node tornado, int dir) {

		// dirR, dirL 정의 (이동방향 오른쪽과 이동방향 왼쪽)
		int dirR = (dir+1) % 4;
		int dirL = (dirR+2) % 4;
		int[] dirs = { dirR, dirL };

		// dir 방향으로 dist만큼 이동
		int nr = tornado.r + dr[dir];
		int nc = tornado.c + dc[dir];
		
		// 기존 모레
		int or = tornado.r + dr[dir];
		int oc = tornado.c + dc[dir];
		int origin = map[or][oc];

		// 총 움직인 모레 양
		int totalMoved = 0;

		// 5% 모레 -> (nr,nc)에서 dir방향으로 2칸
		int r5 = nr + dr[dir]*2;
		int c5 = nc + dc[dir]*2;
		totalMoved += check(r5, c5, (int) (origin*0.05));

		for (int dd : dirs) {

			// 10% 모레 -> 오른쪽(왼쪽)1, dir방향1
			int r10 = nr + dr[dd] + dr[dir];
			int c10 = nc + dc[dd] + dc[dir];
			totalMoved += check(r10, c10, (int) (origin * 0.1));

			// 7% 모레 -> 오른쪽(왼쪽)1
			int r7 = nr + dr[dd];
			int c7 = nc + dc[dd];
			totalMoved += check(r7, c7, (int) (origin * 0.07));

			// 1% 모레 -> 기존위치에서 오른쪽(왼쪽)1
			int r1 = tornado.r + dr[dd];
			int c1 = tornado.c + dc[dd];
			totalMoved += check(r1, c1, (int) (origin * 0.01));

			// 2% 모레 -> 오른쪽(왼쪽)2
			int r2 = nr + dr[dd]*2;
			int c2 = nc + dc[dd]*2;
			totalMoved += check(r2, c2, (int) (origin * 0.02));
		}

		// 기존 nr,nc에서 totalMoved만큼 제거한 만큼이 알파로 이동
		int remain = origin - totalMoved;
		int aR = nr + dr[dir];
		int aC = nc + dc[dir];

		if (aR<0 || aR>=n || aC<0 || aC>=n) total += remain;
		else map[aR][aC] += remain;

		map[or][oc] = 0;

		// 이동한 토네이도 return
		return new Node(nr, nc);
	}



	static int check(int r, int c, int amount) {

		// 격자 밖으로 나갔다면 total에 더해준다
		if (r<0 || r>=n || c<0 || c>=n) total += amount;

		// 나가지 않았다면 map에 갱신한다
		else map[r][c] += amount;

		// 움직인만큼 return
		return amount;
	}



	static class Node {

		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}


}
