-- 코드를 입력하세요

select user_id, nickname, concat(city," ", street_address1, " ", street_address2) as '전체주소', 
    concat(left(tlno, 3), "-", mid(tlno,4,4),"-", right(tlno,4)) as "전화번호"
from USED_GOODS_USER
where user_id in (
    select writer_id 
    from USED_GOODS_BOARD
    group by writer_id
    having count(writer_id)>=3
)
order by user_id desc