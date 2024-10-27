# 读取输入
N, M = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(N)]

# 定义移动方向顺序：下、右、上、左（逆时针）
directions = [(1, 0), (0, 1), (-1, 0), (0, -1)]
dir_idx = 0  # 初始方向：向下

# 初始化当前位置（0-based索引）
x, y = 0, 0
last_value = matrix[x][y]

# 标记已访问的格子
visited = [[False for _ in range(M)] for _ in range(N)]
visited[x][y] = True

while True:
    moved = False
    # 尝试四个方向
    for _ in range(4):
        dx, dy = directions[dir_idx]
        new_x = x + 2 * dx
        new_y = y + 2 * dy
        # 检查新位置是否在矩阵范围内且未被访问
        if 0 <= new_x < N and 0 <= new_y < M and not visited[new_x][new_y]:
            # 更新位置
            x, y = new_x, new_y
            last_value = matrix[x][y]
            visited[x][y] = True
            moved = True
            break  # 成功移动，跳出方向尝试
        else:
            # 方向逆时针旋转
            dir_idx = (dir_idx + 1) % 4
    if not moved:
        break  # 无法移动，终止循环

print(last_value)
