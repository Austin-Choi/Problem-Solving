import java.util.*;
import java.io.*;

public class Main {
	public static int N = 0;
	public static int M = 0;
	public static StringBuilder answer = new StringBuilder();
	public static int[] ans = null;
	
	//백트래킹-> 주어진 길이 한계 있고, 모든 경우의 수를 찾는 것이므로 dfs 활용
	// bfs도 모든 경우의 수를 찾을수 있긴 한데 공간복잡도가 올라가고 최단 거리 찾을때 queue 활용해서 많이 씀 
	public static void dfs(int[] l, boolean[] check, int length) {
		if(length == M) {
			for(int n : ans) {
				answer.append(n+" ");
			}
			answer.append("\n");
			return;
		}
		for(int i = 0; i<N; i++) {
			if(!check[i]) {
				check[i] = true;
				//첨에는 여기서 이렇게 출력함
				//answer.append(l[i]+" ");
				// 근데 이렇게 하면 맨 앞은 안나옴.
				// -> ans배열 만들어서 조건 도달하면 한번에 출력하게 바꿈
				// 각 단계(length)마다 방문하는 값 적어주고 length M되면 string으로 빼주고 리턴
				ans[length]= l[i];
				dfs(l, check, length+1);
				check[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] l = new int[N];
		boolean[] check = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			l[i] = Integer.parseInt(st.nextToken());
		}
		//출력보니까 정렬된 상태임
		Arrays.sort(l);
		ans = new int[M];
		dfs(l,check,0);
		
		System.out.println(answer.toString());
	}

}
