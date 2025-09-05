import java.io.*;
import java.util.*;

public class Main {

	static boolean[] visited = new boolean[12];
	static int[] select = new int[12];
	static String ans;

	static Set<Integer> line1 = Set.of(0, 2, 5, 7);
	static Set<Integer> line2 = Set.of(1, 2, 3, 4);
	static Set<Integer> line3 = Set.of(0, 3, 6, 10);
	static Set<Integer> line4 = Set.of(7, 8, 9, 10);
	static Set<Integer> line5 = Set.of(1, 5, 8, 11);
	static Set<Integer> line6 = Set.of(4, 6, 9, 11);
	static Set<Integer>[] lines = new Set[] { line1, line2, line3, line4, line5, line6 };

	static int[] loc = { 4, 10, 12, 14, 16, 20, 24, 28, 30, 32, 34, 40 };

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int size = 0;
		for (int i=0; i<5; i++) {

			char[] row = br.readLine().toCharArray();

			for (int j=0; j<9; j++) {

				int idx = 9*i + j;

				if (size < 12 && idx == loc[size]) {
					
					if (row[j] >= 65 && row[j] <= 76) {
						select[size] = row[j];
						visited[row[j]-65] = true;
					}
					
					else {
						select[size] = -1;
					}

					size ++;
				}
			}
		}

		// dfs
		dfs(0);

		// result
		StringBuilder sb = new StringBuilder();
		size = 0;
		for (int i=0; i<5; i++) {
			for (int j=0; j<9; j++) {

				int idx = 9*i + j;
				if (size < 12 && idx == loc[size]) {
					sb.append(ans.charAt(size));
					size++;
				}
				
				else sb.append(".");

			}

			sb.append("\n");
		}

		System.out.print(sb);
	}






	static void dfs(int size) {

		if (ans != null) return;

		if (size == 12) {

			if (!validateAll()) return;

			StringBuilder sb = new StringBuilder();
			for (int i=0; i<12; i++) sb.append((char) (select[i]));
			ans = sb.toString();

			return;
		}

		if (select[size] != -1) {
			dfs(size+1);
			return;
		}

		for (int i=0; i<12; i++) {

			if (visited[i]) continue;
			visited[i] = true;
			select[size] = i+65;
			dfs(size+1);
			visited[i] = false;
			select[size] = -1;

		}
	}

	
	

	static boolean validateAll() {
		
		for (Set<Integer> line : lines) {
			
			int sum = 0;
			for (int idx : line) {
				
				if (select[idx] == -1) return false;
				sum += select[idx];
				
			}
			
			if (sum != 64*4 + 26) return false;
		}
		
		return true;
	}

}
