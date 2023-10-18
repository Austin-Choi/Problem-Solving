import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0;  tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] arr = new int[N]; 
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int price = arr[N-1];
			int cnt = 0;
			long sum = 0;
			for(int i = N-2; i >= 0; i--) {
				if(price > arr[i]) {
					cnt++;
					sum-=arr[i];
				}else {
					sum += price*cnt;
					price = arr[i];
					cnt = 0;
				}
			}
			if(cnt != 0) sum += price *cnt;
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}