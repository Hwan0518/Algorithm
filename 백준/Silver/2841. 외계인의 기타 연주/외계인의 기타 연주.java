

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Main {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, f;
	static ArrayDeque<Integer>[] stacks;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());

		stacks = new ArrayDeque[7];
		for (int i=0; i<7; i++) stacks[i] = new ArrayDeque<>();

		// play
		int cnt = 0;

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			int string = Integer.parseInt(st.nextToken());
			int fret = Integer.parseInt(st.nextToken());

			cnt += press(string, fret);
		}

		// result
		System.out.print(cnt);
	}


	static int press(int string, int fret) {

		int cnt = 0;
		ArrayDeque<Integer> stack = stacks[string];

		// 선택한 string에서 fret보다 큰 값을 제거
		Integer cur = stack.peekLast();
		while (cur!=null && cur>fret) {
			stack.removeLast();
			cnt ++;

			cur = stack.peekLast();
		}

		// cur이 null이거나, cur과 fret이 다르다면 추가
		if (cur==null || cur<fret) {
			cnt ++;
			stack.addLast(fret);
		}

		// result
		return cnt;
	}

}
