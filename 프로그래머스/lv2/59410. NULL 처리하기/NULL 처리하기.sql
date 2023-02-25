-- 코드를 입력하세요
-- IFNULL(컬럼명, 대체할 값)함수
-- IFNULL을 쓰면 WHERE절에 NULL인지 안 찾고도 변경 가능
SELECT ANIMAL_TYPE, 
-- IFNULL(NAME, 'No name') 
    coalesce(name, 'No name')
    AS NAME,
    SEX_UPON_INTAKE
FROM ANIMAL_INS
ORDER BY ANIMAL_ID;