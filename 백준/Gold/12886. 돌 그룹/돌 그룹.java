import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int a, b, c, target;
	static boolean possible = false;
	static Set<String> visited;
	static boolean[] done = new boolean[3];

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		target = (a+b+c)/3;

		visited = new HashSet<>();

		// dfs
		String val = getVal();
		visited.add(val);
		dfs();

		// result
		System.out.print(possible ? 1 : 0);
	}


	static void dfs() {

//		System.out.println("a,b,c = " +a+","+b+","+c);

		if (possible || (a+b+c)%3 != 0) return;

		if (a==b && b==c) {
			possible = true;
			return;
		}

		// done check
		if (a == target) done[0] = true;
		if (b == target) done[1] = true;
		if (c == target) done[2] = true;

		int small;

		// a와 b를 고른 경우
		if (a != b && !done[0] && !done[1]) {

			if (a < b) {
				small = a;
				a += small;
				b -= small;

				String val = getVal();
				if (!visited.contains(val)) {
					visited.add(val);
					dfs();
				}

				a -= small;
				b += small;
			}

			else {
				small = b;
				a -= small;
				b += small;

				String val = getVal();
				if (!visited.contains(val)) {
					visited.add(val);
					dfs();
				}

				a += small;
				b -= small;
			}
		}

		// a와 c를 고른 경우
		if (a != c && !done[0] && !done[2]) {

			if (a < c) {
				small = a;
				a += small;
				c -= small;

				String val = getVal();
				if (!visited.contains(val)) {
					visited.add(val);
					dfs();
				}

				a -= small;
				c += small;
			}

			else {
				small = c;
				a -= small;
				c += small;

				String val = getVal();
				if (!visited.contains(val)) {
					visited.add(val);
					dfs();
				}

				a += small;
				c -= small;
			}
		}

		// b와 c를 고른 경우
		if (b != c && !done[1] && !done[2]) {

			if (b < c) {
				small = b;
				b += small;
				c -= small;

				String val = getVal();
				if (!visited.contains(val)) {
					visited.add(val);
					dfs();
				}

				b -= small;
				c += small;
			}

			else {
				small = c;
				b -= small;
				c += small;

				String val = getVal();
				if (!visited.contains(val)) {
					visited.add(val);
					dfs();
				}

				b += small;
				c -= small;
			}
		}
	}


	static String getVal() {

		int max = Integer.max(a, Integer.max(b, c));
		int min = Integer.min(a, Integer.min(b, c));
		int mid = a+b+c-max-min;

		return min+","+mid+","+max;
	}


}