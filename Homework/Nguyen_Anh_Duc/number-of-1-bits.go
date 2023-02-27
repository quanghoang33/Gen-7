package main

import "fmt"

func hammingWeight(num uint32) int {
	step := 0
	for num > 0 {
		num = num & (num - 1)
		step = step + 1
	}

	return step
}

func main() {
	a := []uint32{2, 3, 4, 6, 7}
	for _, m := range a {
		fmt.Println(m, " have ", hammingWeight(m))
	}
}
