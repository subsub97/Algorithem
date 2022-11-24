# 넓이 정보를 알고 있을 배열 생성

area_arr =[[],[],[],[]`] #동,서,남,북 으로만 존재하기 때문에 크기가 4인 arr

n = int(input())

for _ in range(6): # 변이 6개인 육각형이기때문에 6번만 받아주면 된다
    direction,length = map(int,input().split())
    area_arr[direction - 1].append(length) # idx의 시작이 0부터라서 -1 해준다.

# 0 = 동쪽 , 1 = 서쪽 ,2 = 남쪽 , 3 = 북쪽
if len(area_arr[0]) > len(area_arr[1]): # 서쪽변이 최장길이를 가진 경우
    if len(area_arr[2]) > len(area_arr[3]): # 북쪽으로 최장길이를 가진 경우
        square1 = area_arr[2][0] * area_arr[1][0]
        square2 = area_arr[2][1] * area_arr[0][1]
        sum_area = square1 + square2
    else: #남쪽이 최장길이 소유
        square1 = area_arr[3][0] * area_arr[1][0]
        square2 = area_arr[3][1] * area_arr[0][1]
else: #동쪽변이 최장길이를 가진 경우
    if len(area_arr[2]) > len(area_arr[3]): # 북쪽으로 최장길이를 가진 경우
        square1 = area_arr[2][0] * area_arr[0][0]
        square2 = area_arr[2][1] * area_arr[1][1]
        sum_area = square1 + square2
    else: #남쪽이 최장길이 소유
        square1 = area_arr[3][0] * area_arr[0][0]
        square2 = area_arr[3][1] * area_arr[1][1]
        sum_area = square1 + square2
print(sum_area * n)