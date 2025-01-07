import java.io.*;
import java.util.*;

/**
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String n = br.readLine();
		while (!n.equals("0")) {
			sb.append(check(n) ? "yes" : "no").append("\n");
			n = br.readLine();
		}
		System.out.print(sb);
	}
	
	private static boolean check(String n) {
		char[] arr = n.toCharArray();
		int size = arr.length;
		int mid = size/2;
		for (int i=0; i<mid; i++) {
			if (arr[i] != arr[size-i-1]) {
				return false;
			}
		}
		return true;
	}
}