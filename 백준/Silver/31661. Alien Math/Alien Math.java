/*
B -> B진수
0~B-1 까지를 나타내는 문자열 (a aa aaa 이런 입력은없음)
X -> 문자열 대응해서 10진수로 바꿔서 출력 (max 10^9)

B진수 10^9 이하까지 구해놓고 list로
문자열 hashmap으로 대응해서 X읽기
 */
import java.util.*;
import java.io.*;
public class Main {
    static ArrayList<Integer> powB(int B){
        ArrayList<Integer> rst = new ArrayList<>();
        int n = 1;
        rst.add(1);
        while(n <= 1_000_000_000){
            n *= B;
            rst.add(n);
        }
        return rst;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int B = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<String, Integer> m = new HashMap<>();
        for(int i = 0; i<B; i++){
            m.put(st.nextToken(), i);
        }
        ArrayList<Integer> pow = powB(B);
        String[] s = br.readLine().split("");
        int l = 0;
        int r = 0;
        ArrayList<Integer> rst = new ArrayList<>();
        String temp = s[0];
        while(l<=r && r<s.length){
            if(l!=r){
                temp += s[r];
            }
            if(m.get(temp) != null){
                rst.add(m.get(temp));
                l = r+1;
                r++;
                if(l == s.length)
                    break;
                temp = s[l];
            }
            else{
                r++;
            }
        }
        Collections.reverse(rst);
        int ans = 0;
        for(int i = 0; i<rst.size(); i++){
            ans += rst.get(i) * pow.get(i);
        }
        System.out.print(ans);
    }
}
