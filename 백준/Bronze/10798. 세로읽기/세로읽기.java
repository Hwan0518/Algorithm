import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 문자열 구현
 * - 필요한 기능: 입력, 가장 긴 단어 길이 찾기, 모든 단어에 공백 추가하기, 세로로 읽기
 */
public class Main {

	public static void main(String[] args) throws IOException {
		// 단어 리스트
		List<List<String>> wordList = inputData();
		// 가장 긴 단어 길이
		int maxLen = wordList.stream()
			.max((o1, o2) -> o1.size() - o2.size())
			.get()
			.size();
		// 모든 단어에 공백 추가
		wordList = addBlank(wordList, maxLen);
		// 세로로 읽기
		readVertical(wordList, maxLen);
	}

	// 입력
	public static List<List<String>> inputData() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 단어 리스트
		List<List<String>> wordList = new ArrayList<>();
		// 단어 입력
		for (int i=0; i<5; i++) {
			List<String> word = Arrays.asList(br.readLine().split(""));
			wordList.add(word);
		}
		return wordList;
	}

	// 모든 단어에 공백 추가
	public static List<List<String>> addBlank(List<List<String>> wordList, int maxLen) {
		List<List<String>> changedWordList = new ArrayList<>();
		for (List<String> word : wordList) {
			List<String> changedWord = new ArrayList<>(word);
			int insufficient = maxLen - word.size();
			for (int i=0; i<insufficient; i++) {
				changedWord.add(" ");
			}
			changedWordList.add(changedWord);
		}
		return changedWordList;
	}

	// 세로로 읽기
	private static void readVertical(List<List<String>> wordList, int maxLen) {
		List<String> result = new ArrayList<>();
		for (int c=0; c<maxLen; c++) {
			for (int r=0; r<5; r++) {
				String cur = wordList.get(r).get(c);
				if (!cur.equals(" ")) {
					result.add(cur);
				}
			}
		}
		// 결과 출력
		System.out.println(String.join("", result));
	}
}