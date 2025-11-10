import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int r, c, t;
	static int[][] map;
	static int[][] spreadMap;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] airCleaner = new int[2];

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());


		airCleaner[0] = -1;
		map = new int[r][c];
		for (int i=0; i<r; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j=0; j<c; j++) {

				int v = Integer.parseInt(st.nextToken());

				map[i][j] = v;
				if (v == -1) {
					if (airCleaner[0] == -1) airCleaner[0] = i;
					else airCleaner[1] = i;
				}
			}
		}

		// time flow
		timeFlow();

		// result
		int result = findTotalDust();
		System.out.print(result);
	}


	static int findTotalDust() {

		int total = 2; // 공기청정기로 빠지는 값을 미리 선언

		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) total += map[i][j];
		}

		return total;
	}


	static void timeFlow() {

		for (int curTime=0; curTime<t; curTime++) {
			// 미세먼지 확산
			dustSpread();
			// 공기청정기 작동
			operateAirCleaner();
		}
	}


	static void dustSpread() {

		spreadMap = new int[r][c];

		// find spreadAmount
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {

				if (j==0 && (i==airCleaner[0] || i ==airCleaner[1])) continue;

				int curD = map[i][j];
				int spreadD = curD/5;
				int spreadCnt = 0;

				for (int k=0; k<4; k++) {

					int nr = i+dr[k];
					int nc = j+dc[k];

					// validate
					if (nr<0 || nr>=r || nc<0 || nc>=c) continue;
					if (nc == 0 && (nr==airCleaner[0] || nr ==airCleaner[1])) continue;

					spreadCnt ++;
					spreadMap[nr][nc] += spreadD;
				}

				spreadMap[i][j] -= spreadD*spreadCnt;
			}
		}
		
		// sum spreadAmount
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) map[i][j] += spreadMap[i][j];
		}
	}


	static void operateAirCleaner() {

		int aR;

		// top side
		aR = airCleaner[0];

		for (int i=aR-1; i>0; i--) map[i][0] = map[i-1][0]; // 0열 -> j=0 고정
		for (int j=0; j<c-1; j++) map[0][j] = map[0][j+1]; // 0행 -> i=0 고정
		for (int i=0; i<aR; i++) map[i][c-1] = map[i+1][c-1]; // c-1열 -> j=c-1 고정
		for (int j=c-1; j>1; j--) map[aR][j] = map[aR][j-1]; // aR행 -> i=aR 고정
		map[aR][1] = 0; // 공기청정기 바로 앞 칸은 밀려난다

		// bottom side
		aR = airCleaner[1];

		for (int i=aR+1; i<r-1; i++) map[i][0] = map[i+1][0]; // 0열 -> j=0 고정
		for (int j=0; j<c-1; j++) map[r-1][j] = map[r-1][j+1]; // r-1행 -> i=r-1 고정
		for (int i=r-1; i>aR; i--) map[i][c-1] = map[i-1][c-1]; // c-1열 -> j=c-1 고정
		for (int j=c-1; j>1; j--) map[aR][j] = map[aR][j-1]; // aR행 -> i=aR 고정
		map[aR][1] = 0; // 공기청정기 바로 앞 칸은 밀려난다
	}


}