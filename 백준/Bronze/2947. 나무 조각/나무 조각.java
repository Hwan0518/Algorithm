import java.io.*;
import java.util.Arrays;

/**
 * 버블정렬 구현
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		// do
		int[] answer = {1,2,3,4,5};
		StringBuilder sb = new StringBuilder();
		while (!Arrays.equals(input, answer)) {
			for (int i=0; i<4; i++) {
				int left = input[i];
				int right = input[i+1];
				if (left > right) {
					input[i] = right;
					input[i+1] = left;
					for (int num:input) {
						sb.append(num).append(" ");
					}
					sb.append("\n");
				}
			}
		}
		System.out.println(sb);
		br.close();
	}

}