import java.io.*;
import java.util.*;

/*
Conditions
- 암호화
	1. 단어 선택
	2. 단어에서 알파벳 중복되지 않도록 선택하여 단어 키를 만듦 (중복되지 않게  첫 번째만 사용)
	3. 암호화 map 생성(key는 평문 알파벳 순서대로, value는 단어 키를 순서대로)
		- 이때 value에 단어 키를 모두 사용했다면, 그때부터는 사용되지 않은 알파벳을 순서대로 사용
- 암호화 단어 W와 문장 S가 주어질 때, 암호화한 결과를 출력

Approach
- 완탐
	-> A-Z까지 한번만 탐색을 진행하여 암호화 표를 만들자
	-> 중복처리는 visited로 처리하면 될듯
	-> visited = new int[26]
	-> 'A'가 ascii 65니까, visit['알파벳'-65] 로 하면 됨
	-> 중복처리 하면서 hashMap<String,String> 같이 채워야함
	-> 이후 visited 한바꾸 더 돌리면서 안쓴거 다 채우기 ㄱㄱ
	-> 평문idx와 암호idx를 나눠서 돌리자

 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		char[] w = br.readLine().toCharArray();
		char[] s = br.readLine().toCharArray();

		// search
		boolean[] visited = new boolean[26];
		HashMap<Character, Character> table = new HashMap<>();

		int plainIdx = 0;
		for (char secret : w) {

			char key = (char) (65 + plainIdx);
			int secretIdx = secret - 65;

			if (visited[secretIdx]) continue;

			visited[secretIdx] = true;
			table.put(key, secret);
			plainIdx++;
		}

		for (int secretIdx =0; secretIdx <26; secretIdx++) {

			if (visited[secretIdx]) continue;

			char key = (char) (65+ plainIdx);
			char secret = (char) (65+ secretIdx);

			visited[secretIdx] = true;
			table.put(key, secret);
			plainIdx++;

		}

		// result
		StringBuilder sb = new StringBuilder();
		for (char c : s) sb.append(table.get(c));
		System.out.print(sb);
	}

}
