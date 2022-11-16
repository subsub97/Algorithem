'''
문 2) 다음과 같은 (1이상 2,000이하 정수)개의 행과 (1이상 2,000이하 정수)개의 열의 셀(cell)들로 이루어진  격자가 있다. 여기서 가장 아래 행은 행 1이고 가장 위의 행은 행 이며, 가장 왼쪽 열은 열 1이고 가장 오른쪽 열은 열 이다. 셀 는 번째 행과 번째 열의 셀을 나타낸다. 각 셀 에는 비용 이 주어진다. 아래 그림은  격자 예를 보여준다. 여기서  = 3, ...,  = 8이다.
입력: 첫 번째 줄에 과 이 주어진다. 다음 각 줄에 각 행(위로부터 아래로)의 셀의 비용이 주어진다.

출력:
경로의 최소 비용을 출력한다.
'''
# 문제에서 최대 비용을 주어주지 않아 임의로 최대 값을 넣어주기 위함
import sys

# 행, 열을 입력 받는다.
n,m = map(int,input().split())

arr_2d = [
    list(map(int,input().split()))
    for _ in range(n)
]
# 최소비용 기록을 위한 배열 생성

# 1차원 배열을 이용해서 문제를 해결해보자
# dp_arr = [
#     [max_num for _ in range(m)]
#     for _ in range(n)
# ]
dp_arr = [ 0 for _ in range(m) ]
temp = 0 # 1차원 dp_arr을 유지하기 위한 변수

min_cost = 0

#  n행 1열부터 1행 m열까지 확인해야한다.
for i in range(n-1,-1,-1): # 행을 관리하는 i는 n부터 시작한다.
    for j in range(m): # 열을 관리하는 j는 1부터 시작하며 올라간다.
        if i == n-1: # 1행인 경우에는 바로 DP_taple에 넣어준다.
            dp_arr[j] = arr_2d[i][j]
        elif j == 0: # 맨 왼쪽 열인 경우는 왼쪽 위를 확인 할 필요가 없다.
            temp = dp_arr[j]
            dp_arr[j] = arr_2d[i][j] + min(dp_arr[j],dp_arr[j+1]) # DP_arr의 상단읜 원소합

        elif j == m-1: #맨 마지막 열인 경우는 우측상단은 고려대상이 없다.
            # temp = dp_arr[j]
            dp_arr[j] = arr_2d[i][j] + min(dp_arr[j-1],dp_arr[j]) #DP_arr의 좌상단의 원소합이 더 작다면 덮어씌움

        else: # 가장자리 열이 아닐때의 DP_arr에 넣어보도록하자
            if temp >= dp_arr[j]:
                temp = dp_arr[j]
                dp_arr[j] = arr_2d[i][j] + min(dp_arr[j],dp_arr[j+1])
            else:
                dp_arr[j] = arr_2d[i][j] + min(temp,dp_arr[j+1])

min_cost = min(dp_arr)
print(min_cost)

# #  n행 1열부터 1행 m열까지 확인해야한다.
# for i in range(n-1,-1,-1): # 행을 관리하는 i는 n부터 시작한다.
#     for j in range(m): # 열을 관리하는 j는 1부터 시작하며 올라간다.
#         if i == n-1: # 1행인 경우에는 바로 DP_taple에 넣어준다.
#             dp_arr[i][j] = arr_2d[i][j]
#         elif j == 0: # 맨 왼쪽 열인 경우는 왼쪽 위를 확인 할 필요가 없다.
#             if dp_arr[i][j] >= (dp_arr[i+1][j] +arr_2d[i][j]):
#                 dp_arr[i][j] = dp_arr[i+1][j] + arr_2d[i][j]
#             if dp_arr[i][j+1] >= (dp_arr[i+1][j] + arr_2d[i][j+1]):
#                 dp_arr[i][j+1] = dp_arr[i+1][j] + arr_2d[i][j+1]
#         elif j == m-1: #맨 마지막 열인 경우는 우측상단은 고려대상이 없다.
#             if dp_arr[i][j-1] >= (dp_arr[i + 1][j] + arr_2d[i][j-1]): # arr_2d 기준 왼쪽 상단 값과의 합을 확인
#                 dp_arr[i][j - 1] = dp_arr[i + 1][j] + arr_2d[i][j-1]
#             if dp_arr[i][j] >= (dp_arr[i + 1][j] + arr_2d[i][j]): # arr_2d 기준 상단 값과의 합을 확인
#                 dp_arr[i][j] = dp_arr[i + 1][j] + arr_2d[i][j]
#         # elif i == 0: # 최상단 행렬에서는 최소비용을 비교 할 곳이 없다.
#         #     break
#         else: # 가장자리 행렬이 아닐때의 Dp_table에 넣어보도록하자
#             if dp_arr[i][j-1] >= (dp_arr[i + 1][j] + arr_2d[i][j-1]): # arr_2d 기준 왼쪽 상단 값과의 합을 확인
#                 dp_arr[i][j - 1] = dp_arr[i + 1][j] + arr_2d[i][j-1]
#             if dp_arr[i][j] >= (dp_arr[i + 1][j] + arr_2d[i][j]): # arr_2d 기준 상단 값과의 합을 확인
#                 dp_arr[i][j] = dp_arr[i + 1][j] + arr_2d[i][j]
#             if dp_arr[i][j+1] >= (dp_arr[i+1][j] + arr_2d[i][j+1]):  # arr_2d 기준 우측상단 값과의 합을 확인
#                 dp_arr[i][j+1] = dp_arr[i+1][j] + arr_2d[i][j+1]