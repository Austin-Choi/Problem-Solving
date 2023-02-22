-- 코드를 입력하세요
select left(product_code,2) as category, count(*) as products
from product
group by left(product_code, 2)
order by left(product_code, 2)