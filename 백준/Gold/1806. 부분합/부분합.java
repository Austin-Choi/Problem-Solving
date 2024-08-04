import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// 투포인터 활용
		// 합이 s보다 작을때 right를 증가시키고 
		// 합이 s보다 클때 left를 증가
		// 길이는 right - left + 1
		// 가장 짧은 길이로 유지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		List<Integer> l = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			l.add(Integer.parseInt(st.nextToken()));
		}
		l.add(0); //패딩
		
		int left = 0;
		int right = 0;
		int total = 0;
		
		int answer = Integer.MAX_VALUE;
		int length = 0;
		// 값이 딱 나오는 부분합을 구하는게 아니라 
		// 다 돌려서 최소길이 부분합을 구해야됨
		while(right <= N) {
			//기준값보다 작으면 요소를 추가함
			if(total<S) {
				total += l.get(right++);
			}
			// 현재 total의 값이 S보다 작은 것도 아니고 
			// S보다 큰데 요소를 제거하게 되면 같은 상태가 됨
			// 그렇다고 같은 상태를 따로 if문으로 빼버리면 무한루프 발생함
			// 그래서 요소를 제거해야하는 상태에서
			// 요소를 제거하고 (여기서 left 값에 변화 생기면서 while문 계속 돌아감)
			// 현재 부분합의 길이를 갱신함 
			if(total>=S) {
				total -= l.get(left++);
				length = right - left + 1;
				//최소길이 갱신
				if(length < answer)
					answer = length;
			}
		}
		if(answer == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(answer);
	}

}