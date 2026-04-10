
import java.util.*;
import java.io.*;
/*
카테고리 소속 단어들 주어지면 그걸로 아래꺼 채움
map<String, String> = <키워드, 소속 카테고리>
-> word 쭉 읽어가면서 나오는 단어가 m.get 해서 null이 아닌 경우만 그 결과(카테고리)로 해서 아래꺼 채우기
map<String, Integer> = <카테고리, 점수>
-> 이거 그냥 Iterator 구해서 한번 돌고 최대 점수 구하고 values 에서 해당 점수인 카테고리 sb에 붙여서 출력
 */
public class Main {
    static HashMap<String, ArrayList<String>> cate = new HashMap<>();
    static HashMap<String, Integer> score = new HashMap<>();
    static ArrayList<String> categories = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String c = st.nextToken();
            categories.add(c);
            score.put(c, 0);

            int M = Integer.parseInt(st.nextToken());
            // 단어 하나가 여러 카테고리 가능
            for(int j = 0;  j<M; j++){
                String word = st.nextToken();
                cate.putIfAbsent(word, new ArrayList<>());
                cate.get(word).add(c);
            }
        }
        String input;
        while((input = br.readLine()) != null && !input.isEmpty()){
            StringTokenizer st = new StringTokenizer(input);
            while(st.hasMoreTokens()){
                String next = st.nextToken();
                if(!cate.containsKey(next))
                    continue;
                for(String ct : cate.get(next)){
                    score.put(ct, score.get(ct)+1);
                }
            }
        }

        int maxScore = 0;
        for(String k : score.keySet()){
            maxScore = Math.max(maxScore, score.get(k));
        }

        Collections.sort(categories);
        StringBuilder sb = new StringBuilder();
        for(String c : categories){
            if(score.get(c) == maxScore)
                sb.append(c).append("\n");
        }
        System.out.print(sb);
    }
}