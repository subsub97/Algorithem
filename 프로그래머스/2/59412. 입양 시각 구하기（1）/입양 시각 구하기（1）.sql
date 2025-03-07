SELECT HOUR(a.DATETIME) as HOUR ,COUNT(*) AS "COUNT"
FROM ANIMAL_OUTS a
WHERE HOUR(a.DATETIME) >= 9 && HOUR(a.DATETIME) < 20
GROUP BY HOUR(a.DATETIME)
ORDER BY HOUR(a.DATETIME) ASC
