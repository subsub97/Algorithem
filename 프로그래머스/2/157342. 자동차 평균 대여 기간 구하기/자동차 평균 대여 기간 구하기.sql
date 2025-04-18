SELECT c.CAR_ID AS CAR_ID,ROUND(AVG(DATEDIFF(c.end_date,c.start_date) + 1) ,1)  AS AVERAGE_DURATION
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY c
GROUP BY c.car_id
HAVING AVG(DATEDIFF(c.end_date,c.start_date) + 1) >= 7
ORDER BY AVERAGE_DURATION DESC, c.CAR_ID DESC

