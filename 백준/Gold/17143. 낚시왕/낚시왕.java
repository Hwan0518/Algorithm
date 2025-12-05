import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	static int r,c,m;
	static int[] dr = { -1, 0, 1, 0 }; // 위, 오른, 아래, 왼
	static int[] dc = { 0, 1, 0, -1 };
	static List<Node>[][] map;
	static Set<Node> sharks;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		sharks = new HashSet<>();

		map = new List[r][c];
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) map[i][j] = new ArrayList<>();
		}

		for (int i=0; i<m; i++) {

			// r,c,d는 모두 0-based로 바꿈
			st = new StringTokenizer(br.readLine());
			int cr = Integer.parseInt(st.nextToken())-1;
			int cc = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			if (d == 1) d = 0;
			else if (d == 2) d = 2;
			else if (d == 3) d = 1;
			else d = 3;

			// map, sharks에 상어 추가
			Node shark = new Node(cr, cc, s, d, z);

			map[cr][cc].add(shark);
			sharks.add(shark);
		}

		// fishing
		int totalSize = fishing();

		// result
		System.out.print(totalSize);
	}



	static int fishing() {

		int totalSize = 0;

		// 가장 오른쪽에 도달할때까지 낚시
		for (int loc = 0; loc < c; loc ++) {

			// 상어 잡기
			totalSize += getShark(loc);

			// 상어 이동
			sharkMove();

		}

		// 낚시결과
		return totalSize;
	}



	static void sharkMove() {

		Set<Integer> eatLoc = new HashSet<>();

		// 모든 상어 이동
		for (Node shark : sharks) {

			int nr = shark.r + dr[shark.d]*shark.s;
			int nc = shark.c + dc[shark.d]*shark.s;

			// 경계 넘어가는지 확인 -> 홀수번 넘어가면 방향 그대로, 짝수번 넘어가면 방향 바뀜
			if (nr < 0 || nr >= r) nr = checkMove(nr, shark, 0);
			if (nc < 0 || nc >= c) nc = checkMove(nc, shark, 1);
			Node cur = new Node(nr, nc, shark.s, shark.d, shark.z);

			// 이동
			map[shark.r][shark.c].remove(shark); // 기존 위치에서 제거
			map[nr][nc].add(cur); // 새로운 위치로 이동

			eatLoc.add(nr*1000+nc);
		}

		// 상어 잡아먹기
		sharks.clear();
		for (int loc : eatLoc) {

			int nr = loc / 1000;
			int nc = loc % 1000;

			List<Node> list = map[nr][nc];
			Node big = list.get(0);

			// 두마리 이상 존재하면 잡아먹기 시작
			if (list.size() >= 2) {

				// 더 큰놈으로 갱신
				for (int i=1; i<list.size(); i++) {
					Node cur = list.get(i);
					if (cur.z > big.z) big = cur;
				}

				// 큰놈만 남김
				list.clear();
				list.add(big);
			}

			sharks.add(big);
		}

	}


	static int checkMove(int loc, Node shark, int base) {

		int std = base == 0 ? r : c;
		int diff, remain, div;

		if (loc >= std) {
			diff = loc-(std-1);
			remain = diff%(std-1);
			div = diff/(std-1);

			if (div%2 == 1) {
				loc = remain;
			}
			else {
				loc = (std-1)-remain;
				shark.d = (shark.d+2) % 4;
			}
		}

		else if (loc < 0) {
			diff = -loc;
			remain = diff%(std-1);
			div = diff/(std-1);

			if (div%2 == 1) {
				loc = (std-1)-remain;
			}
			else {
				loc = remain;
				shark.d = (shark.d+2) % 4;
			}
		}

		return loc;
	}



	static int getShark(int loc) {

		// 해당 열에 상어가 있다면 잡음
		for (int i=0; i<r; i++) {

			if (!map[i][loc].isEmpty()) {
				// 가장 위에 존재하는 상어
				Node shark = map[i][loc].get(0);

				// 상어 제거
				map[i][loc].clear();
				sharks.remove(shark);

				// 크기 반환
				return shark.z;
			}
		}

		// 해당 열에 상어가 없다면 아무것도 잡지 못함
		return 0;
	}



	static class Node {

		int r, c, s, d, z;

		Node(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}


}
