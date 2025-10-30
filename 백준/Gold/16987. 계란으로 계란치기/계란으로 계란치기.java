import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static int[] hp;
	static int[] w;
	static int max = 0;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		hp = new int[n];
		w = new int[n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			hp[i] = Integer.parseInt(st.nextToken());
			w[i] = Integer.parseInt(st.nextToken());
		}

		// dfs
		int i = 0;
		int cnt = 0;
		dfs(i, cnt);

		// result
		System.out.print(max);
	}


	static void dfs(int i, int cnt) {

		if (i == n) {
			max = Integer.max(max, cnt);
			return;
		}

		// 현재 계란을 들 수 있는가? (내구도가 0보다 큰가?)
		if (hp[i] <= 0) {
			dfs(i+1, cnt);
			return;
		}

		// 다른 계란을 친다
		boolean isAllBroken = true;

		for (int other=0; other<n; other ++) {

			if (other == i) continue;
			if (hp[other] <= 0) continue;

			isAllBroken = false;

			// hp update
			hp[i] -= w[other];
			hp[other] -= w[i];

			// 들고있는게 멀쩡할때
			if (hp[i] > 0) {
				// 다른거 꺠짐
				if (hp[other] <= 0) dfs(i+1, cnt+1);
				// 다른거 안깨짐
				else dfs(i+1, cnt);
			}
			// 들고있는게 깨졌을 때
			else {
				// 다른거 깨짐
				if (hp[other] <=0) dfs(i+1, cnt+2);
				// 다른거 안깨짐
				else dfs(i+1, cnt+1);
			}

			// hp 롤백
			hp[i] += w[other];
			hp[other] += w[i];
		}

		if (isAllBroken) dfs(i+1, cnt);
	}

}
