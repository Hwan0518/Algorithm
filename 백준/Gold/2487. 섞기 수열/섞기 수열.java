import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static int n;
	static int[] arr;
	static int[] cur;
	static int visitCnt = 0;
	static int[] visited;
	static int shuffleCnt = 0;
	static int[] next;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<n+1; i++) arr[i] = Integer.parseInt(st.nextToken());

		cur = new int[n + 1];
		for (int i=1; i<n+1; i++) cur[i] = i;

		next = new int[n+1];

		// search
		visited = new int[n+1];
		shuffleCnt ++;
		shuffle();
		findMinVisit();

		// result
		System.out.print(calc());

	}


	static int calc() {

		int gcd;
		long lcm = visited[1];

		for (int i=2; i<n+1; i++) {

			gcd = calcGcd(lcm, visited[i]);
			lcm = (lcm * visited[i]) / gcd;

		}

		return (int) lcm;
	}


	static int calcGcd(long n1, long n2) {
		return n1 % n2 == 0 ? (int) n2 : calcGcd(n2, n1 % n2);
	}


	static void findMinVisit() {

		while (visitCnt < n) {
			shuffleCnt ++;
			shuffle();
		}

	}


	static void shuffle() {

		for (int i=1; i<n+1; i++) {

			next[i] = cur[arr[i]];

			if (visited[i]==0 && next[i] == i) {
				visitCnt ++;
				visited[i] = shuffleCnt;
			}

		}

		// swap
		int[] temp = cur;
		cur = next;
		next = temp;
	}


}
