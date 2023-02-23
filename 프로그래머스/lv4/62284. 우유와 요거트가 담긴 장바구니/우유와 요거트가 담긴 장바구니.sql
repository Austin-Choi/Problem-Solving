-- 코드를 입력하세요
select cart_id
from cart_products
where
cart_id in
(select cart_id
from cart_products
where name like 'Yogurt')
and
cart_id in
(select cart_id
from cart_products
where name like 'Milk'
)
group by cart_id
order by cart_id