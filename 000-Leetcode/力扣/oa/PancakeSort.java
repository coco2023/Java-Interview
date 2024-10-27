package oa;

import java.util.*;

public class PancakeSort {
          public List<Integer> pancakeSort(int[] arr) {
                    List<Integer> result = new ArrayList<>();
                    int n = arr.length;

                    // 从大到小逐个定位最大值
                    for (int currSize = n; currSize > 1; currSize--) {
                              // 找到当前范围内的最大值索引
                              int maxIndex = findMax(arr, currSize);

                              // 如果最大值不在最后，则进行翻转
                              if (maxIndex != currSize - 1) {
                                        // 翻转到数组的开头
                                        flip(arr, maxIndex);
                                        result.add(maxIndex + 1);

                                        // 翻转到目标位置
                                        flip(arr, currSize - 1);
                                        result.add(currSize);
                              }
                    }

                    return result;
          }

          // 找到数组前n个元素中最大值的索引
          private int findMax(int[] arr, int n) {
                    int maxIndex = 0;
                    for (int i = 1; i < n; i++) {
                              if (arr[i] > arr[maxIndex]) {
                                        maxIndex = i;
                              }
                    }
                    return maxIndex;
          }

          // 翻转数组前k个元素
          private void flip(int[] arr, int k) {
                    int left = 0, right = k;
                    while (left < right) {
                              int temp = arr[left];
                              arr[left] = arr[right];
                              arr[right] = temp;
                              left++;
                              right--;
                    }
          }

          public static void main(String[] args) {
                    PancakeSort sorter = new PancakeSort();
                    int[] arr = { 3, 2, 4, 1 };
                    List<Integer> result = sorter.pancakeSort(arr);

                    System.out.println("Sorted array: ");
                    for (int num : arr) {
                              System.out.print(num + " ");
                    }

                    System.out.println("\nFlip sequence: " + result);
          }
}
