import java.io.*;

/**
 * condition
 * - 별4>동그라미3>네모2>세모1 => 무승부
 * approach
 * - 구현
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n
		int n = Integer.parseInt(br.readLine());
		// n-round search
		for (int i=0; i<n; i++) {
			String[] a = br.readLine().split(" ");
			String[] b = br.readLine().split(" ");
			int[] aCard = getCard(a);
			int[] bCard = getCard(b);
			calcWinner(aCard, bCard);
		}
	}
		
	public static int[] getCard(String[] input) {
		int size = input.length;
		int[] card = new int[5];
		for (int i=1; i<size; i++) {
			int c = Integer.parseInt(input[i]);
			card[c] ++;
		}
		return card;
	}
	
	public static void calcWinner(int[] aCard, int[] bCard) {
		for (int i=4; i>0; i--) {
			int a = aCard[i];
			int b = bCard[i];
			if (i==1 && a==b) {
				System.out.println("D");
			} else if (a!=b){
				System.out.println(a>b ? "A" : "B");
				break;
			}
		}
	}
	
}