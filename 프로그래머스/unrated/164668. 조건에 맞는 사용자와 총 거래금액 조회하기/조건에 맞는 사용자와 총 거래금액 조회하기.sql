-- 코드를 입력하세요
select b.user_id, b.nickname, sum(a.price) as total_sales 
from USED_GOODS_BOARD a join USED_GOODS_USER b
    on a.writer_id = b.user_id
where a.status = 'DONE'
group by a.writer_id
having total_sales >= 700000
order by total_sales asc