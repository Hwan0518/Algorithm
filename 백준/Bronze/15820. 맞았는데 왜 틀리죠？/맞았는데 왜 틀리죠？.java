import java.io.*;
import java.util.*;

/**
 * condition
 * - 조건 분
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		// s1,s2
		int s1 = Integer.parseInt(st1.nextToken());
		int s2 = Integer.parseInt(st1.nextToken());
		// sample
		if (!calc(false, s1, br)) return;
		// system
		if (!calc(true, s2, br)) return;
		// all success
		System.out.println("Accepted");
	}
	
	
	public static boolean calc(boolean isSystem, int cnt, BufferedReader br) throws IOException {
		// search
		for (int i=0; i<cnt; i++) {
			String[] input = br.readLine().split(" ");
			if (!input[0].equals(input[1])) {
				System.out.println(isSystem? "Why Wrong!!!" : "Wrong Answer");
				return false;
			}
		}
		return true;
	}

}