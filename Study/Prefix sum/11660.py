# 2차원 누적합 문제 try 2
# try 1에서 sum_val을 해주는 index값을 잘 못 정해주었던 것을 수정함
import sys

row , q = map(int,input().split()) # row는 2차원 배열의 행의 수 q는 구간합을 구할 question의 수

arr_2d = [] # 숫자들이 주어져있는 2차원 배열

for i in range(row): #2차원 배열에 값을 넣어주는 작업
    arr_2d.append(list(map(int,sys.stdin.readline().split())))

sum_2d = []
answer_arr = []

for arr in arr_2d:
    temp_list =[]
    sum_num = 0
    for num in arr: # 각 row별 누적 값을 구하기위한 for문
        sum_num += num
        temp_list.append(sum_num)
    sum_2d.append(temp_list)

for j in range(q):
    x1,y1,x2,y2 = map(int,sys.stdin.readline().split()) #(x1,y1) 부터 (x2,y2) 사이의 합을 찾아야한다.
    sum_val = 0
    x1 = x1 - 1; x2 = x2 - 1; y1 = y1 - 1; y2 = y2 - 1
    if x1 < x2: # 각은 행에서의 구간 합을 구하는것이 아닌경우
        if y1 == 0:
            for i in range(x1,x2+1):
                if i == x2:
                    sum_val += sum_2d[i][y2]
                    break
                sum_val +=  sum_2d[i][row -1]
            answer_arr.append(sum_val)
        else:
            for i in range(x1,x2+1):
                if i == x2:
                    sum_val += sum_2d[i][row-1] - sum_2d[i][y1-1] # 0번쨰 자기보다 하나 이전 누적합을 값을 빼주어야하기때문에 -1 을 해준다.
                    break
                sum_val += sum_2d[i][row -1] - sum_2d[i][y1-1] # 행이 하나씩 증가함에 따라 누적값 증가
            answer_arr.append(sum_val)
    else: # 같은 행인 경우
        if y1 == 0:
            sum_val = sum_2d[x1][y2]
            answer_arr.append(sum_val)
        else:
            sum_val = sum_2d[x1][y2] - sum_2d[x1][y1-1]
            answer_arr.append(sum_val)


for num in answer_arr:
    print(num)