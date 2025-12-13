import java.io.*;
import java.util.*;

public class Main {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;

	public static void main(String[] args) throws IOException {

		// test case
		int tc = Integer.parseInt(br.readLine());

		for (int t=0; t<tc; t++) {
			br.readLine();
			sb.append(sol()).append("\n");
		}

		// result
		System.out.print(sb);
	}


	static int sol() throws IOException {

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		String[][] mapR = new String[r][c];
		String[][] mapC = new String[c][r];

		for (int i=0; i<r; i++) mapR[i] = br.readLine().split("");
		for (int i=0; i<c; i++) {
			for (int j=0; j<r; j++) mapC[i][j] = mapR[j][i];
		}

		// serach
		int cnt = 0;

		// 행 탐색
		for (int i=0; i<r; i++) cnt += check(mapR[i], 0);

		// 열 탐색
		for (int i=0; i<c; i++) cnt += check(mapC[i], 1);

		// res
		return cnt;
	}


	static int check(String[] data, int type) {

		int cnt = 0;
		String last = null;
		int level = 0;

		for (String s : data) {

			// >나 v가 들어오면 level 1이 된다
			if (type == 0 ? s.equals(">") : s.equals("v")) {
				level = 1;
			}

			// o가 들어가야하는 경우
			else if (level == 1 && s.equals("o")) {
				level = 2;
			}

			// <나 ^가 들어가야하는 경우
			else if (level == 2
				&& (type == 0 ? s.equals("<") : s.equals("^"))
			) {
				cnt++;
				level = 0;
			}

			// 잘못된 경우 -> 다시 level 0으로 시작
			else {
				level = 0;
			}
		}

		return cnt;
	}

}