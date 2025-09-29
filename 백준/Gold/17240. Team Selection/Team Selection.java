import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int[][] arr;
	static boolean[] visitedCandidate;
	static boolean[] visitedAbility;
	static int max;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n][6];
		for (int i=0; i<n; i++) {

			arr[i][5] = i;

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}

		visitedCandidate = new boolean[n];
		visitedAbility = new boolean[5];

		// dfs
		dfs(0, 0);

		// result
		System.out.print(max);

	}


	static void dfs(int size, int cur) {

		if (size == 5) {
			max = Math.max(max, cur);
			return;
		}

		for (int a=0; a<5; a++) {

			if (visitedAbility[a]) continue;
			visitedAbility[a] = true;

			// sort arr by "ability a"
			final int finalA = a;
			Arrays.sort(arr, (o1, o2) -> o1[finalA] != o2[finalA]
					? o2[finalA]-o1[finalA]
					: o1[5] - o2[5]
			);

			// pick candidate
			int pick = 0; // highest value
			int candidate = arr[pick][5]; // real number of candidate
			while (visitedCandidate[candidate]) {
				pick ++;
				candidate = arr[pick][5];
			}
			visitedCandidate[candidate] = true;

			// dfs
			dfs(size+1, cur+arr[pick][a]);
			visitedAbility[a] = false;
			visitedCandidate[candidate] = false;

		}

	}


}