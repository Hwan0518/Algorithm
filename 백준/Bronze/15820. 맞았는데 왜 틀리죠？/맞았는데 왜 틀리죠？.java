import java.io.*;
import java.util.*;

/**
 * condition
 * - 조건 분기
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		// s1,s2
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int s1 = Integer.parseInt(st1.nextToken());
		int s2 = Integer.parseInt(st1.nextToken());
		// sample
		boolean sample = calc(s1);
		// system
		boolean system = calc(s2);
		// answer
		if (!sample) {
			bw.append("Wrong Answer");
		} else if (!system) {
			bw.append("Why Wrong!!!");
		} else {
			bw.append("Accepted");
		}
		// all success
		bw.flush();
		br.close();
		bw.close();
	}
	
	
	public static boolean calc(int cnt) throws IOException {
		// search
		while (cnt-- >0) {
			String[] input = br.readLine().split(" ");
			if (!input[0].equals(input[1])) {
				return false;
			}
		}
		return true;	
	}

}