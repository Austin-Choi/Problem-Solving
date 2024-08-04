import java.util.*;
import java.io.*;


public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer> a = new ArrayList<>();
		List<Integer> b = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			b.add(Integer.parseInt(st.nextToken()));
		}
		
		int p1 = 0;
		int p2 = 0;
		
		StringBuilder sb = new StringBuilder();
		while(p1 <N && p2 < M) {
			if(a.get(p1)>b.get(p2))
				sb.append(b.get(p2++)+" ");
			else
				sb.append(a.get(p1++)+" ");
		}
		//N, M 서로 값 달라서 아직 못 넣어준 값이 있는 경우 
		//p2 남아있음
		if(p1==N) 
			for(int i = p2; i<M; i++) {
				sb.append(b.get(i)+" ");
			}
		
		//p1 남아있음
		if(p2==M)
			for(int i = p1; i<N; i++) {
				sb.append(a.get(i)+" ");
			}
		
		System.out.println(sb.toString());
	}

}