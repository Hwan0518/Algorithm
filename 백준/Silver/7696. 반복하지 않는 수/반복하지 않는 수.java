import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;



	public static void main(String[] args) throws IOException {

		// input -> 한번에 입력받아서 딱 1회 탐색한다
		int[] inputs = new int[1_000_001];
		List<Integer> tc = new ArrayList<>();

		int n = Integer.parseInt(br.readLine());
		int max = n;
		while (n != 0) {
			inputs[n] = -1;
			tc.add(n);
			n = Integer.parseInt(br.readLine());
			max = Integer.max(max, n);
		}

		// set-up
		int cnt = 1;
		int num = 1;
		boolean[] visited = new boolean[10];

		// max Cnt까지 탐색한다
		loop: while (cnt <= max) {

			// 탐색 시작
			int cur = num;
			while (cur > 0) {

				// 자릿수마다 10으로 계속 나눠서 확인 -> 방문한적 있는 수라면 num 증가 & visited 초기화하고 다시 탐
				if (visited[cur%10]) {
					num ++;
					for (int i=0; i<10; i++) visited[i] = false;
					continue loop;
				}

				// 방문처리
				visited[cur%10] = true;
				cur /= 10;
			}

			// 입력으로 주어진 cnt가 되면, 정답을 저장
			if (inputs[cnt] == -1) inputs[cnt] = num;

			// 같은 수가 없다면 cnt, num 증가 및 visited 초기화
			cnt ++;
			num ++;
			for (int i=0; i<10; i++) visited[i] = false;
		}

		// result
		for (int key : tc) System.out.println(inputs[key]);
	}

}