import java.io.*;
import java.util.*;

/*
- 입력받은 문자열중 하나를 먼저 뒤집는다
- 이후 완전탐색을 통해 '순차적으로 동일한 문자열 갯수'를 찾는다
- 정답 : (문자열의 길이 - 순차적으로 동일한 문자열 갯수) * 2
-> 투포인터로도 볼 수 있겠네


=> damn it! 그냥 철자만 바꿀 수 있으면 됌; 즉, 동일한거 몇 개 있는지 확인하라고!
*/

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] c1 = br.readLine().toCharArray();
		char[] c2 = br.readLine().toCharArray();

		Map<Character, Integer> map1 = new HashMap<>();
		Map<Character, Integer> map2 = new HashMap<>();

		for (char c : c1) {
			if (map1.containsKey(c)) map1.put(c, map1.get(c)+1);
			else map1.put(c, 1);
		}

		for (char c : c2) {
			if (map2.containsKey(c)) map2.put(c, map2.get(c)+1);
			else map2.put(c, 1);
		}

		// two pointer
//		int same = twoPointer(arr1, arr2);

		// search
		int result = search(0, map1, map2);
		result = search(result, map2, map1);

		// result
//		int result = (n-same) * 2;
		System.out.print(result);
	}


	static int search(int result, Map<Character, Integer> map1, Map<Character, Integer> map2) {

		for (char s : map1.keySet()) {

			int cnt1 = map1.get(s);
			int cnt2 = map2.containsKey(s) ? map2.get(s) : 0;

			// 비교한 문자 제거
			map2.remove(s);

			result += Math.abs(cnt1 - cnt2);
		}

		return result;
	}


	static int twoPointer(char[] arr1, char[] arr2) {

		// reverse arr1
		for (int i=0; i<n/2; i++) {
			char temp = arr1[i];
			arr1[i] = arr1[n-i-1];
			arr1[n-i-1] = temp;
		}

		// search
		int cnt = 0;
		int p1 = 0;
		int p2 = 0;

		while (p1 < n && p2 < n) {

			for (int i=p2; i<n; i++) {

				if (arr1[p1] == arr2[i]) {
					cnt ++;
					p2 ++;
					break;
				}
			}
			p1 ++;
		}

		return cnt;
	}

}