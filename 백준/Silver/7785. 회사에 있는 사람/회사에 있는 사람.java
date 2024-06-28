import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<String> working = new TreeSet<>();
        // 출퇴근 기록
        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++){
            String[] inputs = br.readLine().split(" ");
            if (inputs[1].equals("enter")) {
                working.add(inputs[0]);
            } else {
                working.remove(inputs[0]);
            }
        }
        // 출력
        for (String name: working.descendingSet()){
            System.out.println(name);
        }
    }
}