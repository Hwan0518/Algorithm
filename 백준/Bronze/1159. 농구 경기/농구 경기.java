import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * condition
 * - 성의 첫 글자가 같은 선수 5명을 선발
 *
 * answer
 * - 가능한 성의 첫 글자를 사전순으로 공백없이 출력
 * - 5명보다 적다면 PREDAJA 출력
 *
 * approach
 * - 입력받은 이름의 첫 번째 값-97('a')의 cnt 증가
 * - 모든 입력이 끝난 후 cnt가 5 이상인 값들을 sb에 추가 후 출력
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 갯수 확인
		char[] result = new char[26];
		for (int i=0; i<n; i++) {
			char first = br.readLine().charAt(0);
			result[first-'a'] +=1;
		}
		// 5 이상인지 확인
		StringBuilder answer = new StringBuilder();
		for (int i=0; i<26; i++) {
			if (result[i] >=5) {
				answer.append(Character.toString(i + 'a'));
			}
		}
		// 출력
		System.out.println(answer.length() == 0 ? "PREDAJA" : answer);
	}

}