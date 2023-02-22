-- 코드를 입력하세요
select a.product_id, a.product_name, sum(a.price*b.amount) as total_sales
from food_product a
    join food_order b
    on a.product_id = b.product_id
where date_format(b.produce_date,'%Y-%m') = '2022-05'
group by a.product_id
order by sum(a.price*b.amount) desc, a.product_id