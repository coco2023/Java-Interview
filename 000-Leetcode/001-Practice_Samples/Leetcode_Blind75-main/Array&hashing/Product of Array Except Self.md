
```python

##solution1: TLE

class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        #brutal force
        result = []

        left = [1]*len(nums)
        right = [1]*len(nums)

        #left
        for i in range(len(left)):
            currlist = nums[0:i]
            curval = 1
            for j in range(len(currlist)):
                curval = currlist[j]*curval
            
            left[i] = curval
        #print(left)
        #right
        for k in range(len(right)):
            currlist = nums[k+1:]
            curval = 1
            for l in range(len(currlist)):
                curval = currlist[l]*curval
            right[k] = curval
        
        #print(right)
        
        for x,y in zip(left,right):
            cur = x*y
            result.append(cur)
        
        return result


##solution2: time:o(n) space:o(1)
#note: using right keeps track of multiplication

class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:

        #divide into two parts
        #first part is left hand side multiplication
        #second part is right hand side multiplication

        result = [1]*len(nums)
        
        for i in range(len(nums)):
            if i == 0:
                continue
            
            result[i] = result[i-1]*nums[i-1]
        
        #original [1,2,3,4]
        #now we have [1,1,2,6]

        
        #need [24,12,8,6]
        right = 1
        for j in reversed(range(len(nums))):
            result[j] = result[j]*right
            right = nums[j]*right
        
        return result








```
