-- 코드를 입력하세요
-- https://jhnyang.tistory.com/328 where절 문자열조건
-- where절에서 여러 조건을 만족할 때는 in 쓰기
-- like는 한개랑 비교할 때
-- "문자%" -> "문자열입니다." "문자열이에요."
-- like문 와일드 카드
-- % 0개 이상
-- "문자_" -> "문자열", "문자수"
-- _ 1개
select category, price as max_price, product_name
from food_product
where price in 
    (select max(price)
    from food_product
    group by category)
and category in ('과자', '국', '김치', '식용유')
order by max_price desc;