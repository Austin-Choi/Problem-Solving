-- 코드를 입력하세요

select a.title, a.board_id, b.reply_id, b.writer_id, b.contents, date_format(b.created_date, '%Y-%m-%d') as created_date
from USED_GOODS_BOARD a join USED_GOODS_REPLY b
    on a.board_id = b.board_id
where date_format(a.created_date, '%Y-%m') like '2022-10'
order by b.created_date, a.title
