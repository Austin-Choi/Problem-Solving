-- 코드를 입력하세요
--  7월에는 아이스크림 주문량이 많아 같은 아이스크림에 대하여 서로 다른 두 공장에서 아이스크림 가게로 출하를 진행하는 경우가 있습니다. 이 경우 같은 맛의 아이스크림이라도 다른 출하 번호를 갖게 됩니다.
-- 따라서 조인할때 flavor로 하고
-- group by 도 flavor로
select a.flavor
from first_half a
    join july b
    on a.flavor = b.flavor
group by a.flavor
order by (sum(a.total_order)+sum(b.total_order)) desc
limit 3;