import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,m;
	static String[][] map;


	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new String[n][m];
		for (int i=0; i<n; i++) map[i] = br.readLine().split("");

		// find
		int min = 64;
		// 잘라낼 시작지점
		for (int i=0; i<=n-8; i++) {
			for (int j=0; j<=m-8; j++) {

				String[] colors = { "W", "B" };
				int curMin = 64;

				// 잘라낸 판을 탐색
				loop: for (String nextColor : colors) {

					int cur = 0;
					for (int r = i; r < i + 8; r++) {
						for (int c = j; c < j + 8; c++) {

							// 바꿔야하는 색인지 판단
							if (!map[r][c].equals(nextColor)) cur ++;

							// min보다 크면 즉시 종료
							if (cur >= min) continue loop;

							// 다음 색 설정
							if (c < j + 7) nextColor = nextColor.equals("W") ? "B" : "W";
						}
					}

					curMin = Integer.min(curMin, cur);
				}

				// update min
				min = Integer.min(min, curMin);
			}
		}

		// result
		System.out.print(min);
	}

}