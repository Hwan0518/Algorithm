import java.io.*;
import java.util.*;

/**
 * 
 */

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static String[] sentence1 = new String[] {
			"\"재귀함수가 뭔가요?\"\n",
			"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
			"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n"
			};
	static String sentence2 = "라고 답변하였지.\n";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		int n = Integer.parseInt(br.readLine());
		// print
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		for (int i=0; i<n; i++) {
			for (int j=0; j<4; j++) {
				add(i);
				sb.append(sentence1[j]);
			}
		}
		addAns(n);
		for (int i=n; i>=0; i--) {
			add(i);
			sb.append(sentence2);
		}
		System.out.print(sb);
	}
	
	
	public static void add(int i) {
		for (int j=0; j<i; j++) {
			sb.append("____");
		}
	}
	
	
	public static void addAns(int n) {
		add(n);
		sb.append("\"재귀함수가 뭔가요?\"\n");
		add(n);
		sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
	}
		
}