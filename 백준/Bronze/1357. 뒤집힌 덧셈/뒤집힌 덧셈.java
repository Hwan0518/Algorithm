import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * 문자열 구현
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		char[] x = st.nextToken().toCharArray();
		char[] y = st.nextToken().toCharArray();
		int rX = toReverse(x);
		int rY = toReverse(y);
		System.out.print(toReverse(String.valueOf(rX+rY).toCharArray()));
	}
	
	public static int toReverse(char[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i=arr.length-1; i>-1; i--) {
			sb.append(arr[i]);
		}
		return Integer.parseInt(sb.toString());
	}
	
}