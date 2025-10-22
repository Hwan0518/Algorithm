import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();

	static int n;
	static PriorityQueue<Node> q;

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		// search
		for (int i=0; i<t; i++) {

			n = Integer.parseInt(br.readLine());
			q = new PriorityQueue<>((o1, o2) -> o1.smaller - o2.smaller);

			search();

			boolean hasPair = !q.isEmpty();

			// add sb
			sb.append("Pairs for ")
				.append(n)
				.append(": ");

			while (!q.isEmpty()) {

				Node pair = q.poll();
				sb.append(pair.smaller)
					.append(" ")
					.append(pair.larger)
					.append(", ");
			}

			if (hasPair) sb.setLength(sb.length()-2);
			sb.append("\n");
		}

		// result
		System.out.print(sb);
	}


	static void search() {

		int p1 = 1;
		int p2 = n-1;

		while (p1 < p2) {

			int sum = p1 + p2;

			if (sum == n) {
				q.offer(new Node(p1, p2));
				p1 ++;
				p2 --;
			}

			else if (sum < n) p1 ++;
			else p2 --;
		}
	}


	static class Node {

		int smaller;
		int larger;

		Node(int smaller, int larger) {
			this.smaller = smaller;
			this.larger = larger;
		}
	}
}