import java.util.*;
import java.io.*;

public class Main {
	private static int validatePalindrome(int left, int right, String[] word, int count) {
		while(left<right) {
			if(word[left].equals(word[right])) {
				left++;
				right--;
			}
			else {
				if(count==0) {
					if(validatePalindrome(left+1, right, word, 1) == 0
							|| validatePalindrome(left, right-1, word, 1)== 0)
						return 1;
					return 2;
				}
				else
					return 2;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		// left 끝 right 끝 설정해두고 
		// 둘이 같아지거나 right가 left보다 작아질때까지
		// left++, right--
		// 문자 제거한 수 세는 count
		//
		// left 문자와 right 문자 비교해서
		// 같으면 left++, right--
		// 다르면 left+1 이랑 right 비교하기 -> 같으면 left++ 해주고 count++ 다르면 count+=2 하고 break;
		// 이 아니라
		// 다르면 left+1 이랑 right 비교하기 -> 같으면 left++ 해주고 count++ 다르면 left+=2, count++;
		// 다르면 left랑 right-1 비교하기 -> 같으면 right-- 해주고 count++ 다르면 right+=2, count++;
		// count 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		int count = 0;
		for(int t = 0; t<T; t++) {
			String[] word = br.readLine().split("");
			int left = 0;
			int right = word.length-1;
			count = 0;
			
			count = validatePalindrome(left, right, word, count);

			System.out.println(count); 
		}
	}

}