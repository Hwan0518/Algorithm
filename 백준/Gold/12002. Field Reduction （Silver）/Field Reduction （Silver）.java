import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static TreeSet<Node> xts;
	static TreeSet<Node> yts;
	static int min = Integer.MAX_VALUE;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		xts = new TreeSet<>((o1, o2) -> o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y);
		yts = new TreeSet<>((o1, o2) -> o1.y != o2.y ? o1.y - o2.y : o1.x - o2.x);

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			Node cur = new Node(x, y);

			xts.add(cur);
			yts.add(cur);
		}

		// dfs
		if (n > 3) dfs(0);
		else min = 0;

		// result
		System.out.print(min);
	}


	static void dfs(int removeCnt) {

		if (removeCnt == 3) {

			if (xts.isEmpty() && yts.isEmpty()) {
				min = 0;
				return;
			}

			else {
				min = Integer.min(min, getMaxArea());
				return;
			}
		}

		// max X 제거
		Node maxX = xts.last();
		xts.remove(maxX);
		yts.remove(maxX);
		dfs(removeCnt +1);
		xts.add(maxX);
		yts.add(maxX);

		// min X 제거
		Node minX = xts.first();
		xts.remove(minX);
		yts.remove(minX);
		dfs(removeCnt +1);
		xts.add(minX);
		yts.add(minX);

		// max Y 제거
		Node maxY = yts.last();
		xts.remove(maxY);
		yts.remove(maxY);
		dfs(removeCnt +1);
		xts.add(maxY);
		yts.add(maxY);

		// min Y 제거
		Node minY = yts.first();
		xts.remove(minY);
		yts.remove(minY);
		dfs(removeCnt +1);
		xts.add(minY);
		yts.add(minY);
	}


	static int getMaxArea() {

		// get x
		Node maxX = xts.last();
		Node minX = xts.first();

		// get y
		yts.remove(maxX);
		yts.remove(minX);

		Node maxY = !yts.isEmpty() ? yts.last() : maxX;
		Node minY = !yts.isEmpty() ? yts.first() : maxX;

		yts.add(maxX);
		yts.add(minX);

		// get maxX, minX, maxY, minY
		int[] xArr = { maxX.x, minX.x, maxY.x, minY.x };
		int[] yArr = { maxX.y, minX.y, maxY.y, minY.y };

		int xDiff = Arrays.stream(xArr).max().getAsInt() - Arrays.stream(xArr).min().getAsInt();
		int yDiff = Arrays.stream(yArr).max().getAsInt() - Arrays.stream(yArr).min().getAsInt();

		// return area
		return xDiff * yDiff;
	}


	static class Node {

		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}


}