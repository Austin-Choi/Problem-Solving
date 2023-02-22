-- 코드를 입력하세요
-- set문 변수사용, :=, = , 사용법 공부

-- 오답
-- SELECT hour(datetime) as hour, count(hour(datetime)) as count
-- from animal_outs
-- group by hour
-- order by hour

set @hour := -1;

select (@hour := @hour + 1) as hour,
    (select count(hour(datetime)) 
    from animal_outs
    where hour(datetime) = @hour) as count
from animal_outs
where @hour < 23
order by hour;