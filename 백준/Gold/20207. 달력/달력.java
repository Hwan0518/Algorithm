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
		int[] count = new int[365+1];

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			for (int j=stt; j<=end; j++) count[j] ++;
		}

		// calc Area
		int area = 0;

		int x = 0;
		int y = 0;
		for (int day=1; day<=365; day++) {

			// 끊어졌다면 면적 계산 & x,y 갱신
			if (count[day] == 0) {
				area += x * y;
				x = 0;
				y = 0;
			}

			// 연속됐다면 x와 y갱신
			else {
				x = Integer.max(x, count[day]);
				y ++;
			}
		}

		if (count[365] != 0) area += x*y;

		return area;
	}

}