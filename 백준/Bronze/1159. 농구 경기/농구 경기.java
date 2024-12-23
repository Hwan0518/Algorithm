import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * condition
 * - 성의 첫 글자가 같은 선수 5명을 선발
 *
 * answer
 * - 가능한 성의 첫 글자를 사전순으로 공백없이 출력
 * - 5명보다 적다면 PREDAJA 출력
 *
 * approach
 * - HashMap 사용
 * - 입력받은 이름의 첫 번째 값-97의 cnt 증가
 * - 모든 입력이 끝난 후 cnt가 5 이상인 값들을 sb에 추가 후 출력
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<Character, Integer> result = new HashMap<>();
		// 갯수 확인
		for (int i=0; i<n; i++) {
			char first = br.readLine().toCharArray()[0];
			Integer cnt = result.get(first);
			if (cnt != null) {
				result.put(first, cnt+1);
			} else {
				result.put(first, 1);
			}
		}
		// 5 이상인지 확인
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Character, Integer> entry: result.entrySet()) {
			if (entry.getValue() >= 5) {
				sb.append(entry.getKey());
			}
		}
		// 출력할 성이 있다면 정렬하여 출력
		char[] array = sb.toString().toCharArray();
		if (array.length == 0) {
			System.out.println("PREDAJA");
			return;
		}
		Arrays.sort(array);
		StringBuilder sortedSb = new StringBuilder();
		for (char c : array) {
			sortedSb.append(c);
		}
		System.out.println(sortedSb);
	}



}