/*
답의 세 주사위를 굴려서 같은 면을 체크하면 모든 가능한 것들이
입력으로 주어짐
구해야하는건 주사위 구성 char들
-> bt 상태 : 각 문자가 세 주사위중 어느 하나에 배정되었는가
-> depth 단어 제약 만족가능?
m-coloring
 */
import java.util.*;
import java.io.*;
public class Main {
    static char[][] output;
    static int n;
    static boolean found = false;
    static int[] assign = new int[26];
    static int[] cnt = new int[3];
    static char[][] arr;
    static void assignnew(int idx, ArrayList<Integer> unknown, ArrayList<Integer> free, int depth){
        if(found)
            return;

        if(idx == unknown.size()){
            bt(depth+1);
            return;
        }

        int ch = unknown.get(idx);
        for(int i = 0; i<free.size(); i++){
            int face = free.get(i);
            // 면 다 채워지면 못씀
            if(cnt[face] == 6)
                continue;

            assign[ch] = face;
            cnt[face]++;

            int removed = free.remove(i);
            assignnew(idx+1, unknown, free, depth);
            free.add(i,removed);

            cnt[face]--;
            assign[ch] = -1;
        }
    }
    static void bt(int depth){
        if(found)
            return;

        if(depth == n){
            found = true;
            for(int i = 0; i<3; i++){
                Arrays.fill(output[i], '?');
            }

            // 출력에서 arr[d][i] d<n하면 output에서 6 못넘어가서 터짐
            int[] pos = new int[3];
            for(int ch = 0; ch < 26; ch++){
                if(assign[ch] != -1){
                    int face = assign[ch];
                    output[face][pos[face]++] = (char) ('a'+ch);
                }
            }
            return;
        }

        char[] word = arr[depth];

        if(word[0] == word[1] || word[1] == word[2] || word[0] == word[2])
            return;

        boolean[] used = new boolean[3];
        Set<Integer> s = new HashSet<>();

        for(int i = 0; i<3; i++){
            int idx = word[i] - 'a';
            if(assign[idx] != -1){
                if(used[assign[idx]])
                    return;
                used[assign[idx]] = true;
            }
            else
                s.add(idx);
        }

        ArrayList<Integer> unknown = new ArrayList<>(s);
        ArrayList<Integer> free = new ArrayList<>();
        for(int i = 0; i<3; i++){
            if(!used[i])
                free.add(i);
        }

        if(unknown.size()>free.size())
            return;

        assignnew(0, unknown, free, depth);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][3];
        output = new char[3][6];
        for(int i = 0; i<n; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<3; j++){
                arr[i][j] = temp[j];
            }
        }

        Arrays.fill(assign, -1);
        bt(0);

        if(!found){
            System.out.print(0);
            return;
        }

        boolean[] usedChar = new boolean[26];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 6; j++)
                if (output[i][j] != '?')
                    usedChar[output[i][j] - 'a'] = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (output[i][j] == '?') {
                    for (int c = 0; c < 26; c++) {
                        if (!usedChar[c]) {
                            output[i][j] = (char) ('a' + c);
                            usedChar[c] = true;
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(
                new String(output[0]) + " " +
                        new String(output[1]) + " " +
                        new String(output[2])
        );
    }
}
