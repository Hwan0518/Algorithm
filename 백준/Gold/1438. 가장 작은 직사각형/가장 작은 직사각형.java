import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static TreeSet<Integer> x;
	static Node[] dots;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		x = new TreeSet<>();
		dots = new Node[n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			x.add(r);
			dots[i] = new Node(r, c);
		}

		Arrays.sort(dots, (o1, o2) -> o1.y - o2.y);

		// search
		int min = Integer.MAX_VALUE;

		// get x pairs
		for (int x1 : x) {
			for (int x2 : x) {

				if (x2 < x1) continue; // x2가 같거나 크다

				// calc area
				min = Integer.min(min, twoPointer(x1, x2));
			}
		}

		// result
		System.out.print(min);
	}


	static int twoPointer(int x1, int x2) {

		// set-up x coordinate
		x1 --;
		x2 ++;

		// min Area
		int minArea = Integer.MAX_VALUE;

		// find n count in y range
		int l = 0;
		int r = n/2-1;
		while (r < n) {

			int cnt = 0;

			// l1부터 l2까지 포함된 좌표 개수
			for (int i=l; i<=r; i++) {

				Node dot = dots[i];

				// 범위 밖이라면 pass (범위에 걸쳐도 포함안한다)
				if (dot.x <= x1 || dot.x >= x2) continue;

				// 포함되었다면 cnt ++
				cnt ++;
				if (cnt == n/2) break;
			}

			if (cnt == n/2) {

				// y좌표 계산
				int y1 = dots[l].y;
				int y2 = dots[r].y;
				y1 --;
				y2 ++;

				// 넓이 계산
				int curArea = (x2-x1)*(y2-y1);
				minArea = Integer.min(minArea, curArea);

				// 범위줄임
				l++;
			}

			else if (cnt < n/2) r++; // 범위 늘림
			else l++; // 범위 줄임
		}

		return minArea;
	}


	static class Node {

		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
