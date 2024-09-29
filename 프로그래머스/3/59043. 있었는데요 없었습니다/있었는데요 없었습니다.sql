#보호 시작일보다 입양일이 더 빠른 동물의 아이디와 이름을 조회하는 SQL문을 작성해주세요
SELECT ai.ANIMAL_ID, ai.NAME
FROM ANIMAL_INS ai 
    INNER JOIN ANIMAL_OUTS ao 
    ON ai.ANIMAL_ID = ao.ANIMAL_ID
WHERE ai.DATETIME > ao.DATETIME # 보호 시작일 > 입양일
ORDER BY ai.DATETIME ASC;