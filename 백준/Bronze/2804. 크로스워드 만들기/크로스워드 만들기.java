import java.io.*;
import java.util.*;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		char[] a = input[0].toCharArray();
		char[] b = input[1].toCharArray();
		int[] idx = findCommon(a, b);
		int aIdx = idx[0];
		int bIdx = idx[1];
		printResult(a, b, aIdx, bIdx);	
	}
	
	
	public static void printResult(char[] a, char[] b, int aIdx, int bIdx) {
		int r = b.length;
		int c = a.length;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<r; i++) {
			if (i == bIdx) {
				for (char aW: a) {
					sb.append(aW);
				}
				sb.append("\n");
				continue;
			}
			for (int j=0; j<c; j++) {
				if (j == aIdx) {
					sb.append(b[i]);
				} else {
					sb.append(".");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	
	public static int[] findCommon(char[] a, char[] b) {
		int i = 0;
		int j = 0;
		while (i < a.length) {
			while (j < b.length) {
				if (b[j] == a[i]) {
					return new int[] {i,j};
				}
				j++;
			}
			j = 0;
			i++;
		}
		return new int[] {i,j};
	}
	
}