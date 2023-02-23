-- 코드를 입력하세요
-- 여러개 조인할 때 따로 서브쿼리로 쓰지 말고
-- 하나의 기준점을 주고 
-- from table1 a
-- join table2 b on a.id = b.id
-- join table3 c on a.id = c.id
-- 이런식으로 다중조인 가능
select a.author_id, b.author_name, a.category, sum(a.price*c.sales) as total_sales
from book a
join author b on a.author_id = b.author_id
join book_sales c on a.book_id = c.book_id
where date_format(c.sales_date,'%Y-%m') = '2022-01'
group by a.author_id, a.category
order by a.author_id asc, a.category desc;