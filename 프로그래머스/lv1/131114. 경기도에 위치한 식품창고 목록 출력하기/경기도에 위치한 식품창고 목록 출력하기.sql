-- 코드를 입력하세요
-- SUBSTR()
-- SUBSTRING()
-- 다른풀이
-- CASE 대신 coalesce()
-- 
SELECT WAREHOUSE_ID, WAREHOUSE_NAME, ADDRESS, 
--    (CASE
--    WHEN FREEZER_YN IS NULL THEN 'N'
--    ELSE FREEZER_YN
--    END)
    coalesce(freezer_yn, 'N')
    AS FREEZER_YN
FROM FOOD_WAREHOUSE
-- WHERE SUBSTR(ADDRESS FROM 1 FOR 3) = '경기도'
where address like '경기도%'
ORDER BY WAREHOUSE_ID ASC
;