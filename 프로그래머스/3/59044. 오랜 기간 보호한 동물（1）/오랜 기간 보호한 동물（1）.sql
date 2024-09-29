# 아직 입양을 못 간 동물 중, 가장 오래 보호소에 있었던 동물 3마리
# 먼저 들어왔는데 나간 기록이 없는 동물을 찾자 
SELECT ai.NAME, ai.datetime AS DATETIME
FROM ANIMAL_INS ai LEFT JOIN ANIMAL_OUTS ao
    ON ai.ANIMAL_ID = ao.ANIMAL_ID
    WHERE ao.ANIMAL_ID IS NULL
ORDER BY ai.DATETIME ASC LIMIT 3;