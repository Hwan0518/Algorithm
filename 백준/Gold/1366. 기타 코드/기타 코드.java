import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, m;
	static int[] open, code, selected, visited;
	static Map<String, Integer> note = Map.ofEntries(
	    Map.entry("A", 0),
	    Map.entry("A#", 1),
	    Map.entry("B", 2),
	    Map.entry("C", 3),
	    Map.entry("C#", 4),
	    Map.entry("D", 5),
	    Map.entry("D#", 6),
	    Map.entry("E", 7),
	    Map.entry("F", 8),
	    Map.entry("F#", 9),
	    Map.entry("G", 10),
	    Map.entry("G#", 11)
	);

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		open = new int[n];
		for (int i=0; i<n; i++) open[i] = note.get(st.nextToken());

		st = new StringTokenizer(br.readLine());
		code = new int[m];
		for (int i=0; i<m; i++) code[i] = note.get(st.nextToken());

		// dfs
		selected = new int[n];
		visited = new int[m];
		int min = dfs(0);

		// result
		System.out.print(min);
	}


	static int dfs(int size) {

		// base-condition
		if (size == n) {

			// validate
			for (int i=0; i<m; i++) {
				if (visited[i] == 0) return 1000;
			}

			int min = 100;
			int max = -100;

			for (int i=0; i<n; i++) {

				if (selected[i] == -1) continue;

				min = Integer.min(min, selected[i]);
				max = Integer.max(max, selected[i]);
			}

			return min==100 ? 0 : max-min+1;
		}

		// select -> size번째 줄을 i번째 코드 음으로 맞춤
		int min = 100;
		for (int i=0; i<m; i++) {
            
			visited[i] ++;
            
			for (int j=0; j<2; j++) {
				if (j==0) selectFret(size, i, true);
				else selectFret(size, i, false);
				min = Integer.min(min, dfs(size + 1));
			}
            
			visited[i] --;
		}

		// result
		return min;
	}


	// size번째 줄을 i번째 코드 음으로 맞춤
	static void selectFret(int size, int i, boolean direct) {

		// 오픈이라면 -1로 설정
		if (open[size] == code[i]) {
			selected[size] = -1;
			return;
		}

		// size줄의 open을, code의 i번째 음에 맞춤
		int diff = (code[i] - open[size]+12) % 12;

		// direct인 경우와, 한 옥타브 위인 경우를 고려
		selected[size] = direct ? diff : diff+12;
	}

}