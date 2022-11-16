'''
1-3) 계단을 올라갈 때 한 번에 s1, s2, s3, ..., sk (s1 < s2 < ... < sk)의 계단만 오를 수 있다. 각 계단은 밟을 때 비용이 있다.
바닥에서 가장 위의 계단으로 올라갈 때 밟는 계단의 비용 합이 최소가 되도록 하면서 올라갈 때, 밟는 계단의 비용 합을 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 n이 주어지고, 두 번째 줄에 각 계단 i(1≤i≤n)를 밟는 비용이 주어진다. 세 번째 줄에 k가 주어지고, 네 번째 줄에 s1, s2, ..., sk가 주어진다.

출력
바닥에서 가장 위의 계단으로 올라갈 때 밟는 계단의 최소 비용 합을 출력한다. 올라가는 방법이 없으면 –1을 출력한다.

입력 예
9
2 7 2 9 12 3 12 6 7 3
3
1 3 4

출력 예
12
'''
def find_cost_using_step(move_arr,cost_arr,n):
    sum_costArr = [ 10001 for _ in range(n+1)]
    #0번째 층 비용
    sum_costArr[0] = 0


    for i in range(move_arr[-1]): #len(m)번
        min_arr = []
        check_point = 0
        for cost in move_arr: # m번
            if i >= cost:
                min_arr.append(sum_costArr[i-cost])
                min_arr.append(sum_costArr[i])
                check_point = 1
            else:
                break
        if check_point == 1:
            min_cost = min(min_arr)
            sum_costArr[i] = min_cost + cost_arr[i - 1]


    for i in range(move_arr[-1],n+1): # n번
        min_arr = []
        for j in range(len(move_arr)): # len(m)번
            min_arr.append(sum_costArr[i-move_arr[j]]) # dp에서 사용 할 (i - 계단수) 를 계산하기 위함
        min_cost = min(min_arr)
        sum_costArr[i] = min_cost + cost_arr[i-1]

    if sum_costArr[n] >= 10001:
        return -1



    return sum_costArr[n]
#최종 목적지 n
n = int(input())
#각 계단의 비용
cost_arr = list(map(int,input().split()))
# k개의 칸
k = int(input())
#움직일 수 있는 조건
move_arr = tuple(map(int,input().split()))

print(find_cost_using_step(move_arr,cost_arr,n))