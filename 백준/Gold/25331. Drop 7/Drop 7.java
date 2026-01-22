import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, cnt;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		// input
		cnt = 1;

		map = new int[7][7];
		for (int i=0; i<7; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<7; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) cnt++;
			}
		}

		n = Integer.parseInt(br.readLine());

		// drop
		int min = cnt;
		for (int c=0; c<7; c++) min = Integer.min(min, drop(c));

		// result
		System.out.print(min);
	}


	static int drop(int c) {

		// set-up
		int remove = 0;
		int[][] curMap = initMap();

		// 1. drop the ball
		dropTheBall(curMap, c, n);

		// 2. 그룹 탐색 & 공 제거
		List<Node> removed = findGroup(curMap);
		remove += removed.size();

		// 3. 제거되는 공이 없을때까지 반복
		while (!removed.isEmpty()) {

			// 제거된 공이 있다면, 해당 공들의 위에 공을 떨어뜨림
			moving(curMap);

			// 그룹 탐색 & 공 제거
			removed = findGroup(curMap);
			remove += removed.size();
		}

		// 결과
		return cnt-remove;
	}


	static void dropTheBall(int[][] curMap, int c, int num) {
		int finalR = 0;
		for (int curR=0; curR<7; curR++) {
			if (curMap[curR][c] == 0) finalR = curR;
			else break;
		}

		// 현재 값 떨어뜨림
		curMap[finalR][c] = num;
	}


	static List<Node> findGroup(int[][] curMap) {

		// 제거된 목록
		List<Node> removed = new ArrayList<>();
		visited = new boolean[7][7];

		// 가로그룹 탐색
		for (int r=0; r<7; r++) {

			int groupStt = 0;
			for (int c=0; c<7; c++) {

				// 0을 만나면 그룹 끝
				if (curMap[r][c] == 0) {

					// 그룹에서 size와 같은 수를 제거
					int size = c-groupStt;

					for (int groupC=groupStt; groupC<c; groupC++) {
						if (curMap[r][groupC] == size) {
							if (!visited[r][groupC]) {
								removed.add(new Node(r,groupC));
								visited[r][groupC] = true;
							}
						}
					}

					// groupStt 갱신
					groupStt = c+1;
				}
			}

			// 그룹이 남아있을 수 있으니 한번 더 확인
			int size = 7-groupStt;
			for (int groupC=groupStt; groupC<7; groupC++) {
				if (curMap[r][groupC] == size) {
					if (!visited[r][groupC]) {
						removed.add(new Node(r,groupC));
						visited[r][groupC] = true;
					}
				}
			}
		}

		// 세로그룹 탐색
		for (int c=0; c<7; c++) {

			int groupStt = 0;
			for (int r=0; r<7; r++) {

				// 0을 만나면 그룹 끝
				if (curMap[r][c] == 0) {

					// 그룹에서 size와 같은 수를 제거
					int size = r-groupStt;

					for (int groupR=groupStt; groupR<r; groupR++) {
						if (curMap[groupR][c] == size) {
							if (!visited[groupR][c]) {
								removed.add(new Node(groupR,c));
								visited[groupR][c] = true;
							}
						}
					}

					// groupStt 갱신
					groupStt = r+1;
				}
			}

			// 그룹이 남아있을 수 있으니 한번 더 확인
			int size = 7-groupStt;
			for (int groupR=groupStt; groupR<7; groupR++) {
				if (curMap[groupR][c] == size) {
					if (!visited[groupR][c]) {
						removed.add(new Node(groupR,c));
						visited[groupR][c] = true;
					}
				}
			}
		}

		// 세로그룹,가로그룹을 한번에 제거
		for (Node ball : removed) curMap[ball.r][ball.c] = 0;

		// result
		return removed;
	}


	static void moving(int[][] curMap) {

		// 제거된 공의 위에 공들을 떨어뜨림
		for (int c=0; c<7; c++) {

			int curR = 6;

			// 0이 아닌 값들만 curR부터 차례대로 채움 (아래에서부터)
			for (int r=6; r>=0; r--) {
				if (curMap[r][c] != 0) {
					curMap[curR][c] = curMap[r][c];
					if (curR != r) curMap[r][c] = 0;
					curR--;
				}
			}

			// 남은 위쪽은 0으로 채움
			for (int r=curR; r>=0; r--) curMap[r][c] = 0;
		}
	}


	static int[][] initMap() {
		int[][] curMap = new int[7][7];
		for (int i=0; i<7; i++) {
			for (int j=0; j<7; j++) curMap[i][j] = map[i][j];
		}

		return curMap;
	}


	static class Node {

		int r, c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
