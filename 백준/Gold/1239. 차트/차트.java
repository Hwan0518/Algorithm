import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] arr, select;
	static boolean[] visited;


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

			// 만약 50이 나오면, 선은 무조건 1개밖에 될 수가 없음
			if (arr[i] == 50) {
				System.out.print(1);
				return;
			}
		}

		select = new int[n];
		visited = new boolean[n];

		// dfs
		int order = 0;
		int max = dfs(order);

		// result
		System.out.print(max);
	}


	// 전체 순열을 탐색
	static int dfs(int order) {

		// base-condition
		if (order == n) {

			int cnt = 0;

			// 누적합 경곗점 찾기
			int sum = 0;
			boolean[] mark = new boolean[100]; // 0~99까지인데, 100은 0과 동일하기에 제외

			mark[0] = true;
			for (int i=0; i<n-1; i++) {
				sum += select[i];
				mark[sum] = true;
			}

			// 중앙을 지나는 선 확인 (중복 결과를 제거하기 위해 50 미만으로 탐색)
			for (int point=0; point<50; point ++) {
				// 누적합 시작점+50 이 true라면, 50만큼 비율을 채웠으므로 선분이 생겼다는 뜻
				if (mark[point] && mark[point+50]) cnt ++;
			}

			// 결과
			return cnt;
		}

		// 선택
		int max = 0;
		for (int i=0; i<n; i++) {

			// visited
			if (visited[i]) continue;

			select[order] = arr[i];
			visited[i] = true;
			max = Integer.max(max, dfs(order+1));
			visited[i] = false;
		}

		// result
		return max;
	}

}



/*
내 반례

8
10 10 10 10 13 14 16 17
-> 3

4
10 20 30 40
-> 1

6
10 10 10 20 20 30
-> 2

6
30 19 1 24 20 6
-> 2
 */