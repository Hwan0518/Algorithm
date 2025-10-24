import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n,m,h;
	static int[] ladderCnt;
	static int[][] edge;
	static int min = 10;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		ladderCnt = new int[n+1];

		edge = new int[h+1][n+1]; // edge[i][j] = k: i행에서 -> j-k열 사다리 연결되어있음
		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			edge[a][b] = b+1;
			edge[a][b+1] = b;

			ladderCnt[b] ++;
			ladderCnt[b+1] ++;
		}

		// dfs
		int r = 1;
		int c = 1;
		int cnt = 0;
		dfs(r, c, cnt);

		// result
		System.out.println(min > 3 ? -1 : min);
	}


	static void dfs(int r, int c, int cnt) {

		if (cnt == n*h || c == n+1 || cnt > 3) {
			if (cnt <=3 && validate()) min = Integer.min(min, cnt);
			return;
		}

		if (r == h+1) {
			dfs(1, c+1, cnt);
			return;
		}

		// 더이상 해당 세로선에는 사다리 놓기 불가능
		if (ladderCnt[c] == h) {
			dfs(1, c+1, cnt); // 옆으로 이동
			return;
		}

		// 이미 놓았거나, 연속된다면 사다리 놓기 불가능
		if (edge[r][c] != 0
			|| (c > 1 && edge[r][c-1] == c)
			|| (c < n && edge[r][c+1] == c+2)
		) {
			dfs(r+1, c, cnt); // 아래로 이동
			return;
		}

		// 선택 (무조건 오른쪽으로만 놓자)
		if (c < n) {
			edge[r][c] = c + 1;
			edge[r][c + 1] = c;
			ladderCnt[c] ++;
			ladderCnt[c+1] ++;
			dfs(r + 1, c, cnt + 1);
			edge[r][c] = 0;
			edge[r][c + 1] = 0;
			ladderCnt[c] --;
			ladderCnt[c+1] --;
		}

		// 비선택
		dfs(r+1, c, cnt);
	}


	static boolean validate() {

		for (int stt=1; stt<=n; stt++) {

//			// 각 세로줄의 사다리 수는 짝수가 되어야함
//			if (ladderCnt[stt]%2 != 0) return false;

			// 탐색
			int r = 1;
			int c = stt;

			while (r < h+1) {
				if (edge[r][c] != 0) c = edge[r][c];
				r++;
			}

			if (c != stt) return false;
		}

		return true;
	}



}