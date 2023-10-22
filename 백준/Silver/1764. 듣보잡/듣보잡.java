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

import static java.lang.Math.max;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // n,m 입력
        int[] inputNM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputNM[0];
        int m = inputNM[1];

        // 사람들 이름을 저장할 해쉬
        HashMap<String, Boolean> people = new HashMap<>();
        // 듣도 못한 사람
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            people.put(name, false);
        }

        // 보도 못한 사람
        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            if (people.containsKey(name)) {
                people.put(name, true);
            }
        }

        // 듣도 보도 못한 사람을 찾음
        ArrayList<String> notPeople = new ArrayList<>();
        for (String name : people.keySet()) {
            if (people.get(name)==true) {
                notPeople.add(name);
            }
        }

        // 사전순 정렬
        Collections.sort(notPeople);
        // 사전순 출력
        System.out.println(notPeople.size());
        for (String name : notPeople) {
            System.out.println(name);
        }
    }
}


