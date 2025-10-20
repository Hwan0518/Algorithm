import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] A;
	static int[] B;
	static int[] C;
	static int[] D;
	static int[] abSum;
	static int[] cdSum;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		A = new int[n];
		B = new int[n];
		C = new int[n];
		D = new int[n];

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		// set-up
		abSum = getSumArr(A, B);
		Arrays.sort(abSum);

		cdSum = getSumArr(C, D);
		Arrays.sort(cdSum);

		// two pointer
		long result = twoPointer();

		// result
		System.out.print(result);
	}


	static long twoPointer() {

		long cnt = 0;

		int abPointer = 0;
		int cdPointer = n*n-1;

		while (abPointer < n*n && cdPointer >= 0) {

			int sum = abSum[abPointer] + cdSum[cdPointer];

			if (sum == 0) {

				int cdCnt = 1;
				int cdTemp = cdPointer-1;
				while (cdTemp >= 0 && cdSum[cdTemp] == cdSum[cdPointer]) {
					cdCnt ++;
					cdTemp --;
				}

				int abCnt = 1;
				int abTemp = abPointer+1;
				while (abTemp < n*n && abSum[abTemp] == abSum[abPointer]) {
					abCnt ++;
					abTemp ++;
				}

				cdPointer -= cdCnt;
				abPointer += abCnt;
				cnt += (long) cdCnt * abCnt;
			}

			else if (sum > 0) cdPointer --;
			else abPointer ++;
		}

		return cnt;
	}


	static int[] getSumArr(int[] arr1, int[] arr2) {

		int[] temp = new int[n*n];

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) temp[n*i+j] = arr1[i] + arr2[j];
		}

		return temp;
	}

}
