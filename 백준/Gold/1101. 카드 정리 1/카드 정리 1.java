import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,m;
	static int[][] map;
	static int[] rowCnt;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// make map & select joker
		boolean[] visited = new boolean[n];
		int joker = -1;
		map = new int[n][m];
		rowCnt = new int[n];
		
		for (int i=0; i<n; i++) {

			int curRowCnt = 0;

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) curRowCnt ++;
			}

			rowCnt[i] = curRowCnt;

			if (joker == -1 && curRowCnt > 1) {
				visited[i] = true;
				joker = i;
			}
		}

		// joker == -1이라면, col에서 겹치는게 있는지 확인
		if (joker == -1) {
			loop:
			for (int j = 0; j < m; j++) {

				int dupl = 0;

				for (int i = 0; i < n; i++) {

					if (map[i][j] > 0) dupl++;

					if (dupl > 1) {
						joker = i;
						visited[i] = true;
						break loop;
					}
				}
			}
		}

		// col도 겹치는게 없는 경우 정답은 0이다
		if (joker == -1) {
			System.out.print(0);
			return;
		}

		// find unique row & column
		int cnt = 0;

		for (int i=0; i<n; i++) {
			if (i==joker) continue;
			if (rowCnt[i]>1 && !visited[i]) {
				visited[i] = true;
				cnt ++;
			}
		}

		for (int j=0; j<m; j++) {

			List<Integer> dupl = new ArrayList<>();

			for (int i=0; i<n; i++) {
				if (map[i][j] > 0 && !visited[i]) dupl.add(i);
			}

			if (dupl.size() > 1) {
				for (int i=1; i<dupl.size(); i++) {
					if (!visited[dupl.get(i)]) {
						cnt ++;
						visited[dupl.get(i)] = true;
					}
				}
			}
		}

		// result
		System.out.print(cnt);
	}

}