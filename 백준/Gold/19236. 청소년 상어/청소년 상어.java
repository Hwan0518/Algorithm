import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static Node[][] map = new Node[4][4];
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; //↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static List<Node> fishes, originFishes;
	static int max;

	public static void main(String[] args) throws IOException {

		// input
		fishes = new ArrayList<>();
		originFishes = new ArrayList<>();
		for (int i=0; i<4; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j=0; j<4; j++) {

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken())-1; // 0-based로 변경

				Node fish = new Node(i, j, a, b, false);

				map[i][j] = fish;
				fishes.add(fish);
			}
		}

		Collections.sort(fishes, (o1,o2) -> o1.num - o2.num);

		// initial setting
		Node eaten = map[0][0];
		Node shark = new Node(0, 0, eaten.num, eaten.dir, true);

		map[0][0] = shark;
		fishes.remove(eaten);

		// dfs
		dfs(shark, map, fishes);

		// result
		System.out.print(max);
	}


	static void dfs(Node shark, Node[][] map, List<Node> fishes) {

		// 1. 물고기 이동
		moveFishes(map, fishes);

		// 2. 상어 이동 가능한 위치 확인
		List<Node> possible = isPossible(shark, map);

		// 3. 상어 이동 불가능한 경우 -> 최댓값 갱신 후 종료
		if (possible.isEmpty()) {
			max = Integer.max(max, shark.num);
			return;
		}

		// 4. 상어 이동 가능한 경우 -> 이동
		for (Node loc : possible) {
			moveShark(shark, loc, map, fishes);
		}
	}


	static void moveFishes(Node[][] map, List<Node> fishes) {

		// 번호 작은 물고기부터 이동
		int size = fishes.size();
		for (int i=0; i<size; i++) {

			Node fish = fishes.get(i);

			int nr = fish.r + dr[fish.dir];
			int nc = fish.c + dc[fish.dir];

			// 이동 가능한 칸이 나올때까지 방향 전환
			boolean movePossible = false;
			int originDir = fish.dir;
			while (true) {

				// 경계를 벗어나지 않고, 상어를 만나지 않았다면 이동가능
				if ((nr>=0 && nr<4 && nc>=0 && nc<4)
					&& (map[nr][nc] == null || !map[nr][nc].isShark)
				) {
					movePossible = true;
					break;
				}

				// 위의 조건에서 벗어나면 방향 전환
				fish.dir = (fish.dir + 1) % 8;
				nr = fish.r + dr[fish.dir];
				nc = fish.c + dc[fish.dir];

				// 방향을 계속 바꾸다 기존 방향에 도달하면 종료. 이동 불가능임
				if (fish.dir == originDir) break;
			}

			// 이동 불가능하면 이동하지 않음
			if (!movePossible) continue;

			// 물고기 이동
			int originR = fish.r;
			int originC = fish.c;
			fish.r = nr;
			fish.c = nc;

			// 비어있다면 바로 이동
			if (map[nr][nc] == null) {
				map[nr][nc] = fish;
				map[originR][originC] = null;
			}

			// 물고기가 있다면 자리 바꿈
			else {
				// 새로운 자리에 있던 물고기 이동
				Node otherFish = map[nr][nc];
				map[originR][originC] = otherFish;
				otherFish.r = originR;
				otherFish.c = originC;

				// 현재 물고기 이동
				map[nr][nc] = fish;
			}
		}

	}



	static List<Node> isPossible(Node shark, Node[][] map) {

		// 방향으로 전체 탐색하면서 가능한 위치 정보를 담음
		List<Node> possible = new ArrayList<>();

		// 탐색
		int nr = shark.r + dr[shark.dir];
		int nc = shark.c + dc[shark.dir];

		while (true) {

			// 칸 벗어나면 종료
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) break;

			// 해당 칸 물고기 있으면 이동할 수 있는 칸임
			if (map[nr][nc] != null) possible.add(map[nr][nc]);

			// 다음칸으로
			nr += dr[shark.dir];
			nc += dc[shark.dir];
		}

		// 결과
		return possible;
	}


	static void moveShark(Node shark, Node loc, Node[][] map, List<Node> fishes) {

		// 이동한 상어
		Node nextShark = new Node(loc.r, loc.c, shark.num + loc.num, loc.dir, true);

		// map, fishes 정보 새로 생성
		Node[][] nextMap = new Node[4][4];
		List<Node> nextFishes = new ArrayList<>();

		for (Node fish : fishes) {
			Node nextFish = new Node(fish);
			nextFishes.add(nextFish);
			nextMap[nextFish.r][nextFish.c] = nextFish;
		}

		// 이동할 위치
		Node nextLoc = nextMap[loc.r][loc.c];

		// 상어 이동 & 물고기 제거
		nextMap[shark.r][shark.c] = null;
		nextMap[loc.r][loc.c] = nextShark;
		nextFishes.remove(nextLoc);

		// 다음으로 탐색
		dfs(nextShark, nextMap, nextFishes);
	}



	static class Node {

		int r,c, num, dir;
		boolean isShark;

		Node(int r, int c, int num, int dir, boolean isShark) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir;
			this.isShark = isShark;
		}

		public Node(Node node) {
			if (node != null) {
				this.r = node.r;
				this.c = node.c;
				this.num = node.num;
				this.dir = node.dir;
				this.isShark = node.isShark;
			}
		}
	}

}
