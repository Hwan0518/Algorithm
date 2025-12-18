import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	static int n, m, l, k;
	static Node[] stars;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		stars = new Node[k];
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			stars[i] = new Node(r, c);
		}

		// search -> 각 별똥별이 만드는 변만 탐색(모서리에 걸치는게 최대니까)
		int maxCnt = search();

		// result
		System.out.print(k-maxCnt);
	}


	static int search() {

		int maxCnt = 0;

		// r을 고정시키고 탐색
		for (int i=0; i<k; i++) {
			int r = stars[i].r;

			// 모든 c에 대해 탐색
			for (int j=0; j<k; j++) {
				int c = stars[j].c;

				int curCnt = 0;

				// 모든 별에 대해서 탐색
				for (Node star : stars) {
					if (star.r >= r && star.r <= r+l
						&& star.c >= c && star.c <= c+l) curCnt++;
				}

				maxCnt = Integer.max(maxCnt, curCnt);
			}
		}

		// result
		return maxCnt;
	}


	static class Node {

		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}