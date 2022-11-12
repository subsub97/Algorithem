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
def find_cost_using_step(k_arr,cost_arr,n):
    sum_costArr = [ 0 for _ in range(n+1)]
    #0번째 층 비용
    sum_costArr[0] = 0
    # k_arr= [1,3,4,7]

    max_range = k_arr[-1]
    for elem in k_arr:
        for  i in range(1,max_range + 1):
            if elem == i:
                sum_costArr[elem] =
            else:
                sum_costArr[i] =
    #1번째 층 비용
    sum_costArr[1] = cost_arr[0]
    #2번째 층 비용
    sum_costArr[2] = cost_arr[1] + cost_arr[0]
    #3번쨰 층 비용
    sum_costArr[3] = cost_arr[2] if (sum_costArr[1] + sum_costArr[2]) >= 1 else sum_costArr[2] + cost_arr[2]


    for i in range(4,n+1):
        sum_costArr[i] = min(sum_costArr[i-1] , sum_costArr[i-3] ,sum_costArr[i-4]) + cost_arr[i-1]



    return sum_costArr[n]

n = int(input())
cost_arr = list(map(int,input().split()))

print(find_cost_using_step(cost_arr,n))