import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static Node[] checkPoints;
	static Dist[] netDistWithSkip;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		int totalDist = 0;
		checkPoints = new Node[n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			checkPoints[i] = new Node(x, y, i);

			if (i>0) totalDist += Math.abs(x - checkPoints[i-1].x) + Math.abs(y - checkPoints[i-1].y);
		}

		// netDist = originDist - newDist를 구한다 (거리 2개가 없어지고 1개가 새로 생김)
		netDistWithSkip = new Dist[n-2];
		for (int i=1; i<n-1; i++) {

			Node skip = checkPoints[i];
			Node before = checkPoints[i-1];
			Node after = checkPoints[i+1];

			int origin1Dist = Math.abs(skip.x - before.x) + Math.abs(skip.y - before.y);
			int origin2Dist = Math.abs(skip.x - after.x) + Math.abs(skip.y - after.y);
			int newDist = Math.abs(after.x - before.x) + Math.abs(after.y - before.y);

			int netDist = totalDist - (origin1Dist + origin2Dist - newDist);

			netDistWithSkip[i-1] = new Dist(i-1, i+1, netDist);
		}

		Arrays.sort(netDistWithSkip, (o1, o2) -> o1.dist - o2.dist);

		// result
		System.out.print(netDistWithSkip[0].dist);
	}


	static class Node {

		int x,y,idx;

		Node(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}


	static class Dist {

		int idx1, idx2, dist;

		Dist(int idx1, int idx2, int dist) {
			this.idx1 = idx1;
			this.idx2 = idx2;
			this.dist = dist;
		}
	}

}