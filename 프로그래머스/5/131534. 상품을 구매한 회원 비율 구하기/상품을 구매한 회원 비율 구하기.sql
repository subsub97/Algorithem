# 비율 : 상품을 구매한 회원수 / 21년에 가입한 전체 회원수 (두자리 반올림)
#년을 기준으로 오름차순
WITH cte AS (SELECT COUNT(*) as cnt
    FROM USER_INFO ui
    WHERE DATE_FORMAT(ui.JOINED, "%Y-%m-%d") >= "2021-01-01" AND DATE_FORMAT(ui.JOINED, "%Y-%m-%d") < "2022-01-01")
SELECT YEAR(O.SALES_DATE) as YEAR, MONTH(O.SALES_DATE) as MONTH, COUNT(DISTINCT(O.USER_ID)) as PURCHASED_USERS, ROUND(COUNT(DISTINCT(O.USER_ID)) / cte.cnt,1) as PURCHASED_RATIO
FROM ONLINE_SALE O,cte
WHERE O.USER_ID IN (
    SELECT USER_ID
    FROM USER_INFO
    WHERE DATE_FORMAT(JOINED, "%Y-%m-%d") >= "2021-01-01" AND DATE_FORMAT(JOINED, "%Y-%m-%d") < "2022-01-01")
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH


# 21년 전체 가입 회원 수는 구함
# 월별로 구매한 사람을 알자

# 21년에 FROM에서 상품구매한 사람을 뽑아
# SELECT 해서 
# YEAR랑 MONTH를 구분해야한다.

#### 21년을 어떻게 선택하지? 검색함
# ONLINE_SALE에서 21년에 가입한 회원 중 상품을 구매한 회원 수 구하기 
# 월별로 구매한 회원수