-- 코드를 입력하세요
select board_id, writer_id, title, price, 
    (case
    when status like 'DONE' then '거래완료'
    when status like 'RESERVED' then '예약중'
    else '판매중'
    end)
    as status
from USED_GOODS_BOARD
where date_format(created_date, '%Y-%m-%d') like '2022-10-05'
order by board_id desc