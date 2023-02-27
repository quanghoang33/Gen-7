package main

import "fmt"

type NumArray struct {
	nums []int
}

func Constructor(nums []int) NumArray {
	a := NumArray{}
	a.nums = nums
	for i, num := range nums {
		if i != 0 {
			a.nums[i] = num + a.nums[i-1]
		}
	}
	return a
}

func (this *NumArray) SumRange(left int, right int) int {
	if left == 0 {
		return this.nums[right]
	}
	return this.nums[right] - this.nums[left-1]
}

func main() {
	n := Constructor([]int{-2, 0, 3, -5, 2, -1})
	fmt.Println("0,2", n.SumRange(0, 2))
	fmt.Println("2,5", n.SumRange(2, 5))
	fmt.Println("0,5", n.SumRange(0, 5))
}
