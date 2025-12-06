/*
재귀로
가능한 것들 현재 type이 뭔지 나열하고 하나 고르고 숫자 증가시키고
더이상 고를게 없으면 끝내서 답 max로 갱신하기

맨 처음에 ff,fs 갯수 받아서 둘중 한 종류라도 1개 이상이면 startF = true로
startF면 0개 이상인 ff, fs로만 시작
!startF면 0개 이상인 아무거나로 시작

0 -> 0,1
1 -> 2,3
2- -> 0,1
3 -> 2,3
--
1000^4라서 재귀는 터짐
sf fs sf fs sf fs .. 가 다른 종류 안쓰고 제일 많이 수록함
->>>> fs- ss-ss-ss-ss... - sf가 제일 김
만약 ff!=0 && fs!=0 -> ff + fs-sf 패턴쓰고(fs-1개) + fs 하나 + ss
fs=0 && ff>0 -> ff
fs>0 && ff=0 -> fs-sf 패턴 쓰고(fs-1개) + fs 하나 + ss
fs=0 && ff=0 -> ss + 1
 */
import java.util.*;
import java.io.*;
public class Main {
    static long[] s = new long[4];
    static long ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<4; i++){
            s[i] = Long.parseLong(st.nextToken());
        }

        // ff, fs, sf, ss
        // ff>0 && fs>0
        // 버그 -> Math.min(s[1]-1,s[2])+1
        // fs sf ss
        // -> fs가 sf보다 적으면 뒤에 +1 불가능
        if(s[0]>0 && s[1]>0){
            ans = s[0];
            // ff-> fs-sf + fs -> ss
            if(s[1]>s[2])
                ans += 2*s[2] + 1 + s[3];
            // ff -> fs-sf
            // ff -> fs-sf -1(sf제거하고) + ss
            // 해결!!! -> fs-ss-sf 가 더 길게 가능
            if(s[1]<=s[2])
                ans += 2*s[1] + s[3];
        }
        // ff>0 && fs=0
        else if(s[0]>0 && s[1]==0)
            ans = s[0];

        // ff=0 && fs>0
        else if(s[0]==0 && s[1]>0){
            if(s[1]>s[2])
                ans += 2*s[2] + 1 + s[3];
            if(s[1]<=s[2])
                ans += 2*s[1] + s[3];
        }

        else if(s[0]==0 && s[1]==0)
            ans = s[3] + Math.min(s[2],1);

        System.out.println(ans);
    }
}
