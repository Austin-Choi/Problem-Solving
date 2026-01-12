import java.io.*;
public class Main {
    static final long r = 31;
    static final long MOD = 1234567891L;
    static long[] exptable = new long[51];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        exptable[0] = 1;
        for(int i = 1; i<=50; i++){
            exptable[i] = (exptable[i-1]*r) % MOD;
        }
        int L = Integer.parseInt(br.readLine());
        char[] temp = br.readLine().toCharArray();
        long hash = 0;
        for(int i = 0; i<L; i++){
            hash += ((temp[i]-'a'+1)*exptable[i])%MOD;
        }
        System.out.print(hash % MOD);
    }
}
