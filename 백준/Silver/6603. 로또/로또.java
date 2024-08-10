import java.util.*;
import java.io.*;

/*
 * 입력처리 -> 처음 입력받은거 0들어오면 while문 빠져나오기
 * 
 */

public class Main {
	public static StringBuilder sb = new StringBuilder();
	public static int N;
	public static void dfs(int[] list, boolean[] check, int[] ans, int pos, int depth) {
		if(depth == 6) {
			for(int n : ans) {
				sb.append(n);
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		//중복없으니까 중복체크 달아주고
		//오름차순 (사전순으로 출력하랫으니까 i에 재귀할때마다 증가하는 pos달아줌)
		for(int i = pos; i<N; i++) {
			if(!check[i]) {
				check[i] = true;
				ans[depth] = list[i];
				dfs(list, check, ans, i, depth+1);
				check[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0)
				break;
			
			int[] l = new int[N];
			int[] ans = new int[6];
			boolean[] check = new boolean[N];
			for(int i = 0; i<N; i++) {
				l[i] = Integer.parseInt(st.nextToken());
			}
			dfs(l, check, ans, 0, 0);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
