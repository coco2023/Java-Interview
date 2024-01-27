'''
HashTable
'''
class HashTable:
    def __init__(self, size):
        self.size = size
        self.table = [None] * size

    def hash_function(self, key):
        return key % self.size
    
    def insert(self, key, value):
        index = self.hash_function(key)
        if self.table[index] is None:
            self.table[index] = []
        self.table[index].append((key, value))

    def search(self, key):
        index = self.hash_function(key)
        if self.table[index] is not None:
            for pair in self.table[index]:
                if pair[0] == key:
                    return pair[1]
                
        return None
    
    
'''
quick Sort
'''
def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    right = [x for x in arr if x > pivot]
    middle = [x for x in arr if x == pivot]
    return quick_sort(left) + middle + quick_sort(right)

'''
Merge Sort: 分治算法
'''
def merge_sort(arr):
    if len(arr) <= 1:
        return arr
    
    mid = len(arr) // 2
    left = merge_sort(arr[:mid])
    right = merge_sort(arr[mid:])
    return merge(left, right)

def merge(left, right):
    result = []
    i = j = 0
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result += left[i:]
    result += right[j:]
    return result

####
### 搜索
####
'''
Binary Search
'''
def binary_search(arr, x):
    low, high = 0, len(arr) - 1
    while low <= high:                  # low <= high
        mid = (low + high) // 2
        if arr[mid] < x:
            low = mid + 1
        elif arr[mid] > x:
            high = mid - 1
        else:
            return mid
        
    return -1

'''
Jump Search: 在已排序的数组中，先按固定间隔跳跃，然后在较小的范围内进行线性搜索
'''
import math
def jump_search(arr, x):
    n = len(arr)
    step = int(math.sqrt(n))
    prev = 0
    while arr[min(step, n) - 1] < x:
        prev = step
        step += int(math.sqrt(n))
        if prev >= n:
            return -1
    while arr[prev] < x:
        prev += 1
        if prev == min(step, n):
            return -1
    if arr[prev] == x:
        return prev
    return -1

'''
Exponential Search: 在已排序的数组中，先找到可能包含目标值的范围，然后在该范围内使用二分搜索
'''
def exponential_search(arr, x):
    if arr[0] == x:
        return 0
    i = 1
    n = len(arr)
    while i < n and arr[i] <= x:
        i *= 2
    return binary_search(arr[:min(i, n)], x)

####
### 二叉树：前中后序遍历
####
class TreeNode:
    def __init__(seld, value):
        self.value = value
        self.left = None
        self.right = None

class BinaryTree:
    def __ini__(self, root_value):
        self.root = TreeNode(root_value)

    def print_tree(self, traversal_type="inorder"):
        if traversal_type == "inorder":
            return self._inorder_print(self.root, "")
        elif traversal_type == "preorder":
            return self._preorder_print(self.root, "")
        elif traversal_type == "postorder":
            return self._postorder_print(self.root, "")
        else:
            print("Traversal type " + str(traversal_type) + " is not supported.")
            return False
        

    '''
    Inorder: Left -> Root -> Right
    '''
    def _inorder_print(self, start, traversal):
        if start:
            traversal = self._inorder_print(start.left, traversal)
            traversal += (str(start.value) + " - ")
            traversal = self._inorder_print(start.right, traversal)
        return traversal
    
    """
    PreOrder: Root -> Left -> Right
    """
    def _preorder_print(self, start, traversal):
        if start:
            traversal += (str(start.value) + " - ")
            traversal = self._preorder_print(start.left, traversal)
            traversal = self._preorder_print(start.right, traversal)
        return traversal

    """
    postOrder: Left -> Right -> Root 
    """
    def _postorder_print(self, start, traversal):
        if start:
            traversal = self._preorder_print(start.left, traversal)
            traversal = self._preorder_print(start.right, traversal)
            traversal += (str(start.value) + " - ")
        return traversal

####
### 二叉搜索树
####
class BinarySearchTree:
    def __init__(self):
        self.root = None

    def insert(self, value):
        if not self.root:
            self.root = TreeNode(value)
        else:
            self._insert(value, self.root)

    def _insert(self, value, current_node):
        if value < current_node.value:
            if current_node.left is None:
                current_node.left = TreeNode(value)
            else:
                self._insert(value, current_node.left)
        elif value > current_node.value:
            if current_node.right is None:
                current_node.right = TreeNode(vlaue)
            else:
                self._insert(value, current_node.right)
        else:
            print("Value already in tree!")

    def find(self, value):
        if self.root:
            return self._find(value, self.root)
        else:
            return False
        
    def _find(self, value, current_node):
        if value < current_node.value and current_node.left:
            return self._find(value, current_node.left)
        elif value > current_node.value and current_node.right:
            return self._find(value, current_node.right)
        if value == current_node.value:
            return True
        return False

    
####
### DFS BFS 自顶向下，自底向上
####
class TreeNode:
    def __init__(self, value):
        self.value = value
        self.left = left
        self.right = right

class BinaryTree:
    def __init__(self, root_value):
        self.root = TreeNode(root_value)

    '''
    DFS
    '''
    def dfs(self, root_value):
        return self._dfs_visit(self.root, [])
    
    def _dfs_visit(self, node, path):
        if node:
            path.append(node.value)
            self._dfs_visit(node.left, path)
            self._dfs_visit(node.right, path)
        return path
        
    '''
    自顶向下
    '''
    def top_down(self):
        return self._top_down_visit(self.root, [])
    
    def _top_down_visit(self, node, path):
        if node:
            path.append(node.value)
            self._top_down_visit(node.left, path)
            self._top_down_visit(node.right, path)
        return path
    
    '''
    自底向上
    '''
    def buttom_up(self):
        return self._buttom_up_visit(self.root, [])
    
    def _buttom_up_visit(self, node, path):
        if node:
            self._buttom_up_visit(node.left, path)
            self._buttom_up_visit(node.right, path)
            path.append(node.value)
        return path
    

####
### DFS
####
def dfs(root):
    if not root:
        return []
    return [root.value] + dfs(root.left) + dfs(root.right)

####
### BFS
####
def bfs(root):
    if not root:
        return []
    
    queue = [root]
    res = []
    while queue:
        node = queue.pop(0)
        res.append(node)
        if node.left:
            queue.append(node.left)
        if node.right:
            queue.append(node.right)
    return res

####
### 自顶向下: 类似于DFS的前序遍历，从根节点向下遍历到叶节点
####
def top_down(root, path=[]):
    if not root:
        return []
    path.append(root.value)
    left_path = top_down(root.left, path.copy())
    right_path = top_down(root.right, path.copy())
    return [path] + left_path + right_path

####
### 自底向上: 从叶节点开始，逐步向上遍历到根节点，类似于DFS的后序遍历
####
def buttom_up(root):
    if not root:
        return []
    left_path = buttom_up(root.left)
    right_path = buttom_up(root.right)
    return left_path + right_path + [[root.value] ]

# ####
# ### 红黑树简化版
# ####
# class Node:
#     def __init__(self, data, is_red = True, left = None, right = None, parent = None):
#         self.data = data
#         self.is_red = is_red
#         self.left = left
#         self.right = right
#         self.parent = parent

# class RedBlackTree:
#     def __init__(self):
#         self.root = None

#     def insert(self, data):
#         new_node = Node(data)
#         if self.root is None:
#             self.root = new_node
#             self.root.is_red = False
#         else:
#             self._insert(self.root, new_node)
#             self._fix_insert(new_node)
    
#     def _insert(self, current, new_node):
#         if new_node.data < current.data:
#             if current.left is None:
#                 current.left = new_node
#                 new_node.parent = current
#             else:
#                 self._insert(current.left, new_node)
#         else:
#             if current.right is None:
#                 current.right = new_node
#                 new_node.parent = current
#             else:
#                 self._insert(current.right, new_node)

#     def _fix_insert(self, node):
#         pass

####
### 红黑树完整版
"""
每个节点要么是红色，要么是黑色。
根节点是黑色。
所有叶子（NIL）节点是黑色的。
每个红色节点的两个子节点都是黑色的（即不能有两个连续的红色节点）。
从任何节点到其每个叶子节点的所有路径都包含相同数量的黑色节点。
"""
####
class Node:
    def __init__(self, data, is_red=True, left=None, right=None, parent=None):
        self.data = data
        self.is_red = is_red
        self.left = left if left else None
        self.right = right if right else None
        self.parent = parent

class RedBlackTree:
    def __init__(self):
        self.NIL = Node(data=None, is_red=False)
        self.root = self.NIL

    def insert(self, data):
        new_node = Node(data, left=self.NIL, right=self.NIL)
        if self.root == self.NIL:
            self.root = new_node
        else:
            self._insert(self.root, new_node)
        self._fix_insert(new_node)
        self.root.is_red = False

    def _insert(self, current, new_node):
        if new_node.data < current.data:
            if current.left == self.NIL:
                current.left = new_node
                new_node.parent = current
            else:
                self._insert(current.left, new_node)
        else:
            if current.right == self.NIL:
                current.right = new_node
                new_node.parent = current
            else:
                self._insert(current.right, new_node)

    def _fix_insert(self, node):
        while node != self.root and node.parent.is_red:
            if node.parent == node.parent.parent.left:
                uncle = node.parent.parent.right
                if uncle.is_red:
                    uncle.is_red = False
                    node.parent.is_red = False
                    node.parent.parent.is_red = True
                    node = node.parent.parent
                else:
                    if node == node.parent.right:
                        node = node.parent
                        self._left_rotate(node)
                    node.parent.is_red = False
                    node.parent.parent.is_red = True
                    self._right_rotate(node.parent.parent)
            else:
                uncle = node.parent.parent.left
                if uncle.is_red:
                    uncle.is_red = False
                    node.parent.is_red = False
                    node.parent.parent.is_red = True
                    node = node.parent.parent
                else:
                    if node == node.parent.left:
                        node = node.parent
                        self._right_rotate(node)
                    node.parent.is_red = False
                    node.parent.parent.is_red = True
                    self._left_rotate(node.parent.parent)

    def _left_rotate(self, x):
        y = x.right
        x.right = y.left
        if y.left != self.NIL:
            y.left.parent = x
        y.parent = x.parent
        if x.parent == self.NIL:
            self.root = y
        elif x == x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left = x
        x.parent = y

    def _right_rotate(self, y):
        x = y.left
        y.left = x.right
        if x.right != self.NIL:
            x.right.parent = y
        x.parent = y.parent
        if y.parent == self.NIL:
            self.root = x
        elif y == y.parent.right:
            y.parent.right = x
        else:
            y.parent.left = x
        x.right = y
        y.parent = x
        
####
### B Tree:
####
class BTreeNode:
    def __init__(self, min_degree, leaf):
        self.min_degree = min_degree
        self.leaf = leaf
        self.keys = []
        self.children = []

class BTree:
    def __init__(self, min_degree):
        self.root = BTreeNode(min_degree, True)
        self.min_degree = min_degree

    def insert(self, key):
        root = self.root
        if len(root.keys) == 2 * self.min_degree - 1:
            temp = BTreeNode(self.min_degree, False)
            self.root = temp
            temp.children.insert(0, root)
            self.split_child(temp, 0)
            self.insert_non_full(temp, key)
        else:
            self.insert_non_full(root, key)

    def insert_non_full(self, node, key):
        i = len(node.keys) - 1
        if node.leaf:
            node.keys.append(None)
            while i >= 0 and key < node.keys[i]:
                node.keys[i + 1] = node.keys[i]
                i -= 1
            node.keys[i + 1] = key
        else:
            while i >= 0 and key < node.keys[i]:
                i -= 1
            i += 1
            if len(node.children[i].keys) == 2 * self.min_degree - 1:
                self.split_child(node, i)
                if key > node.keys[i]:
                    i += 1
            self.insert_non_full(node.children[i], key)

    def split_child(self, parent, i):
        min_degree = self.min_degree
        new_node = BTreeNode(min_degree, parent.children[i].leaf)
        t = parent.children[i]
        new_node.keys = t.keys[min_degree:(2 * min_degree - 1)]
        t.keys = t.keys[:min_degree - 1]
        if not t.leaf:
            new_node.children = t.children[min_degree:(2 * min_degree)]
            t.children = t.children[:min_degree]
        parent.children.insert(i + 1, new_node)
        parent.keys.insert(i, t.keys[min_degree - 1])

btree = BTree(3)
for k in [10, 20, 4, 6, 12, 33, 7, 16]:
    btree.insert(k)

####
### 双指针
####
    """
    在有序数组中找到两个数，使得它们的和等于一个特定的目标数, 返回这两个数的索引
    找出数组中的两个数，使它们的和为特定值（如上述示例）。
    合并两个有序数组或链表。
    快速排序中的分区操作。
    寻找链表中的环。
    在不允许使用额外空间的条件下移除特定元素。
    """
def two_pointer_technique(arr, target):
    left, right = 0, len(arr) - 1
    while left < right:
        current_sum = arr[left] + arr[right]
        if current_sum == target:
            return left, right
        elif current_sum < target:
            left += 1
        else:
            right -= 1
    return None


####
### 滑动窗口
####
def sliding_window(arr, k):
    if not arr or k == 0:
        return []
    
    result = []
    window = []

    for i in range(len(arr)):
        # 如果窗口头部的元素已不在窗口中（超出范围），则将其移出
        if window and window[0] <= i - k:
            window.pop(0)
            print("window.pop(0):", window)

        # 保持窗口元素递减
        while window and arr[i] > arr[window[-1]]:
            window.pop()
            print("window.pop():", window)

        window.append(i)
        print("window:", window)

        # 当窗口大小达到 k 时，记录当前窗口的最大值
        if i >= k - 1:
            result.append(arr[window[0]])
            print("window[0]:", window[0])
            print("arr[window[0]]:", arr[window[0]])
            print("result:", result)
        print("******")
    return result

# 测试滑动窗口技术
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
result = sliding_window(arr, k)
result

###
def minWindow(s, t):
    freq_t = {}
    for char in t:
        freq_t[char] = freq_t.get(char, 0) + 1
    
    left, right = 0, 0
    min_len = float('inf')
    min_len_start = 0
    required_chars = len(freq_t)
    formed_chars = 0
    window_chars = {}
    
    while right < len(s):
        char = s[right]
        
        if char in freq_t:
            window_chars[char] = window_chars.get(char, 0) + 1
            if window_chars[char] == freq_t[char]:
                formed_chars += 1
        
        while formed_chars == required_chars and left <= right:
            if right - left + 1 < min_len:
                min_len = right - left + 1
                min_len_start = left
            
            char = s[left]
            if char in freq_t:
                window_chars[char] -= 1
                if window_chars[char] < freq_t[char]:
                    formed_chars -= 1
            
            left += 1
        
        right += 1
    
    if min_len == float('inf'):
        return ""
    
    return s[min_len_start:min_len_start+min_len]


####
### prefix tree 前缀树
####
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end_of_word = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.is_end_of_word = True

    def search(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                return False
            node = node.children[char]
        return node.is_end_of_word
    
    def starts_with(self, prefix):
        node = self.root
        for char in prefix:
            if char not in node.children:
                return False
            node = node.children[char]
        return True

# 测试前缀树
trie = Trie()
trie.insert("apple")
trie.insert("app")
trie.insert("apricot")
trie.insert("banana")

search_result = trie.search("apple")  # 应返回 True
prefix_result = trie.starts_with("ap")  # 应返回 True

search_result, prefix_result


####
### Queue
####
class Queue:
    def __init__(self):
            self.items = []

    def enqueue(self, item):
        self.items.insert(0, item)
    
    def dequeue(self):
        return self.items.pop() if self.items else None
    
    def is_empty(self):
        return len(self.items) == 0
            
####
### Stack
####
class Stack:
    def __init__(self):
        self.stack = []
    
    def push(self, val):
        self.stack.append(val)

    def pop(self):
        return self.stack.pop() if not self.is_empty() else None
    
    def is_empty(self):
        return len(self.stack) == 0
    
    def peek(self):
        return self.stack[-1] if not self.is_empty() else None

# 20
def is_valid_parentheses(s):
    stack = []
    mapping = {")": "(", "}": "{", "]": "["}
    for char in s:
        if char in mapping:
            top_element = stack.pop() if stack else '#'
            if mapping[char] != top_element:
                return False
        else:
            stack.append(char)
    return not stack

# 739
def daily_temp(T):
    answer = [0] * len(T)
    stack = []
    for i in range(len(T)):
        while stack and T[i] > T[stack[-1]]:
            last_index = stack.pop()
            answer[last_index] = i - last_index
        stack.append(i)
    return answer

# 155
class MinStack:
    def __init__(self):
        self.stack = []
        self.min_stack = []

    def push(self, val):
        self.stack.append(val)
        if not self.min_stack or x <= self.min_stack[-1]:
            self.min_stack.append(val)

    def pop(self):
        val = self.stack.pop()
        if val == self.min_stack[-1]:
            self.min_stack.pop()
    
    def top(self):
        self.stack[-1]

    def getMin(self):
        return self.min_stack[-1]
    
####
### Heap 堆
####
import heapq

heap = []

heapq.heappush(heap, 4)
print("heap:", heap)
heapq.heappush(heap, 1)
print("heap:", heap)
heapq.heappush(heap, 3)
print("heap:", heap)
heapq.heappush(heap, 11)
print("heap:", heap)

print(heapq.heappop(heap))  # 弹出最小元素
print(heapq.heappop(heap))  # 弹出最小元素
print(heapq.heappop(heap))  # 弹出最小元素
print(heapq.heappop(heap))  # 弹出最小元素

# 23
import heapq
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def merge_k_lists(lists):
        min_heap = []
        # 建立一个最小堆，并保持堆的大小为链表数目
        for l in lists:
            while l:
                heapq.heappush(min_heap, l.val)
                l = l.next

        dummy = ListNode(None)
        current = dummy
        # 从堆中依次取出元素，构建新链表
        while min_heap:
            min_val = heapq.heappop(min_heap)
            current.next = ListNode(min_val)
            current = current.next

        return dummy.next            

# 295
import heapq
class MedianFinder:
    def __init__(self) -> None:
        self.min_heap = []
        self.max_heap = []

    def addNum(self, num):
        heapq.heappush(self.max_heap, -num)
        heapq.heappush(self.min_heap, -heapq.heappop(self.max_heap))
        
        # 保持两个堆平衡
        if len(self.min_heap) > len(self.max_heap):
            heapq.heappush(self.max_heap, -heapq.heappop(self.min_heap))

    def findMedian(self):
        if len(self.min_heap) == len(self.max_heap):
            return (self.min_heap[0] - self.max_heap[0]) / 2.0
        else:
            return -self.max_heap[0]

# 973
def k_closest(points, k):
    max_heap = []
    for (x, y) in points:
        dist = -(x ** 2 + y ** 2)
        if len(max_heap) < k:
            heapq.heappush(max_heap, (dist, x, y))
        else:
            heapq.heappushpop(max_heap, (dist, x, y))
    return [(x, y) for (dist, x, y) in max_heap]
    
# K个最接近的点
closest_points = k_closest([(1, 3), (-2, 2)], 1)
closest_points

####
### 单向链表
####
class ListNode:
    def __init__(self, value):
        self.value = value
        self.next = None

class LinkedList:
    def __init__(self):
        self.head = None
    
    def append(self, value):
        if not self.head:
            self.head = ListNode(value)
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = ListNode(value)

####
### 使用单向链表实现一个简单的栈结构
####
class Stack:
    def __init__(self):
        self.linked_list = LinkedList()

    def push(self, value):
        new_node = ListNode(value)
        new_node.next = self.linked_list.head
        self.linked_list.head = new_node
    
    def pop(self):
        if self.linked_list.head:
            value = self.linked_list.head.value
            self.linked_list.head = self.linked_list.head.next
            return value
        return None

####
### 双向链表
####
class DoubleListNode:
    def __init__(self, value):
        self.value = value
        self.next = None
        self.prev = None

class DoublyLikedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def append(self, value):
        new_node = DoubleListNode(value)
        if not self.head:
            self.head = self.tail = new_node
        else:
            self.tail.next = new_node
            new_node.prev = self.tail
            self.tail = new_node

####
### 使用双向链表实现一个简单的栈结构
####
class Deque:
    def __init__(self):
        self.doubly_linked_lsit = DoublyLikedList()
    
    def append(self, value):
        self.doubly_linked_lsit.append(value)

    def append_left(self, value):
        new_node = DoubleListNode(value)
        new_node.next = self.doubly_linked_list.head
        if self.doubly_linked_list.head:
            self.doubly_linked_list.head.prev = new_node
        self.doubly_linked_list.head = new_node
        if not self.doubly_linked_list.tail:
            self.doubly_linked_list.tail = new_node
        
    def pop(self):
        if self.doubly_linked_list.tail:
            value = self.doubly_linked_list.tail.value
            self.doubly_linked_list.tail = self.doubly_linked_list.tail.prev
            if self.doubly_linked_list.tail:
                self.doubly_linked_list.tail.next = None
            return value
        return None
    
    def pop_left(self):
        if self.doubly_linked_list.head:
            value = self.doubly_linked_list.head.value
            self.doubly_linked_list.head = self.doubly_linked_list.head.next
            if self.doubly_linked_list.head:
                self.doubly_linked_list.head.prev = None
            return value
        return None
    
####
### 递归实现 - 计算斐波那契数列
####
def fib(n):
    if n <= 1:
        return n
    else:
        return fib(n-1) + fib(n-2)
    
####
### 回溯实现 - N皇后问题
### 在4x4的棋盘上放置4个皇后，使得她们相互不能攻击
####
# def solve_n_queens(n):
#     def is_safe(row, col):
#         for i in range(row):
#             if board[i] == col or \
#                board[i] - i == col - row or \
#                board[i] + i == col + row:
#                 return False
#         return True
    
#     def backtrack(row):
#         if row == n:
#             result.append(board[:])
#             return
#         for col in range(n):
#             if is_safe(row, col):
#                 board[row] = col
#                 backtrack(row + 1)

#     result = []
#     board = [-1] * n
#     backtrack(0)
#     return result
    
####
### 动态规划实现 - 最大子数组和
### 在给定数组 [-2,1,-3,4,-1,2,1,-5,4] 中找到具有最大和的连续子数组
####
def max_sub_array(nums):
    if not nums:
        return 0
    max_sum = current_sum = nums[0]
    for num in nums[1:]:
        current_sum = max(num, current_sum + num)
        max_sum = max(max_sum, current_sum)
    return max_sum

####
### Graph
####
class Graph:
    def __init__(self) -> None:
        self.graph = {}

    def add_edge(self, u, value):
        if u in self.graph:
            self.graph[u].append(value)
        else:
            self.graph[u] = value










































