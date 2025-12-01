import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;



	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

		// two pointer
		int max = twoPointer(n, k, arr);

		// result
		System.out.print(max);
	}


	static int twoPointer(int n, int k, int[] arr) {

		// set-up
		int max = 0; // 최장길이
		int p1 = 0; // 포인터1
		int p2 = 0; // 포인터2
		int kCnt = 0; // k 개수
		List<Integer> oddIdxs = new ArrayList<>(); // 홀수 idx 기억
		int farLeftOddIdx = 0; // 현재 뽑은 k개의 홀수 중, 가장 왼쪽 oddIdx

		// search
		while (p2 < n) {

			// cur val
			int cur = arr[p2];

			// cur == even
			if (cur % 2 == 0) {
				// 짝수라면 p2-p1 길이가 현재 짝수길이가 되므로, 이를 업데이트
				max = Integer.max(max, p2-p1-kCnt+1);
			}

			// cur == odd
			else {
				// oddIdxs에 현재 인덱스 추가
				oddIdxs.add(p2);

				// k만큼 뽑지 않은 경우
				if (kCnt < k) {
					kCnt ++;
				}

				// 이미 k만큼 뽑은 경우
				else {
					// p1을 farLeftOdd+1로, farLeftOdd는 그 다음으로 이동
					p1 = oddIdxs.get(farLeftOddIdx)+1;
					farLeftOddIdx ++;
				}
			}

			// p2 증가
			p2 ++;
		}

		// return max len
		return max;
	}


}