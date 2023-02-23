-- 코드를 입력하세요
-- timestampdiff(format,시간1,시간2) day의 경우 +1 ;
select b.animal_id, b.name
from animal_ins a join animal_outs b
    on a.animal_id = b.animal_id
order by timestampdiff(minute, a.datetime, b.datetime ) desc
limit 2;

