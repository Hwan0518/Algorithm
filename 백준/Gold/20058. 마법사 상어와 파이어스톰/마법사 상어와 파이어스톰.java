import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, q, size;
	static int[][] map1, map2;
	static int[][][] maps;
	static int[] l;
	static List<Node> decrease;
	static int[] dr = { -1, 0, 1, 0 }; // 0, 3, 6, 9 방향
	static int[] dc = { 0, 1, 0, -1 }; // 0, 3, 6, 9 방향
	static boolean[][] visited;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		size = (int) Math.pow(2, n);
		map1 = new int[size][size];
		map2 = new int[size][size];
		maps = new int[][][] { map1, map2 };

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) map1[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		l = new int[q];
		for (int i = 0; i < q; i++) l[i] = Integer.parseInt(st.nextToken());

		// step
		int turn = 0;
		for (int limit : l) turn = fireStorm(limit, turn);

		// result
		sb = new StringBuilder();
		sb.append(findTotalIce(turn));
		sb.append("\n");
		sb.append(biggestIce(turn));

		System.out.print(sb);
	}


	static int findTotalIce(int turn) {

		int[][] map = maps[turn];

		int iceAmount = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) iceAmount += map[i][j];
		}

		return iceAmount;
	}


	static int biggestIce(int turn) {

		int[][] map = maps[turn];

		// bfs 시작
		visited = new boolean[size][size];
		int max = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if (map[i][j] == 0) continue; // 얼음 없으면 pass
				if (visited[i][j]) continue;
				visited[i][j] = true;

				// 얼음 최댓값 비교
				max = Integer.max(max, bfs(i, j, turn));
			}
		}

		// 결과
		return max;
	}



	static int bfs(int r, int c, int turn) {

		// set-up
		int[][] map = maps[turn];
		int iceCnt = 1;
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.addLast(new Node(r, c));

		// search
		while (!q.isEmpty()) {

			Node cur = q.removeFirst();

			for (int i = 0; i < 4; i++) {

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				// validate
				if (nr < 0 || nr >= size || nc < 0 || nc >= size) continue;
				if (map[nr][nc] == 0) continue;
				if (visited[nr][nc]) continue;

				visited[nr][nc] = true;
				iceCnt++;
				q.addLast(new Node(nr, nc));
			}
		}

		return iceCnt;
	}



	static int fireStorm(int limit, int turn) {

		// ref와 change를 설정
		int[][] ref = maps[turn];
		int[][] change = maps[(turn + 1) % 2];

		// 2^limit 크기로 격자를 나눔
		int s = (int) Math.pow(2, limit);

		// 각 격자별로 회전
		for (int i = 0; i < size; i += s) {
			for (int j = 0; j < size; j += s) {
				rotate(ref, change, i, j, s);
			}
		}

		// 얼음 감소
		iceDecrease(change);

		// 인덱스 변경
		return (turn + 1) % 2;
	}



	static void rotate(int[][] ref, int[][] change, int sttR, int sttC, int s) {

		// 각 격자 내부에서 회전 진행
		for (int i=0; i<s; i++) {
			for (int j=0; j<s; j++) {

				int cr = sttR + i;
				int cc = sttC + j;

				int nr = sttR + j; // 열 이동 수만큼 행 이동이 됨
				int nc = sttC + (s-1-i); // 대칭이동 느낌

				// 대입
				change[nr][nc] = ref[cr][cc];
			}
		}
	}



	static void iceDecrease(int[][] change) {

		// set-up
		decrease = new ArrayList<>();

		// 네 방향에서 얼음 존재하는게 2군데 이하라면 decrease에 추가
		for (int r=0; r<size; r++) {
			for (int c=0; c<size; c++) {

				// 현재 위치에 얼음이 존재하지 않으면 pass
				if (change[r][c] == 0) continue;

				// 얼음 탐색
				int iceCnt = 0;
				for (int i = 0; i < 4; i++) {

					int nr = r + dr[i];
					int nc = c + dc[i];

					// validate
					if (nr<0 || nr>=size || nc<0 || nc>=size) continue;
					if (change[nr][nc] == 0) continue;

					iceCnt++;
				}

				// decrease
				if (iceCnt <= 2) decrease.add(new Node(r, c));
			}
		}

		// 얼음 일괄 감소
		for (Node loc : decrease) change[loc.r][loc.c] --;
	}



	static class Node {

		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}

