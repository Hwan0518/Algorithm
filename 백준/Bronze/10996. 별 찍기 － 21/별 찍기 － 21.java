import java.io.*;

/**
 * condition
 * - 별별찍기찍기
 * 
 * approach
 * - 첫째줄: (n+1)//2개
 * - 둘째줄: n//2개
 * - 총 n번을 반복해서 출력
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for (int i=0; i<n; i++) {
			sb.append("* ".repeat((n+1)/2)+"\n")
			.append(" *".repeat(n/2)+"\n");
		}
		System.out.println(sb);
		br.close();
	}
	
}