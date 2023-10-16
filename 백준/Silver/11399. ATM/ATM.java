import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int answer = 0;
		int temp = 0;
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); 
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(); 
		
		for(int i = 0; i<N; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		pq2 = pq;
		while(!pq.isEmpty()) {
			temp += pq.poll();
			answer += temp;
		}
		System.out.println(answer);
	}

}