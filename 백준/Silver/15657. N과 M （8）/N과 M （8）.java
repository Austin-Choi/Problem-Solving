import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N = 0;
	public static int M = 0;
	public static StringBuilder answer = new StringBuilder();
	public static int[] ans = null;

	public static void dfs(int[] l, int pos, int length) {
		if(length == M) {
			for(int n : ans) {
				answer.append(n+" ");
			}
			answer.append("\n");
			return;
		}
		// 중복있으니까 중복체크 빼고
		// 비내림차순 ㅋㅋ 오름차순으로 보려면 리스트 이미 정렬되있으니
		// pos하나 생성해서
		// 재귀 들어갈때마다 증가시켜서 리스트 처음꺼부터 뽑지 못하게 하면됨
		for(int i = pos; i<N; i++) {
			ans[length]= l[i];
			dfs(l, i, length+1);
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
		dfs(l,0,0);
		
		System.out.println(answer.toString());
	}
}
