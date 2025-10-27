/*
색과 닉네임 둘 다 길이별 롤링 해시로 저장하고
substring 없음 : rangeHash로 O(1)에 계산
색 닉 길이별 HashSet<H>로 조회 : 후보 축소 + 빠른 비교
이중 해시 : 서로 다른 base와 mod -> 충돌 위험 최소화
분할은 i<L-1만 검사
 */
import java.io.*;
import java.util.*;
public class Main {
    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 1_000_000_009L;
    static final long BASE1 = 911_382_323L;   
    static final long BASE2 = 972_663_749L;   

    static class H {
        long a, b;
        H(long a, long b){ 
            this.a = a; 
            this.b = b; 
        }
        
        @Override 
        public boolean equals(Object o){
            if (this == o) 
                return true;
            if (!(o instanceof H)) 
                return false;
            H x = (H) o; 
            return a == x.a && b == x.b;
        }
        
        @Override 
        public int hashCode(){
            long v = a * 1_000_003L + b;
            return (int)(v ^ (v >>> 32));
        }
    }

    static Map<Integer, HashSet<H>> colorByLen = new HashMap<>();
    static Map<Integer, HashSet<H>> nickByLen  = new HashMap<>();

    static long[] pref1, pref2, pow1, pow2;

    static void buildHash(char[] s){
        int n = s.length;
        pref1 = new long[n+1]; pref2 = new long[n+1];
        pow1  = new long[n+1]; pow2  = new long[n+1];
        pow1[0] = 1; pow2[0] = 1;
        for (int i = 0; i < n; i++){
            pref1[i+1] = (pref1[i] * BASE1 + s[i]) % MOD1;
            pref2[i+1] = (pref2[i] * BASE2 + s[i]) % MOD2;
            pow1[i+1]  = (pow1[i] * BASE1) % MOD1;
            pow2[i+1]  = (pow2[i] * BASE2) % MOD2;
        }
    }
    static H rangeHash(int l, int r){
        long A = (pref1[r+1] - (pref1[l] * pow1[r-l+1]) % MOD1 + MOD1) % MOD1;
        long B = (pref2[r+1] - (pref2[l] * pow2[r-l+1]) % MOD2 + MOD2) % MOD2;
        return new H(A, B);
    }
    static H hashOf(char[] s){
        long A = 0, B = 0;
        for (char c : s){
            A = (A * BASE1 + c) % MOD1;
            B = (B * BASE2 + c) % MOD2;
        }
        return new H(A, B);
    }
    static void put(Map<Integer, HashSet<H>> mp, char[] s){
        int len = s.length;
        mp.computeIfAbsent(len, k -> new HashSet<>()).add(hashOf(s));
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        while(C-->0){
            String s = br.readLine().toLowerCase();
            put(colorByLen, s.toCharArray());
        }
        while(N-->0){
            String s = br.readLine().toLowerCase();
            put(nickByLen, s.toCharArray());
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        
        while (Q-->0){
            String t = br.readLine().toLowerCase();
            char[] s = t.toCharArray();
            int L = s.length;
            
            if (L < 2){
                out.append("No\n");
                continue;
            }

            buildHash(s);

            boolean ok = false;
            for (int i = 0; i < L - 1; i++){
                int lenC = i + 1, lenN = L - (i + 1);

                HashSet<H> cs = colorByLen.get(lenC);
                if (cs == null)
                    continue;
                H hc = rangeHash(0, i);
                if (!cs.contains(hc))
                    continue;

                HashSet<H> ns = nickByLen.get(lenN);
                if (ns == null)
                    continue;
                H hn = rangeHash(i + 1, L - 1);
                if (ns.contains(hn)) {
                    ok = true;
                    break;
                }
            }
            out.append(ok ? "Yes\n" : "No\n");
        }
        System.out.print(out);
    }
}
