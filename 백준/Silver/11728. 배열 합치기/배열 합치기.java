import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[] a, b;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		a = new int[n];
		for (int i=0; i<n; i++) a[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		b = new int[m];
		for (int i=0; i<m; i++) b[i] = Integer.parseInt(st.nextToken());

		// twoPinter
		twoPointer();

		// result
		System.out.print(sb);
	}


	static void twoPointer() {

		int p1 = 0;
		int p2 = 0;

		while (p1<n || p2<m) {

			// 한쪽이 끝에 도달한 경우
			if (p1 == n) {
				for (int i=p2; i<m; i++) sb.append(b[i]).append(" ");
				break;
			}
			else if (p2 == m) {
				for (int i=p1; i<n; i++) sb.append(a[i]).append(" ");
				break;
			}

			// 탐색
			int v1 = a[p1];
			int v2 = b[p2];

			if (v1 <= v2) {
				sb.append(v1).append(" ");
				p1 ++;
			}

			else {
				sb.append(v2).append(" ");
				p2 ++;
			}
		}
	}


}


