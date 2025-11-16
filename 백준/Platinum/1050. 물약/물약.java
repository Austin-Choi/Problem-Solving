/*
일단 map으로 파싱하고 Pattern, Matcher
등장하는 물건들 price[hash(name)] = INF로 초기화하고
시장에 있으면 그가격으로 초기화함

정점 : 물건
간선 : List<Info> 하나
dist[물건] = 물건을 완성하는 최소 거리(cost)

파싱할때 =로 스플릿해주고 \\d[A-Z]+하고
id값 매길때는 null검사하고 put

재귀로 board 타고 들어가서 공식 최소합 갱신하기

반례
1 11
A 1
A=1E
A=1D
B=1A
B=1E
C=1B
C=1A
D=1C
D=1B
E=1D
E=1C
LOVE=1A+1B+1C+1D+1E
5라는데 -1나옴

재귀를
-> update 안될때까지 계속 돌리기로 바꾸기
 */
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Map<String, Integer> parse = new HashMap<>();
    static class Info{
        int item;
        long amount;
        Info(int i, long a){
            this.item = i;
            this.amount = a;
        }
    }
    static class Recipe{
        int result;
        ArrayList<Info> formula;
        Recipe(int r, ArrayList<Info> f){
            this.result = r;
            this.formula = f;
        }
    }
    static ArrayList<ArrayList<Info>>[] board;
    static long[] price;
    static final long INF = (long) 1e18;
    static int N,M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Info> input = new ArrayList<>();
        int id = 0;
        while(N-->0){
            st = new StringTokenizer(br.readLine());
            String item = st.nextToken();
            long cost = Long.parseLong(st.nextToken());
            parse.put(item, id++);
            input.add(new Info(parse.get(item), cost));
        }

        ArrayList<Recipe> bb = new ArrayList<>();
        while(M-->0){
            String l = br.readLine();
            String[] temp = l.split("=");
            String result = temp[0];
            if(parse.get(result) == null)
                parse.put(result, id++);

            Pattern p = Pattern.compile("[\\d][A-Z]+");
            Matcher m = p.matcher(temp[1]);
            // 공식 계수랑 아이템명 뽑기
            ArrayList<Info> r = new ArrayList<>();
            while(m.find()){
                String s = m.group();
                long cnt = (long) s.charAt(0) - '0';
                String name = s.substring(1);
                if(parse.get(name) == null)
                    parse.put(name, id++);
                r.add(new Info(parse.get(name), cnt));
            }
            bb.add(new Recipe(parse.get(result), r));
        }
        // 시장가격, dist 배열 초기화
        price = new long[id];
        Arrays.fill(price, INF);
        for(Info i : input){
            price[i.item] = i.amount;
        }

        board = new ArrayList[id];
        for(int i = 0; i<id; i++){
            board[i] = new ArrayList<>();
        }

        for(Recipe r : bb){
            board[r.result].add(r.formula);
        }

        Integer love = parse.get("LOVE");
        if(love == null){
            System.out.println(-1);
            return;
        }

        boolean u;
        do{
            u = false;

            for(int i=0; i<id; i++){
                if(!board[i].isEmpty()){
                    for(ArrayList<Info> r : board[i]){
                        long sum = 0;
                        boolean isINF = false;

                        for(Info ii : r){
                            int name = ii.item;
                            long c = price[name];
                            if(c == INF){
                                isINF = true;
                                break;
                            }

                            long add = c * ii.amount;
                            // INF는 아닌데 여튼 큰값임
                            if(add > 1000000000L)
                                add = 1000000001L;

                            sum += add;
                            // 문제조건상 큰값은 걍 이걸로 고정해둠
                            if(sum > 1000000001L){
                                sum = 1000000001L;
                                break;
                            }
                        }

                        // cost INF인 재료있으면 스킵함
                        if(isINF)
                            continue;

                        if(sum < price[i]){
                            price[i] = sum;
                            u = true;
                        }
                    }
                }
            }
        }while (u);

        long answer = price[love];

        if(answer == INF)
            System.out.println(-1);
        else if(answer > 1000000000L)
            System.out.println(1000000001L);
        else
            System.out.println(answer);
    }
}
