-- 코드를 입력하세요
-- mysql에서는 5/3을 하면 소숫점 자리까지 나온다 
-- select truncate
-- SELECT (PRICE DIV 10000) * 10000 
-- select truncate(price/10000,0) * 10000 
select floor(price/10000) * 10000
    AS PRICE_GROUP, 
    COUNT(PRODUCT_ID) AS PRODUCTS
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP
;