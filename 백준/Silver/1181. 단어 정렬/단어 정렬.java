/*
Condition
    - TL :
      ML :
    - 1<=N,M<=50만
    - 1<=name<=20, 알파벳 소문자만

Question
    - 듣 + 보 에서 겹치는 사람의 명단을 사전순 출력

Access
    - Hash
        : hash에 듣+보 각각 cnt 확인후 1 이상인것 출력 -> N+M+(N,M중첩)


input&output hint
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringBuffer sb = new StringBuffer();
String input = br.readLine();
int val = Integer.parseInt(input);
sout(sb);
br.close();
*/


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<String>> sortedMap = new HashMap<>();
        HashSet<String> duplCheck = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            // 중복 체크
            if (duplCheck.contains(word)) {
                continue;
            }
            duplCheck.add(word);

            // 중복이 아니라면 <key=단어길이, value=단어>인 hashMap을 완성한다
            int wordLength = word.length();
            ArrayList<String> wordList = new ArrayList<>();

            // key=wordLength에 해당하는 value가 없다면 새로운 리스트를 추가
            if (sortedMap.get(wordLength) == null) {
                wordList.add(word);
            }

            // 존재한다면 기존 리스트에 word를 추가하고, 리스트를 정렬
            else {
                wordList = sortedMap.get(wordLength);
                wordList.add(word);
                Collections.sort(wordList);
            }
            sortedMap.put(wordLength, wordList);
        }

        List<Integer> lengthList = sortedMap.keySet().stream().collect(Collectors.toList());
        Collections.sort(lengthList);
        //결과 출력
        for (int length : lengthList) {
            List<String> wordList = sortedMap.get(length);
            for (String word : wordList) {
                System.out.println(word);
            }
        }
    }
}

