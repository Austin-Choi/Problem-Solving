import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// N과M7 에서 방문체크만 빼면됨 (중복허용한뎄으니까!)
public class Main {
	public static int N = 0;
	public static int M = 0;
	public static StringBuilder answer = new StringBuilder();
	public static int[] ans = null;

	public static void dfs(int[] l, int length) {
		if(length == M) {
			for(int n : ans) {
				answer.append(n+" ");
			}
			answer.append("\n");
			return;
		}
		for(int i = 0; i<N; i++) {
			ans[length]= l[i];
			dfs(l, length+1);
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] l = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			l[i] = Integer.parseInt(st.nextToken());
		}
		//출력보니까 정렬된 상태임
		Arrays.sort(l);
		ans = new int[M];
		dfs(l,0);
		
		System.out.println(answer.toString());
	}

}
