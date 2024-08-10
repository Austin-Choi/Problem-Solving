import java.util.*;
import java.io.*;

public class Main {
	public static int N = 0;
	public static int M = 0;
	public static StringBuilder answer = new StringBuilder();
	public static int[] ans = null;
	public static int[] l;
	public static boolean[] check;
	
	public static void dfs(int pos, int length) {
		if(length == M) {
			for(int n : ans) {
				answer.append(n);
				answer.append(" ");
			}
			answer.append("\n");
			return;
		}
		//여기서 i를 재귀하면서 같이 가지고 가면
		/*
		 * 1 7
		 * 1 8
		 * 1 9 
		 * 다음에 
		 * 7 1
		 * 안나오고
		 * 7 8 나옴
		 * 
		 * 따라서 pos 파라미터 하나 생성해줌
		 */
		for(int i = pos; i<N; i++) {
			if(!check[i]) {
				check[i] = true;
				ans[length]= l[i];
				//pos 파라미터 값을 재귀할때마다 증가하는 i로 주면
				//이미 탐색한 1 이런거 스킵하고 그담거부터 보니까
				dfs(i, length+1);
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
		
		l = new int[N];
		check = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			l[i] = Integer.parseInt(st.nextToken());
		}
		//출력보니까 정렬된 상태임
		Arrays.sort(l);
		ans = new int[M];
		dfs(0, 0);
		
		System.out.println(answer.toString());
	}

}
