import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int n;
	static int cnt = 0;
	static boolean[] visitCol;
	static int[] rowCol;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		visitCol = new boolean[n];
		rowCol = new int[n];

		// dfs
		dfs(0, 0);

		// result
		System.out.print(cnt);
	}


	static void dfs(int r, int size) {

		// size == n인 경우
		if (size == n || r == n) {
			if (size == n) cnt++;
			return;
		}

		// 행의 각 열을 순회
		for (int c=0; c<n; c++) {

			// 방문할 수 없는 경우 다음 좌표로 이동
			if (visitCol[c] || !validate(r, c)) continue;

			// 현재 위치 선택
			visitCol[c] = true;
			rowCol[r] = c;

			dfs(r+1, size + 1); // 같은 행에 갈 수가 없음. 따라서 r+1

			visitCol[c] = false;
		}
	}


	static boolean validate(int r, int c) {

		for (int i=0; i<r; i++) {

			int visitC = rowCol[i];
			if (Math.abs(i-r) == Math.abs(visitC-c)) return false;
		}

		return true;
	}

}
