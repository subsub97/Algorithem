'''n(1≤n≤10,000)개 계단을 바닥에서 가장 위의 계단으로 올라가려고 한다.

1-1) 계단을 올라갈 때 한 번에 1개, 3개 혹은 4개의 계단만 오를 수 있다.
    바닥에서 가장 위 계단으로 올라가는 방법의 수를 구하시오.
입력
첫 번째 줄에 n이 주어진다.
출력
바닥에서 가장 위 계단으로 올라가는 방법의 수를 99,999로 나눈 나머지를 출력한다.
입력 예
9  // n
출력 예
40'''

n = int(input())# 몇번쨰 계단을 오르고싶은지를 받는 변수

def find_path_method(num):
    cnt = 0 # 몇가지의 경로가있는지 카운트해주는 변수
    if num == 1:
        return 1
    elif num == 0:
        return  1
    elif num < 0:
        return 0

    return find_path_method(num -1) + find_path_method(num-3) + find_path_method(num-4)

print(find_path_method(n)%99999)

# DP_version

def find_step(n):
 	step[0] = 1
	step[1] = 1
	step[2] = 1
	step[3] = 2
	for i in range(4,n+1):
		step[i] = step[i-1] + step[i-3] + step[i-4]
	return step[n]

n = int(input())
step = [0 for _ in range(n+3)]

print(find_step(n)%99999)



