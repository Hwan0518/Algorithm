import java.io.*;

/**
 * 문자열 구현
 * - 입력값 -> 가로,세로 n번씩 곱해서 출력
 */
public class Main {
	
	static String[] output = {"@@@@@","@","@@@@@","@","@"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (String o:output) {
			for (int i=0; i<n; i++) {
				sb.append(o.repeat(n));
				sb.append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}

}