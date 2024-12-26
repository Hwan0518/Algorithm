import java.io.*;

/**
 * approach
 * 1. 구현
 * - 뒤에가 앞에보다 클때 카운트하자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		int n = Integer.parseInt(br.readLine());
		int[] towers = new int[n];
		String[] sTowers = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			towers[i] = Integer.parseInt(sTowers[i]);
		}
		// calc
		int cnt = 1;
		for (int i=0; i<n-1; i++) {
			if (towers[i+1] >= towers[i]) {
				cnt ++;
			}
		}
		// answer
		System.out.println(cnt);
	}
	
}