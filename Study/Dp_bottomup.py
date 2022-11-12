
def find_step(n):
	step[0] = 1
	step[1] = 1
	step[2] = 1
	step[3] = 2
	for i in range(4,n+1):
		step[i] = step[i-1] + step[i-3] + step[i-4]
	return step[n]

n = int(input())
step = [0 for _ in range(n+1)]

print(find_step(n)%99999)