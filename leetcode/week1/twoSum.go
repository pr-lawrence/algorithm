package week1

import "fmt"

func main() {
	fmt.Println("hello world")

	nums := []int{2, 7, 11, 15};
	result := twoSum(nums, 9);
	fmt.Println(result)
}

func twoSum(nums []int, target int) []int {
	for i := 0; i < len(nums); i++ {
		for j := i + 1 ; j < len(nums); j++ {
			if nums[i] + nums[j] == target {
				return []int{i ,j}
			}
		}
	}

	return []int{0, 0}
}
