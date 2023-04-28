/*
Condition
    - TL :
      ML :

Question
    -

Access
    -

*/

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 테스트 케이스
        int c = Integer.parseInt(br.readLine());
        // 테스트 시작
        for (int i = 0; i < c; i++) {
            String input = br.readLine();
            // 성적 List : <학생 수 + 학생들 성적>
            LinkedList<Integer> score = Arrays.stream(input.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(LinkedList::new));
            // 학생 수 = score의 첫 번째 값
            int size = score.get(0);
            score.remove(0);
            // 총점 : score 모든 원소의 합 - score 첫 번째 값
            int total = score.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            // 평균 : 총점 / 학생 수
            double average = (double) total / (double) size;
            // 평균이 넘는 학생 수
            long count = score.stream()
                    .filter(x -> x > average)
                    .count();
            // 비율 : 평균이 넘는 학생 수 / 학생 수
            double ratio = (double) count / (double) size *100;
            sb.append(String.format("%.3f",ratio) + "%\n");
        }


        // 결과 출력
        System.out.println(sb);
        br.close();
    }
}
