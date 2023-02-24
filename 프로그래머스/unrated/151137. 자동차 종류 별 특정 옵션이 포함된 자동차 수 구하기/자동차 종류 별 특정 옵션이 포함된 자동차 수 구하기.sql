-- 코드를 입력하세요
SELECT CAR_TYPE, COUNT(CAR_ID) AS CARS
FROM CAR_RENTAL_COMPANY_CAR
where options like '%통풍시트%' or options like '%열선시트%' or options like '%가죽시트%'
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE ASC
;