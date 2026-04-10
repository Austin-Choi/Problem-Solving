# [Silver II] Problem Classification - 17925 

[문제 링크](https://www.acmicpc.net/problem/17925) 

### 성능 요약

메모리: 21512 KB, 시간: 228 ms

### 분류

자료 구조, 해시를 사용한 집합과 맵, 문자열, 집합과 맵

### 제출 일자

2026년 4월 10일 11:23:55

### 문제 설명

<p>When reading programming problems, one can often get some hints regarding the topic of the problem by skimming the problem statement for certain words. If, for example, the word "vertex" or "edge" appears, the problem is almost certainly a graph problem, while the words "words" or "letters" suggest that the problem is about strings.</p>

<p>Your task is to implement a simple program that attempts to classify a problem according to one of $N$ categories. Each category has an associated set of words, which, if they appear as words in a statement, suggest the problem belongs to this category. When classifying a statement, the program should suggest those categories which have the highest number of occurences of their associated words. Note that words that are part of other words do not count. For example, the word <code>statement</code> should not count as an occurance for the word <code>ate</code>.</p>

<p>In the above example, we suggested that the category <code>graph</code> may have the associated words <code>vertex</code> and <code>edge</code> and the category <code>string</code> could have the associated words <code>words</code> and <code>letters</code>. Then, if there were $3$ occurances each of the words <code>vertex</code> and <code>edge</code>, the number of matches for the category <code>graph</code> would be $6$. If the statement contained $14$ occurances of <code>words</code> and $4$ of <code>letters</code>, the number of matches for the category <code>string</code> would be $18$. Since there are more matches for the second category, the program should suggest it.</p>

<p>If there are multiple categories with the same number of matches, your program should suggest all of them.</p>

### 입력 

 <p>The first line of input contains the number of categories $1 \le N \le 10$.</p>

<p>The next $N$ lines each contain a description of a category. The description starts with the name of the category -- a single word. Then, an integer $1 \le W \le 10$ follows -- the number of words associated with this category. This is followed by those $W$ words, separated by spaces. No two words <em>within a category</em> are the same, and no two categories have the same name.</p>

<p>This is followed by a number of lines describing the statement of the problem. Each line contains a list of space-separated words.</p>

<p>Every word in the input will consist solely of at most $30$ lower-case letters <code>a-z</code>. The statement consists of between $1$ and $10\,000$ words.</p>

### 출력 

 <p>For each suggested category, output the name of the category on a single line, in lexicographical order.</p>

