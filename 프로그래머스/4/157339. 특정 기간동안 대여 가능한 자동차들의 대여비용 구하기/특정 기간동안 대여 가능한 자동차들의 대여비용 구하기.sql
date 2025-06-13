-- 코드를 입력하세요
-- 자동차 종류가 세단 또는 SUV 인 자동차 찾기 
-- 22년 11월 1일부터 11월 30일 까지 대여 가능하고 30일간의 대여 금액이 
-- 50만원 이상 200만원 미안인 자동차 

SELECT 
    cc.car_id AS CAR_ID, 
    cc.car_type AS CAR_TYPE, 
    ROUND((cc.daily_fee * 30) * (100 - dp.discount_rate) / 100,0) AS FEE
FROM CAR_RENTAL_COMPANY_CAR cc
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN dp 
    ON cc.car_type = dp.car_type AND dp.duration_type = '30일 이상'
WHERE cc.car_type IN ('세단', 'SUV')
  AND NOT EXISTS (
      SELECT 1
      FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY rh
      WHERE rh.car_id = cc.car_id
        AND rh.start_date <= '2022-11-30'
        AND rh.end_date >= '2022-11-01'
  )
  AND ((cc.daily_fee * 30) * (100 - dp.discount_rate) / 100) >= 500000
  AND ((cc.daily_fee * 30) * (100 - dp.discount_rate) / 100) < 2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC;
