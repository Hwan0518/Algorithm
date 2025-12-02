import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;



	public static void main(String[] args) throws IOException {

		// input
		char[] word = br.readLine().toCharArray();

		// find
		int time = 0;
		for (char c : word) {

			// index 계산
			int idx = c - 'A';

			// 예외
			if (c == 'S' || c == 'V' || c == 'Y') idx --;
			else if (c == 'Z') idx -=2;

			// 시간 계산
			time += 3 + (idx / 3);
		}

		// result
		System.out.print(time);
	
	}


}
