/*
1000^4라서 재귀는 터짐
sf fs sf fs sf fs .. 가 다른 종류 안쓰고 제일 많이 수록함
->>>> fs- ss-ss-ss-ss... - sf가 제일 김
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

        // ff=0 && fs=0
        if(s[0]==0 && s[1]==0)
            ans = s[3] + Math.min(s[2],1);
        // ff>0 && fs=0
        else if(s[0]>0 && s[1]==0)
            ans = s[0];
        // ff, fs, sf, ss
        // ff>0 && fs>0
        // 버그 -> Math.min(s[1]-1,s[2])+1
        // fs sf ss
        // -> fs가 sf보다 적으면 뒤에 +1 불가능
        // -> if(s[0]==0 && s[1]>0) OR if(s[0]>0 && s[1]>0)
        else {
            ans = s[0];
            // ff-> fs-sf + fs -> ss
            if(s[1]>s[2])
                ans += 2*s[2] + 1 + s[3];
            // ff -> fs-sf
            // ff -> fs-sf -1(sf제거하고) + ss
            // 해결!!! -> fs-ss-sf 가 더 길게 가능
            //if(s[1]<=s[2])
            else
                ans += 2*s[1] + s[3];
        }

        System.out.println(ans);
    }
}
