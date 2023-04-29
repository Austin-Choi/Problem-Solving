-- 코드를 입력하세요
select concat("/home/grep/src/",a.board_id,"/",file_id,file_name,file_ext) as FILE_PATH
from 
    (select *
    from USED_GOODS_BOARD
    order by views desc
     limit 1
    ) as a
join USED_GOODS_FILE b
    on a.board_id = b.board_id
where views = (select max(views) from USED_GOODS_BOARD)
order by file_id desc