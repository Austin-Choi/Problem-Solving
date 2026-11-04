/*
대문자는 원래 글자 소문자는 추가된 글자
2번만 등장하는 소문자는 그 사이는 무조건 단어임. 사이에는 한가지의 1개 이상의 소문자만 존재하던지 아예 소문자가 없어야함
한번 사용한 소문자 재사용 금지 -> 

1) 일단 소문자별로 등장 횟수를 세기
-> (map, int[] 등장횟수, 처음 등장 위치, 마지막 등장 위치, 마지막 등장 위치와 바로 그전 gap 크기)

2) DFS로 문자열을 앞에서부터 단어 단위로 탐색
-> 소문자를 만나면 등장 횟수와 위치를 이용해 규칙 판별

3) 규칙 1부터 검사, 대문자와 소문자가 번갈아 등장하는지 확인
-> 유효하면 대문자만 추출하고 나머지 문자열 DFS

4) 규칙 1이 실패하면 규칙 2 검사
    4-1) 소문자가 정확히 2번 등장하는지 확인
    4-2) 내부에 규칙 1이 적용되었는지 검사하고 대문자 추출
    -> 유효하면 나머지 문자열 DFS

5) 복원한 단어들을 공백으로 연결
*/
import java.util.*;
class Solution {
    static char[] A;
    static int N;

    // 등장 횟수, 첫 등장 위치, 마지막 등장 위치, 마지막 간격
    static int[][] count;
    // memo[i] = i에서 시작한 복원 문자열 저장
    // 규칙 1과 규칙 2가 같이 적용되면 분기점이 생겨서 중복계산 막는 메모이제이션
    static String[] memo;
    // visited[i] = i를 이미 검사했는지
    static boolean[] visited;

    public String solution(String sentence) {
        A = sentence.toCharArray();
        N = A.length;
        count = new int[26][4];
        // 전처리
        for (int i = 0; i < N; i++) {
            char c = A[i];
            if (Character.isLowerCase(c)) {
                int ci = c - 'a';
                int cc = count[ci][0];
                int first = count[ci][1];
                int last = count[ci][2];
                if (cc == 0)
                    count[ci] = new int[]{1, i, i, 0};
                else
                    count[ci] = new int[]{cc + 1, first, i, i - last};
            }
        }
        memo = new String[N + 1];
        visited = new boolean[N + 1];

        String ans = dfs(0);
        return ans == null ? "invalid" : ans;
    }

    // 규칙1 [start, end] 구간이 AaAaA 형태인지 확인
    // 같은 소문자가 대문자랑 교대로 존재하는지
    static boolean checkRule1(int start, int end, char c) {
        if (start < 0 || end >= N || start >= end)
            return false;

        int ci = c - 'a';
        // AaA 형태이므로 길이는 홀수, 최소 3
        if ((end - start) % 2 != 0 || end - start < 2)
            return false;

        // 이미 쓴 소문자는 다시 쓸수 없다
        // 현재 소문자의 예상 등장 횟수가 소문자 실제 등장횟수와 맞는지 검증
        int needed = (end - start) / 2;
        if (count[ci][0] != needed)
            return false;
        // 첫 등장과 마지막 등장이 현재 단어 범위랑 맞아야함
        if (count[ci][1] != start + 1 || count[ci][2] != end - 1)
            return false;

        for (int i = start; i <= end; i++) {
            if ((i - start) % 2 == 0) {
                if (Character.isLowerCase(A[i]))
                    return false;
            } else {
                if (A[i] != c)
                    return false;
            }
        }

        return true;
    }

    // 규칙2 내부가 대문자만 있거나 규칙1 형태여야 함
    static String checkRule2(int start, int end) {
        if (start > end)
            return null;

        char lower = 0;
        StringBuilder temp = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (Character.isUpperCase(A[i])) 
                temp.append(A[i]);
            else {
                if (lower == 0)
                    lower = A[i];
                else if (lower != A[i])
                    return null;
            }
        }

        // 내부에 규칙 1이 없음
        if (lower == 0)
            return temp.toString();

        // 내부 전체에 규칙 1이 적용되어야 함
        if (!checkRule1(start, end, lower))
            return null;

        return temp.toString();
    }

    // a,b,c 단어를 공백으로 연결하기
    static String join(String a, String b, String c) {
        StringBuilder sb = new StringBuilder();
        for (String s : new String[]{a, b, c}) {
            if (s == null || s.isEmpty())
                continue;
            if (sb.length() > 0)
                sb.append(' ');
            sb.append(s);
        }
        return sb.toString();
    }

    // 현재 위치부터 단어 단위로 파싱
    static String dfs(int idx) {
        if (idx == N)
            return "";

        if (visited[idx])
            return memo[idx];

        visited[idx] = true;

        // 다음 소문자 위치 탐색
        int first = idx;
        while (first < N && Character.isUpperCase(A[first])) 
            first++;

        // 남은 부분이 모두 대문자라면 그대로 사용
        if (first == N) 
            return memo[idx] = new String(A, idx, N - idx);

        char c = A[first];
        int ci = c - 'a';
        int last = count[ci][2];

        // 규칙1 first 바로 앞부터 last 바로 뒤까지 하나의 단어
        int start = first - 1;
        int end = last + 1;

        if (start >= idx && end < N && checkRule1(start, end, c)) {
            String prefix = new String(A, idx, start - idx);

            StringBuilder word = new StringBuilder();
            for (int i = start; i <= end; i++) {
                if (Character.isUpperCase(A[i]))
                    word.append(A[i]);
            }

            String next = dfs(end + 1);

            if (next != null)
                return memo[idx] = join(prefix, word.toString(), next);
        }

        // 규칙2 해당 소문자가 정확히 2번 나와야 함
        if (count[ci][0] == 2) {
            String word = checkRule2(first + 1, last - 1);
            if (word != null) {
                String prefix = new String(A, idx, first - idx);
                String next = dfs(last + 1);
                if (next != null) 
                    return memo[idx] = join(prefix, word, next);
            }
        }

        // 두 규칙 모두 실패
        memo[idx] = null;
        return null;
    }
}
