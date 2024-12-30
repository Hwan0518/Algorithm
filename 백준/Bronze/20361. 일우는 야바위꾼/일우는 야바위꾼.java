import java.io.*;
import java.util.*;

/**
 * 시뮬레이션
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int x = Integer.parseInt(input[1]);
		int k = Integer.parseInt(input[2]);
		// start game
		for (int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a==x) {
				x = b;
			} else if (b==x) {
				x = a;
			}
		}
		// answer
		System.out.println(x);
	}

}