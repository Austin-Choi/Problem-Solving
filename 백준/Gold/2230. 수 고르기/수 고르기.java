import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//M 범위가 int 한계점 정도 됨
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer> l = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			l.add(Integer.parseInt(st.nextToken()));
		}
		//차이 구해야하니까 정렬함
		Collections.sort(l);
		//패딩 : right<N 이고 차이 구하는거라 마지막 숫자 복붙
		l.add(l.get(N-1));
		
		int left = 0;
		int right = 0;
		int sub = 0;
		
		int answer = Integer.MAX_VALUE;
		
		while(left < N && right < N) {
			//M 이상이면서 가장 작은 차이를 구해야 하니까 
			if(sub >= M) {
				if(answer > sub)
					answer = sub;
				sub = l.get(right)-l.get(++left);
				
			}
			// 차이가 M보다 작으면 더 늘려줌
			else if(sub < M) {
				sub = l.get(++right)-l.get(left);	
			}	
		}
		System.out.println(answer);
	}

}
