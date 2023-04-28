/*
Condition
    - TL :
      ML :

Question
    -

Access
    - 연속확인, 중복확인, 방문체크 >> 순서를 따라서 해결한다

*/

import java.io.*;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 입력 문자열 개수
        int n = Integer.parseInt(br.readLine());
        // 문자열을 하나씩 확인
        int groupCnt = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            String string = br.readLine();
            int size = string.length();
            // 중복을 체크할 집합
            HashSet<Character> visited = new HashSet<>();
            // 1.연속을 확인하고 2.중복을 확인한 후 3.visited에 대입한다
            char before = string.charAt(0);
            visited.add(before);
            for (int j = 1; j < size; j++) {
                char current = string.charAt(j);
                // 1.연속 확인 : 연속이라면 계속해서 진행
                if (current == before) {
                    continue;
                // 2.중복 확인 : 연속이 아니라면, 중복을 확인. 중복이라면 flag = false 이후 break
                } else if (visited.contains(current)) {
                    flag = false;
                    break;
                // 3.방문 체크 : 중복이 아니라면 방문체크하고 계속진행
                } else {
                    visited.add(current);
                }
                // before을 갱신
                before = current;
            }
            // flag == true라면 group단어이다
            if (flag) {
                groupCnt += 1;
            }
        }
        // group단어의 개수를 확인
        sb.append(groupCnt);
        // 결과 출력
        System.out.println(sb);
        br.close();
    }
}