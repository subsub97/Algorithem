# arr_2d = [
#     [0 for _ in range(5)]
#     for _ in range(7)
# ]
#
# for elem in arr_2d:
#     for idx in elem:
#         print(idx,end=' ')
#     print('a'>'b')
#
# A ='abc'
# B =A[1:3]
#
#
# arr=['C','B']
# print(min(arr))

for i in range(5):
    for j in range(4):
        if j ==2:
            break
        print(j)
# '''
# 3-1) 두 문자열(길이는 1 이상 2,000 이하 정수)의 가장 긴 공통의 부분문자열을 구하는 프로그램을 작성하시오. 가장 긴 공통의 부분문자열이 여러개 일 경우, 이들 중 사전식으로 가장 앞에 나오는 문자열 하나를 출력한다.
# '''
# first_word = input()
# second_word = input()
# memo_arr = []
#
# def find_same_char_amount(A,B):
#     A_length = len(A) + 1
#     B_length = len(B) + 1
#     max_length = 0
#
#     arr_2d = [
#         [0 for _ in range(B_length)] # col
#         for _ in range(A_length) # row
#     ]
#
#     for i in range(1,A_length):
#         for j in range(1,B_length):
#             if A[i-1] == B[j-1]:
#                 arr_2d[i][j] = arr_2d[i-1][j-1] + 1
#                 if arr_2d[i][j] > max_length:
#                     memo_arr = []
#                     max_length = arr_2d[i][j]
#                     memo_arr.append(i)
#                     memo_arr.append(j)
#
#                 elif arr_2d[i][j] == max_length and A[i-1] > A[memo_arr[0]-1]:
#                      memo_arr = []
#                      memo_arr.append(i)
#                      memo_arr.append(j)
#
#
#
#     return max_length,memo_arr
#
# max_length,memo_arr=find_same_char_amount(first_word,second_word)
# start_point = memo_arr[0] - max_length
#
#
# for i in range(start_point,memo_arr[0]):
#     print(first_word[i],end='')



'''
문 2) 다음과 같은 (1이상 2,000이하 정수)개의 행과 (1이상 2,000이하 정수)개의 열의 셀(cell)들로 이루어진  격자가 있다. 여기서 가장 아래 행은 행 1이고 가장 위의 행은 행 이며, 가장 왼쪽 열은 열 1이고 가장 오른쪽 열은 열 이다. 셀 는 번째 행과 번째 열의 셀을 나타낸다. 각 셀 에는 비용 이 주어진다. 아래 그림은  격자 예를 보여준다. 여기서  = 3, ...,  = 8이다.
입력: 첫 번째 줄에 과 이 주어진다. 다음 각 줄에 각 행(위로부터 아래로)의 셀의 비용이 주어진다.

출력:
경로의 최소 비용을 출력한다.
'''
import sys
# 행, 열을 입력 받는다.
n,m = map(int,input().split())

max_num = sys.maxsize
arr_2d = [
    list(map(int,input().split()))
    for _ in range(n)
]
# 최소비용 기록을 위한 배열 생성
dp_arr = [
    [max_num for _ in range(m)]
    for _ in range(n)
]
min_cost = 0

#  n행 1열부터 1행 m열까지 확인해야한다.
for i in range(n-1,-1,-1): # 행을 관리하는 i는 n부터 시작한다.
    for j in range(m): # 열을 관리하는 j는 1부터 시작하며 올라간다.
        if i == n-1: # 1행인 경우에는 바로 DP_taple에 넣어준다.
            dp_arr[i][j] = arr_2d[i][j]
        elif j == 0: # 맨 왼쪽 열인 경우는 왼쪽 위를 확인 할 필요가 없다.
            if dp_arr[i][j] >= (dp_arr[i+1][j] +arr_2d[i][j]):
                dp_arr[i][j] = dp_arr[i+1][j] + arr_2d[i][j]
            if dp_arr[i][j+1] >= (dp_arr[i+1][j] + arr_2d[i][j+1]):
                dp_arr[i][j+1] = dp_arr[i+1][j] + arr_2d[i][j+1]
        elif j == m-1: #맨 마지막 열인 경우는 우측상단은 고려대상이 없다.
            if dp_arr[i][j-1] >= (dp_arr[i + 1][j] + arr_2d[i][j-1]): # arr_2d 기준 왼쪽 상단 값과의 합을 확인
                dp_arr[i][j - 1] = dp_arr[i + 1][j] + arr_2d[i][j-1]
            if dp_arr[i][j] >= (dp_arr[i + 1][j] + arr_2d[i][j]): # arr_2d 기준 상단 값과의 합을 확인
                dp_arr[i][j] = dp_arr[i + 1][j] + arr_2d[i][j]
        # elif i == 0: # 최상단 행렬에서는 최소비용을 비교 할 곳이 없다.
        #     break
        else: # 가장자리 행렬이 아닐때의 Dp_table에 넣어보도록하자
            if dp_arr[i][j-1] >= (dp_arr[i + 1][j] + arr_2d[i][j-1]): # arr_2d 기준 왼쪽 상단 값과의 합을 확인
                dp_arr[i][j - 1] = dp_arr[i + 1][j] + arr_2d[i][j-1]
            if dp_arr[i][j] >= (dp_arr[i + 1][j] + arr_2d[i][j]): # arr_2d 기준 상단 값과의 합을 확인
                dp_arr[i][j] = dp_arr[i + 1][j] + arr_2d[i][j]
            if dp_arr[i][j+1] >= (dp_arr[i+1][j] + arr_2d[i][j+1]):  # arr_2d 기준 우측상단 값과의 합을 확인
                dp_arr[i][j+1] = dp_arr[i+1][j] + arr_2d[i][j+1]

min_cost = min(dp_arr[0])
print(min_cost)

