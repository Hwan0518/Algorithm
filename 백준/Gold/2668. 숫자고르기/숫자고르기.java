import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int[] arr;
	static boolean[] visited;
	static Set<Integer> need1;
	static Set<Integer> need2;
	static TreeSet<Integer> ans;
	static Set<Integer> fix;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n+1];
		fix = new HashSet<>();
		ans = new TreeSet<>();
		for (int i=1; i<n+1; i++) {

			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] == i) {
				fix.add(i);
				ans.add(i);
			}

		}

		// dfs
		for (int i=1; i<n+1; i++) {

			if (fix.contains(i)) continue;

			visited = new boolean[n + 1];
			for (int f : fix) visited[f] = true;

			need1 = new HashSet<>();
			need2 = new HashSet<>();
			dfs(i, fix.size(), fix.size());
		}

		// result
		StringBuilder sb = new StringBuilder();

		sb.append(ans.size()).append("\n");
		for (int num : ans) sb.append(num).append("\n");

		System.out.print(sb);
	}


	static void dfs(int num, int size, int cnt) {

		need1.add(num);

		if (size == n || need1.equals(need2)) {

			if (need1.equals(need2)) {
				for (int i=1; i<n+1; i++) if (visited[i]) ans.add(i);
			}

			return;
		}

		int next = arr[num];

		if (visited[next]) return;
		visited[next] = true;
		need2.add(next);

		dfs(next, size + 1, cnt + 1);

	}

}