m = 3
n = 7
dp = [1] * n
for i in range(1,m):
    for j in range(1,n):
        dp[j] += dp[j-1]
print(dp[-1])