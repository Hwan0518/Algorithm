import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 1. 투포인터
 * - 정렬 시킨 다음 투포인터 쓰자
 * - 합이 m보다 작으면 -> left 증가
 * - 합이 m보다 크면 -> 끝
 * - 반례: 1 50 60 99
 *
 * 2. 완탐
 * - 3중 for문 돌려도 100*100*100 = 1000000
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n,m
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		// card
		List<Integer> card = Arrays.stream(br.readLine().split(" "))
			.map(Integer::parseInt)
			.collect(Collectors.toList());

		// searching
		int answer = 0;
		for (int i=0; i<n-2; i++){
			for (int j=i+1; j<n-1; j++) {
				for (int k=j+1; k<n; k++) {
					int sum = card.get(i) + card.get(j) + card.get(k);
					if (sum <= m) {
						answer = Math.max(answer, sum);
					}
				}
			}
		}

		// answer
		System.out.println(answer);
	}

}