import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		// search
		int result = findArea();

		// result
		System.out.print(result);
	}


	static int findArea() throws IOException {

		// sort schedules
		int[][] schedules = new int[n][2];

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			schedules[i][0] = stt;
			schedules[i][1] = end;
		}

		Arrays.sort(schedules, (o1, o2) -> {
			return o1[0] != o2[0]
				? o1[0] - o2[0]  // stt 오름차순
				: o2[1] - o1[1]; // end 내림차순
		});

		// calc Area
		int area = 0;
		boolean[][] visited = new boolean[n+1][365+1];

		int maxR = 1;
		int maxC = schedules[0][1];
		int minC = schedules[0][0];
		int lastDay = schedules[0][1];

		for (int i=0; i<n; i++) {

			int curStt = schedules[i][0];
			int curEnd = schedules[i][1];

			// 현재 일정을 입력할 row를 계산
			int curR = 1;
			for (int c=curStt; c<=curEnd; c++) {
				while (visited[curR][c]) curR++;
			}

			// 방문처리
			for (int c = curStt; c <= curEnd; c++) visited[curR][c] = true;

			// 연속되는 경우
			if (curStt <= lastDay+1) {
				maxR = Integer.max(maxR, curR); // maxR은 curR과 비교해서 갱신
				maxC = Integer.max(maxC, curEnd); // maxC는 curEnd와 비교해서 갱신
			}

			// 연속되지 않음
			else {
				area += maxR * (maxC-minC+1);
				maxR = 1;
				minC = curStt;
				maxC = curEnd;
			}

			lastDay = Integer.max(lastDay, curEnd);
		}

		if (schedules[n-1][0] <= lastDay+1) area += maxR * (maxC-minC+1);

		return area;
	}

}