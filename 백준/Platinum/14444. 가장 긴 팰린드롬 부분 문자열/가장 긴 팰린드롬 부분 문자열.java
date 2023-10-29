import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] A;
	public static char[] cArr;
	//O(n)
	//짝수 길이의 팰린드롬을 위해 # 패딩을 넣어줘야 한다.
	//A[i] 선형탐색을 하며 각 요소를 중심으로 갖는 가장 긴 팰린드롬의 반지름이지만
	//패딩 때문에 A[i]는 길이가 됨.
	public static void palindrome(String s) {
		//r은 i-1 단계 까지의 모든 팰린드롬의 끝나는 인덱스 중 가장 큰 값
		int r = 0;
		//c는 i-1 단계 까지의 r의 값이 최대가 되게 하는 중심 인덱스를 저장함.
		int c = 0;
		for(int i = 0; i<s.length(); i++) {
			//i번째 문자가 i미만의 문자를 중심으로 하는 팰린드롬이 아님
			if(r<i)
				A[i] = 0;
			//i번째 문자가 i미만의 문자를 중심으로 하는 팰린드롬의 요소이다.
			//ex. banana에서 i=1일때 a는 anana의 요소이다.
			else
				//1. i를 중심으로 하는 가장 긴 팰린드롬이 
				//j<i인 j를 중심으로 하는 가장 긴 팰린드롬에 완전히 들어가는 경우
				//i와 대칭인 i`를 기준으로 하는 가장 긴 팰린드롬은 항상 대칭임
				//j를 기준으로 A[j]는 대칭이 보장됨. 다시 설명하면,
				//팰의 성질에 의해 c가 중심일때(c<i) 2C-i 부터 i까지만 팰의 대칭성이 보장됨 
				//대칭일 때 A[i] = A[2c-i]는 항상 참
				A[i] = Math.min(A[2*c-i], r-i);
			//하지만 대칭을 넘어서는 부분에서는 저게 항상 참이지 않으므로
			//naive하게 탐색해주며 길이를 늘린다.
			//i+A[2c-i]가 c+A[c] 이상인 경우에는 c+A[c]-i만큼만 회문의 성질이 보장됨
			
			//2. i를 중심으로 하는 가장 긴 팰린드롬이
			//j<i인 j를 중심으로 하는 가장 긴 팰린드롬에 일부만 포함되는 경우
			//A[i]와 A[i`]는 대칭이 아니지만 j를 기준으로 하는 가장 긴 팰린드롬 구간에 포함되는 
			//A[i]와 A[i`]는 대칭이 보장됨
			
			//3. i를 중심으로 하는 가장 긴 팰린드롬이
			//j<i인 j를 중심으로 하는 가장 긴 팰린드롬에 겹치는 경우
			//2와 같이 초기값은 A[i] = (j + A[j]) - i이며, 양 옆으로 1씩 이동하며 비교하면 됨
			while((i-A[i]-1>=0) 
					&& (i+A[i]+1<s.length()) 
					&& (cArr[i-A[i]-1]==cArr[i+A[i]+1]))
				A[i]++;
			
			//i에서 A[i]+1 이상으로 팰린드롬 확장이 불가능한 경우
			if(r<i+A[i]) {
				r = i + A[i];
				c = i;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		cArr = s.toCharArray();
		sb.append("#");
		for(char c : cArr)
		{
			sb.append(c).append("#");
		}
		String ns = sb.toString();
		cArr = ns.toCharArray();
		A = new int[ns.length()];
		palindrome(ns);
		
		int answer=-1;
		for(int i : A) {
			answer = Math.max(answer, i);
		}
		System.out.println(answer);
	}

}