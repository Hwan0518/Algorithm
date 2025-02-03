import java.io.*;
import java.util.*;

/**
 * 구현
 * 
 * approach
 * - col별로 같은 수 갯수만큼 count 증가시키면 될거같은데?
 * - map을 사용해서 반별로 학생이 누구누구 있었는지 확인하자
 * - **** 한번이라도 같은 반이었던 '사람'의 수를 카운팅해야한다!!!!!!
 * - 즉, 같은 수 갯수만큼 계속 더해주면 안되고, visited 처리를 해주어야함. set을 활용하자
 */

public class Main {
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		int n = Integer.parseInt(br.readLine());
		int[][] data = new int[n][5];
		for (int c=0; c<n; c++) {
			data[c] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		
		// search
		List<Set<Integer>> students = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
		    students.add(new HashSet<>());
		}
		Map<Integer, List<Integer>> clazz = new HashMap<>();
		for (int r=0; r<5; r++) {
			// initialize
			clazz.clear();
			for (int i=1; i<=9; i++) {
				clazz.put(i, new ArrayList<Integer>());
			}
			// put student to class
			for (int c=0; c<n; c++) {
				int clz = data[c][r];
				List<Integer> val = clazz.get(clz);
				val.add(c+1);
			}
			// counting
			for (int i=1; i<=9; i++) {
				List<Integer> clz = clazz.get(i);
				if (clz.size() == 0) continue;
				for (int num:clz) {
					Set<Integer> friends = students.get(num); 
					clz.forEach(other -> {
						friends.add(other);
					});
				}
			}
		}
		
		// result
		int maxCnt = 0;
		int maxStudent = 1;
		for (int i=1; i<=n; i++) {
			int cnt = students.get(i).size()-1;
			if (cnt > maxCnt) {
				maxCnt = cnt;
				maxStudent = i;
			}
		}
		System.out.print(maxStudent);
	}
		
}