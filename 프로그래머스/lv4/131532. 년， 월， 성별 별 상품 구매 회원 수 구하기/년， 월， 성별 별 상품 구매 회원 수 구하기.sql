-- 코드를 입력하세요
SELECT year(b.sales_date) as year, month(b.sales_date) as month, a.gender as gender, count(distinct b.user_id) as users
from user_info a
join online_sale b on a.user_id = b.user_id
where gender is not null
group by year, month, gender
order by year, month, gender
;
