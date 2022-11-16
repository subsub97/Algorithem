'''
행렬에서 각 최저 비용만을 이용해서 행렬 최상단으로 올라가는 알고리즘
'''

# 행, 열을 입력 받는다.
n,m = map(int,input().split())

arr_2d = [
    list(map(int,input().split()))
    for _ in range(n)
]
# 최소비용 기록을 위한 배열 생성
dp_arr = [
    0 for _ in range(m)
]
min_cost = 0

#  n행 1열부터 1행 m열까지 확인해야한다.
for i in range(n-1,-1,-1): # 행을 관리하는 i는 n부터 시작한다.
    for j in range(m): # 열을 관리하는 j는 1부터 시작하며 올라간다.
        if j == 0 and i != 0: # 최좌측 열인 경우 왼쪽 대각위는 확인 하지 않는다. 꼭대기 행에서는 확인 안함
            dp_arr[j] = arr_2d[i-1][j] # 우측 상단 확인
            dp_arr[j+1] = arr_2d[i-1][j+1] # 상단 확인
        elif j == m-1 and i != 0: # 최우측 열인 경우는 우측상단은 확인 하지 않아도 된다. 꼭대기에서는 확인 안함
            dp_arr[j - 1] = arr_2d[ i- 1][j-1] # 왼쪽 상단 확인
            dp_arr[j] = arr_2d[i - 1][j] # 상단 확인
        elif i != 0:
            dp_arr[j - 1] = arr_2d[i - 1][j - 1]  # 왼쪽 상단 확인
            dp_arr[j] = arr_2d[i - 1][j]  # 상단 확인
            dp_arr[j + 1] = arr_2d[i - 1][j + 1]  # 상단 확인
        else:
            dp_arr[j] = arr_2d[n-1][j]
    min_cost += min(dp_arr)

print(min_cost)



