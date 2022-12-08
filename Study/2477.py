# 넓이 정보를 알고 있을 배열 생성

area_arr =[[],[],[],[]] #동,서,남,북 으로만 존재하기 때문에 크기가 4인 arr

n = int(input())

for i in range(1,7): # 변이 6개인 육각형이기때문에 6번만 받아주면 된다
    direction,length = map(int,input().split())
    area_arr[direction - 1].append(i) # 몇번째로 입력받은 변인지 알기위함.
    area_arr[direction - 1].append(length) # idx의 시작이 0부터라서 -1 해준다.

# 0 = 동쪽 , 1 = 서쪽 ,2 = 남쪽 , 3 = 북쪽
if len(area_arr[0]) > len(area_arr[1]):
    