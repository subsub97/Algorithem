WITH CATEGORY_MAX AS (
    SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
)

SELECT M.CATEGORY, M.MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT FP JOIN CATEGORY_MAX M
ON FP.CATEGORY = M.CATEGORY AND FP.PRICE = M.MAX_PRICE
WHERE FP.CATEGORY IN ('식용유', '과자', '국', '김치') 
ORDER BY MAX_PRICE DESC;