import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * r: 2*n
 * c: 2*n
 * -> r+1개를 *로, 2*(n-r-1) 개를 공백으로 채움
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		repeatStar(n, 0, n, true);
		repeatStar(n, n-2, -1, false);
	}


	public static void repeatStar(int n, int stt, int end, boolean state) {
		StringBuilder sb = new StringBuilder();
		int add = state ? 1 : -1;
		for (int r=stt; (state? r<end : r>end); r += add) {
			sb.append("*".repeat(r + 1))
				.append(" ".repeat(2 * (n - r - 1)))
				.append("*".repeat(r + 1))
				.append("\n");
		}
		if (state) sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);
	}


}