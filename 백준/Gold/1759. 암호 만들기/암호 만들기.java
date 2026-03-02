/*
정렬해서 L개 될때까지 뽑고
L개 되면 모음한개, 자음2개 이상인지 검사하기..

L,C 크면 미리 pruning 해야함.
문제 조건은 매우 널널하긴함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int L,C;
    static char[] pool;
    static char[] output;
    static Set<Character> s = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    // vcnt = 모음수, nvcnt = 자음수
    static void dfs(int depth, int start, int vcnt, int nvcnt){
        // 남은 자리를 자음 2개 조건 만족못하면 볼필요없음. prune
        if(nvcnt + (L-depth) < 2)
            return;

        // 모음이 0개인데 나머지 뽑을 것 중에 모음이 없으면 prune
        if(vcnt == 0){
            boolean hasVowel = false;
            for(int i = start; i<C; i++){
                if(s.contains(pool[i])){
                    hasVowel = true;
                    break;
                }
            }
            if(!hasVowel)
                return;
        }

        // 출력 : pruning 했더라도 조건 걸어야됨
        if(depth == L){
            if(vcnt >=1 && nvcnt >= 2)
                sb.append(output).append("\n");
            return;
        }

        // 중복안되게 사전순 오름차순(정렬됨)
        for(int i = start; i<C; i++){
            char c = pool[i];
            output[depth] = c;
            if(s.contains(c))
                dfs(depth+1, i+1,vcnt+1, nvcnt);
            else
                dfs(depth+1,i+1,vcnt,nvcnt+1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        output = new char[L];
        s.add('a');
        s.add('e');
        s.add('i');
        s.add('o');
        s.add('u');
        pool = br.readLine().replaceAll("\\s", "").toCharArray();
        Arrays.sort(pool);
        dfs(0,0,0,0);
        System.out.print(sb);
    }
}