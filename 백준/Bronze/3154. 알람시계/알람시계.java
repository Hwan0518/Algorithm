import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static Node[] keyboard;
	static List<Integer> hourCandidate, minuteCandidate;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		keyboard = new Node[10];
		for (int i=1; i<10; i++) {
			int x = (i-1) / 3;
			int y = (i-1) % 3;
			keyboard[i] = new Node(x, Integer.max(y, 0));
		}
		keyboard[0] = new Node(3, 1);

		String[] target = br.readLine().split(":");
		int targetHour = Integer.parseInt(target[0]);
		int targetMinute = Integer.parseInt(target[1]);

		// find candidates
		hourCandidate = findCandidates(targetHour, 24);
		minuteCandidate = findCandidates(targetMinute, 60);

		// find result
		String result = search();

		// result
		System.out.print(result);

	}


	static String search() {

		String ans = "";
		int minEffort = Integer.MAX_VALUE;

		// hh:mm -> ab:cd 로 가정
		int a,b,c,d;
		for (int h : hourCandidate) {
			for (int m : minuteCandidate) {

				int curEffort = 0;

				a = h / 10;
				b = h % 10;
				c = m / 10;
				d = m % 10;

				int[] temp = { a, b, c, d };

				for (int i=0; i<3; i++) {

					int idx1 = temp[i];
					int idx2 = temp[i+1];

					curEffort += Math.abs(keyboard[idx1].x - keyboard[idx2].x) + Math.abs(keyboard[idx1].y - keyboard[idx2].y);
				}

				if (curEffort < minEffort) {
					minEffort = curEffort;
					ans = "" + a + b + ":" + c + d;
				}
			}
		}

		return ans;
	}


	static List<Integer> findCandidates(int target, int divisor) {

		List<Integer> candidates = new ArrayList<>();

		int cur = 1;

		while (cur < 100) {
			if (cur % divisor == target) candidates.add(cur);
			cur ++;
		}

		return candidates;
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
