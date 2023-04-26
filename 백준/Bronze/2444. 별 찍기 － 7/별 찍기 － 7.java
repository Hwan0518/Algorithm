/*
Condition
    - TL :
      ML :

Question
    -

Access
    - 규칙
        line>cnt
        1 > 1
        2 > 3
        3 > 5
        4 > 7
        5 > 9 : 2*line - 1

    - 2n-1번 반복
        line = 1부터 시작, 1씩 증가
        cnt = 2*line-1

    - cnt == 2n-1이라면 > line은 1씩 감소
      cnt == 1이라면 > line은 1씩 증가

*/

import java.io.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // n
        int n = Integer.parseInt(br.readLine());
        // 2*n-1번 반복
        int max = 2*n-1;
        int line = 1;
        int change = 1;
        for (int i = 0; i < max; i++) {
            int cnt = 2*line-1;
            int blank = (max - cnt) / 2;
            String star = "*".repeat(cnt);
            if (blank != 0) {
                sb.append(String.format("%" + blank + "s", " ") + star + "\n");
            }else {
                sb.append(star + "\n");
            }
            if(cnt==max){
                change = -1;
            }else if(cnt==1){
                change = 1;
            }
            line += change;
        }

        System.out.println(sb);
        br.close();
    }
}