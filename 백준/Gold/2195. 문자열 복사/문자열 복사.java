/*
30분
S의 s-1번 문자부터 p개의 문자를 P에 복사해서 붙이기
P의 뒤에만 붙으니까 P 인덱스 따라가면서 보기

P의 접두사 중에서 S에 존재하는 가장 긴 접두사 찾기
->
P안에서 시작점을 i로 두고 길이를 0부터 계속 늘려가면서
substring을 한 뒤
그 substring이 S에 존재하면 다음 것을 확인하고
존재하지 않으면 빠져나와서
그 substring의 길이만큼 i를 증가시키고 copy 횟수 1 누적
 */

import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String P = br.readLine();
        int ans = 0;

        int i = 0;
        while(i < P.length()){
            int maxLen = 0;
            for(int len = 0; i+len<=P.length(); len++){
                String tmp = P.substring(i, i+len);
                if(S.contains(tmp)){
                    maxLen = len;
                }
                else
                    break;
            }

            i += maxLen;
            ans++;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();

    }
}
