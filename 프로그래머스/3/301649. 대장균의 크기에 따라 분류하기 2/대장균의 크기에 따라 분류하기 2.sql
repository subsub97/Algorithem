

SELECT e.ID, 
    CASE WHEN rate.p <= 0.25 THEN 'CRITICAL'
         WHEN rate.p <= 0.5 THEN 'HIGH'
         WHEN rate.p <= 0.75 THEN 'MEDIUM'
    ELSE 'LOW'
    END AS COLONY_NAME
FROM (
    SELECT e.ID, percent_rank() OVER(ORDER BY e.SIZE_OF_COLONY DESC) as p
FROM ECOLI_DATA e
    ) rate, ECOLI_DATA e
WHERE e.ID = rate.id
ORDER BY e.ID ASC;

