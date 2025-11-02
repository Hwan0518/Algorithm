import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static long x;
	static long[] arr;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		x = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new long[n];
		for (int i = 0; i < n; i++) arr[i] = (Long.parseLong(st.nextToken()));
		Arrays.sort(arr);

		// two pointer
		int result = twoPointer();

		// result
		System.out.print(result);
	}


	static int twoPointer() {

		int cnt = 0; // 재활용으로 만들어진 병의 수
		int pass = 0; // 두 병의 합이 절반이 안되는 병들의 수 -> 다시 재활용 진행

		int p1 = 0;
		int p2 = arr.length-1;

		while (p1 < p2) {

			long v1 = arr[p1];
			long v2 = arr[p2];

			// p2 용량 확인
			if (v2 == x) {
				p2 --;
				cnt ++;
				continue;
			}

			// 두 용량의 합이 x/2(절반) 이상인 경우 -> 재활용해서 새로운 병 하나 생성
			if (v1+v2 >= (x+1)/2) {
				p1 ++;
				p2 --;
				cnt ++;
			}

			// x/2 미만인 경우 -> p1 증가시키고, pass 갯수 ++
			else {
				pass ++;
				p1 ++;
			}
		}

		// p1 == p2인 경우 -> 해당 값이 x와 같으면 cnt ++; 아니면 pass ++;
		if (p1 == p2) {
			if (arr[p1] == x) cnt ++;
			else pass ++;
		}

		// pass한 p1들 -> 두 개의 합은 x/2 미만이므로, 세 개가 모여야 재활용해서 1병이 된다
		cnt += pass/3;

		// 결과
		return cnt;
	}

}
