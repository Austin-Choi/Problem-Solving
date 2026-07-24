import java.util.*;
import java.io.*;

/*
GHHHGGHH
GGGGGGHG
-> 처음으로 다른 값을 가지는 구간의 마지막 부분이 다음 flip 위치
1 HGGGGGHH
2 GGGGGGHH
3 HHHHHHGG
4 GGGGGGGG
5 HHHHHHHG
6 GGGGGGHG

아이디어는 맞는데 이거 N^2라 터짐..

짝수번 뒤집힌경우는 원래 문자열 그대로고 홀수번 뒤집힌 경우는 뒤집혀 있을거임
누르는 곳을 또 누른다는 조건은 각 위치를 최대 한번만 누르는 최적해가 존재한다는 말이기도 함
*/

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int flips = 0;
        int ans = 0;
        // 실제로 뒤집진 않고 현재 flips 홀짝성으로 
        // 지금의 상태 판단
        // 오 -> 왼
        for(int i =N-1; i>=0; i--){
            char cur = A[i];
            if((flips & 1) != 0){
                cur = (cur == 'H') ? 'G' : 'H';
            }

            if(cur != B[i]){
                flips++;
                ans++;
            }
        }
        
        System.out.print(ans);
    }
}