```python

heap基础和性质
1. head(堆)是一个除了底层节点之外的二叉树
2.堆的每个元素都要大于 或者 小于它的其他孩子

3.(最大堆)
             12
          8     10
        6   4  7  9
        
4.(最小堆)
             2
          3       6
        6   4   7   9 
        
        
 
 5.基本操作: 添加一个新的元素, 找到最大值 和 删除最大值
 
 添加一个元素: 把这个元素添加为叶节点 然后把它移动到合适的位置
 
 删除最大元素: 把新的根和孩子对比 从来来移动位置
 
 6.heap[0]为最小的元素
 
 heap定义:
 heapq.heappush(heap, item) //item为增加的元素
 >> import heapq //直接import 
 >> h = [] //这里说明heap的实现方式为list 列表
 >> heapq.heappush(h, 2) // .heappush() 这个function
 >> h
 [2] //输出 
 
 heapq.heapify(list) //把列表转化为堆
 >> list = [1,2,3,5,1,5,8,9,6]
 >> heapq.heapify(list)
 >> list
 [1,1,3,5,2,5,8,9,6]
 
 
 heapq.heappop(heap) //删除最小值 因为heap[0]为最小的元素 所以删除的都是第一个元素
 >> list
 [1,1,3,5,2,5,8,9,6]
 >> heapq.headpop(list)
 1
 >> list
 [1,2,3,5,6,5,8,9] //输出
 
 heap.heapreplace(heap, heap.item) //删除最小的元素值 添加新的元素值
 >> list
 [1,2,3,5,6,5,8,9]
 >> heapq.heapreplace(list, 99)
 1
 >> list
 [2,5,3,9,6,5,8,99]
 
 heapq.nlargest(n, heap) //查询堆中最大的元素, n表示查询元素个数 即查询n个最大的元素个数
 >> list
 [3,5,5,9,6,6,8,99]
 >> heapq.nlargest(3, list)
 [99,9,8]
 
 heapq.nsmallest(n, heap) //查询堆中最小的元素
 >> list
 [3,5,5,9,6,6,8,99]
 >> heapq.nsamllest(3, list)
 [3,5,5]

```
