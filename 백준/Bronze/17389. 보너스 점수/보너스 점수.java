import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();

		// calc
		int score = 0;
		int bonus = 0;
		for (int i=1; i<=n; i++) {

			boolean collect = arr[i-1] == 'O';

			if (collect) {
				score += (i+bonus);
				bonus ++;
			}
			else {
				bonus = 0;
			}
		}

		// result
		System.out.print(score);
	}

}