import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[] seq;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		// input
		String s = br.readLine();

		seq = new int[s.length()];
		for (int i=0; i<s.length(); i++) seq[i] = -1;

		visited = new boolean[51];

		// dfs
		dfs(0, 0, s);

		// result
		System.out.print(sb);
	}


	static void dfs(int size, int idx, String s) {

		if (sb.length() != 0) return;

		if (idx == s.length()) {

			// validate1 : 1을 무조건 사용해야함 (1부터 시작)
			if (!visited[1]) return;

			// validate2 : 연속되게 true여야한다. 중간에 false 있으면 안됨
			for (int num=1; num<50; num++) {
				if (!visited[num] && visited[num + 1]) return;
			}

			// ans
			for (int sq : seq) {
				if (sq == -1) break;
				sb.append(sq).append(" ");
			}

			return;
		}

		// 만들 수 있는 수
		int num1 = s.charAt(idx) - '0';
		int num2 = -1;

		if (idx < s.length()-1) num2 = Integer.parseInt(s.substring(idx, idx+2));

		// idx만 사용 -> 현재가 0이면 사용 못함
		if (num1 != 0 && !visited[num1]) {

			visited[num1] = true;
			seq[size] = num1;
			dfs(size+1, idx+1, s);
			visited[num1] = false;
			seq[size] = -1;
		}

		// idx, idx+1 사용하는 경우 (최대 두자릿수임. 50 넘어가면 안됨)
		if (num2 >= 10 && num2 <= 50 && !visited[num2]) {

			visited[num2] = true;
			seq[size] = num2;
			dfs(size+1, idx+2, s);
			visited[num2] = false;
			seq[size] = -1;
		}
	}


}