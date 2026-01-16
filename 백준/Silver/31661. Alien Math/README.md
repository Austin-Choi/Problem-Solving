# [Silver I] Alien Math - 31661 

[문제 링크](https://www.acmicpc.net/problem/31661) 

### 성능 요약

메모리: 14456 KB, 시간: 108 ms

### 분류

자료 구조, 문자열, 해시를 사용한 집합과 맵, 파싱

### 제출 일자

2026년 1월 16일 11:28:56

### 문제 설명

<p>Humans are exploring the stars, and we have encountered aliens! In an exchange of scientific knowledge, we’ve learned that not only do they use entirely different symbols to do math, they don’t even use base ten! It would be nice to have a program to convert the alien numbers to decimal.</p>

<p>Shockingly, the aliens represent each of their digits with what might be multiple ascii characters. For example, <code>aba</code> might be a single digit for the alien’s numerical system.</p>

<p>Given the base of their number system, an ordered list of all their system’s digits, and a number $X$, convert $X$ to base $10$ and print it without any leading zeros.</p>

<p>For a quick refresher on number systems, a number system with base $B$ has $B$ digits that represent the values $0$ through $B-1$. For example, an alien number system with base $3$ could have digits <code>zork</code>, <code>ooble</code>, and <code>trzap</code> representing the values $0$, $1$, and $2$ respectively. The alien number <code>trzapzorkooble</code>, when written in standard decimal notation, is just $2 \cdot 3^2 + 0 \cdot 3^1 + 1 \cdot 3^0 = 19$.</p>

### 입력 

 <p>The first line of input will consist of a single integer $B$ ($2 \leq B \leq 10^3$) indicating the number of digits used in the alien number system.</p>

<p>The next line consists of $B$ strings $D_0, D_1, \ldots , D_{B-1}$ with a single space between each string. These represent the digits in the alien number system, namely string $D_ i$ is the digit with value $i$. Each digit string $D_ i$ will consist of at least one and at most $10$ characters and will only contain lowercase letters (i.e. <code>a</code> through <code>z</code>).</p>

<p>The third line contains a single string $X$ consisting of at least $1$ and at most $300$ characters. This string represents a number written in the alien number system. The value of this number, in decimal, is at most $10^9$. It may be written with leading zeros in the alien number system. You are guaranteed that $X$ is the concatenation of digits from the alien number system (i.e. it is a valid number in their system).</p>

<p>Oh, and this is important. The aliens knew their system was a bit complicated, so to make it easier to read, they designed their number system so that the strings representing distinct digits do not have one being a prefix of the other. That is, $D_ i$ is not a prefix of $D_ j$ for any indices $i \neq j$.</p>

### 출력 

 <p>Print the value of $X$ in standard base $10$ notation without any leading zeros.</p>

