import java.io.*;

/**
 * condition
 * - 3명 레이팅 합 <k 는 가입안됨
 * - 모든 팀원 L 이상이어야함
 * 
 * approach
 * 1. 
 * - 뒤에가 앞에보다 클때 카운트하자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n,k,l
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int k = Integer.parseInt(input[1]);
		int l = Integer.parseInt(input[2]);
		// rating
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for (int i=0; i<n; i++) {
			// input rating
			int[] rating = new int[3];
			String[] sRating = br.readLine().split(" ");
			for (int j=0; j<3; j++) rating[j]=Integer.parseInt(sRating[j]);
			// calc
			if (calc(rating, k, l)) {
				for (int j=0; j<3; j++) sb.append(rating[j] + " "); 
				cnt ++;
			}
		}
		// answer
		System.out.println(cnt + "\n" + sb);		
	}
	
	public static boolean calc(int[] rating, int k, int l) {
		int sum = 0;
		// check rating
		for (int r:rating) {
			if (r < l) {
				return false;
			} else {
				sum += r;
			}
		}
		// check sum
		return sum >= k ? true : false;
	}
	
}