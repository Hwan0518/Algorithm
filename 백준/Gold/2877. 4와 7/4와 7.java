import java.io.*;
import java.time.LocalDate;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int k;
	static int limit;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());

		// find n, m;
		int n = findN();
		int m = findM(n);

		// change 2-digit
		String result = change2Digit(n, m);

		// result
		System.out.print(result);
	}


	static String change2Digit(int n, int m) {

		int[] mTo2Digit = new int[n];

		int i=n-1;
		while (m > 0) {
			mTo2Digit[i] = m % 2;
			i --;
			m = m/2;
		}

		StringBuilder stringNum = new StringBuilder();
		for (int v : mTo2Digit) stringNum.append(v==0 ? "4" : "7");

		return stringNum.toString();
	}


	static int findM(int n) {

		int before = n>1 ? limit - (int) Math.pow(2, n) : 0;

		return k-before -1;
	}


	static int findN() {

		int n=1;
		limit = (int) Math.pow(2, n);
		while (k > limit) {
			n++;
			limit += (int) Math.pow(2, n);
		}

		return n;
	}

}