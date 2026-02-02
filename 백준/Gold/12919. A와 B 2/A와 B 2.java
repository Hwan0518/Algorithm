import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static String s,t;

	public static void main(String[] args) throws IOException {

		// input
		s = br.readLine();
		t = br.readLine();

		// dfs
//		String cur = s;
//		boolean isPossible = dfs(cur);

		// dfs2
		String cur = t;
		boolean isPossible = dfs2(cur);

		// result
		System.out.print(isPossible ? 1 : 0);
	}


	// 역방향 -> a추가/b추가가 무조건 가능한게 아니라 조건부로 가능함. 따라서 가지치기 가능
	static boolean dfs2(String cur) {

		// base-condition
		if (cur.length() == s.length()) {
			return cur.equals(s);
		}

		// a제거 가능한 경우
		boolean res1 = false;
		if (cur.charAt(cur.length()-1) == 'A') res1 = dfs2(cur.substring(0, cur.length()-1));

		// b제거 가능한 경우
		boolean res2 = false;
		sb = new StringBuilder();
		sb.append(cur.substring(1)).reverse();
		if (cur.charAt(0) == 'B') res2 = dfs2(sb.toString());

		// 결과
		return res1 || res2;
	}


	static boolean dfs(String cur) {

		// base-condition
		if (cur.length() >= t.length()) {
			return cur.equals(t);
		}

		// a추가
		boolean res1 = dfs(cur+"A");

		// b추가 & 뒤집기
		sb = new StringBuilder();
		sb.append(cur).append("B");
		boolean res2 = dfs(sb.reverse().toString());

		// result
		return res1 || res2;
	}

}