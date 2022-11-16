'''
1-2) 계단을 올라갈 때 한 번에 1개, 3개 혹은 4개의 계단만 오를 수 있다. 각 계단은 밟을 때 비용이 있다. 바닥에서 가장 위의 계단으로 올라갈 때 밟는 계단의 비용 합이 최소가 되도록 하면서 올라갈 때, 밟는 계단의 비용 합을 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 n이 주어진다. 다음 줄에 계단 1부터 계단 n까지 각 계단을 밟을 때 비용이 차례대로 주어진다.
출력
바닥에서 가장 위의 계단으로 올라갈 때 밟는 계단의 최소 비용 합을 출력한다.


입력 예
2 7 2 9 12 3 12 6 7

출력 예
12
'''
def find_cost_using_step(cost_arr,n):
    sum_costArr = [ 0 for _ in range(n+1)]
    #0번째 층 비용
    sum_costArr[0] = 0
    #1번째 층 비용
    sum_costArr[1] = cost_arr[0]
    #2번째 층 비용
    sum_costArr[2] = cost_arr[1] + cost_arr[0]
    #3번쨰 층 비용
    sum_costArr[3] = cost_arr[2] if (sum_costArr[1] + sum_costArr[2]) >= 1 else sum_costArr[2] + cost_arr[2]


    for i in range(4,n+1):
        sum_costArr[i] = min(sum_costArr[i-1],sum_costArr[i-3],sum_costArr[i-4]) + cost_arr[i-1]
        print(sum_costArr[i])



    return sum_costArr[n]

n = int(input())
cost_arr = list(map(int,input().split()))

print(find_cost_using_step(cost_arr,n))

[2 5 10]

