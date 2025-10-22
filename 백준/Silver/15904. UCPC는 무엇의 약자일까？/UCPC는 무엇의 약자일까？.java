import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();




	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		// search
		boolean possible = getUCPC(s);

		// result
		String result;
		if (possible) result = "I love UCPC";
		else result = "I hate UCPC";

		System.out.print(result);
	}


	static boolean getUCPC(String s) {

		// U, C1, P, C가 있는지 확인
		char[] arr = s.toCharArray();

		int U = 0;
		int C1 = 0;
		int P = 0;
		int C2 = 0;

		for (char c : arr) {

			if (c == 'U') U ++;

			else if (c == 'C') {
				// U 다음에 나와야함
				if (U > 0) C1 ++;
				// P 다음에 나와야함
				if (P > 0) C2 ++;
			}
			else if (c == 'P' && C1 > 0) P ++; // C1 다음에 나와야함
		}

		return U > 0 && C1 > 0 && P > 0 && C2 > 0;
	}
}