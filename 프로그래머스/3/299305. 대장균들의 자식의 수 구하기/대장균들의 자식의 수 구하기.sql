SELECT e.ID, COUNT(e1.ID) AS CHILD_COUNT
# 이렇게하면 e의 모든 ID는 무조건 다 나온다.
# 그리고 추가적으로 늘어나느 경우도 생긴다.
# e1의 PARENT_ID는 중복이 존재하기 때문
FROM ECOLI_DATA e LEFT JOIN ECOLI_DATA e1 ON e.ID = e1.PARENT_ID
GROUP BY e.ID
ORDER BY e.ID ASC;


## 어떻게 자식을 찾지?