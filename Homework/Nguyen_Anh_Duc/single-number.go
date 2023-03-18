package main

import "fmt"

func singleNumber(nums []int) int {
	var xor = 0
	for _, num := range nums {
		xor ^= num
	}
	return xor
}

func main() {
	fmt.Println(singleNumber([]int{2, 2, 1}))
	fmt.Println(singleNumber([]int{4, 1, 2, 1, 2}))
}
