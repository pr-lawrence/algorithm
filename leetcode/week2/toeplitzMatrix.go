package main

import "fmt"

func main() {
	matrix := [][]int{
		{1, 2, 3, 4},
		{5, 1, 2, 3},
		{9, 5, 1, 2},
	}

	fmt.Println(isToeplitzMatrix(matrix))
}

func isToeplitzMatrix(matrix [][]int) bool {
	if len(matrix[0]) <= 1 {
		return true;
	}

	res := true;

	for i := 1; i < len(matrix) ; i++ {
		for j := 1; j < len(matrix[i]); j++ {
			if(matrix[i-1][j-1] != matrix[i][j]) {
				return false;
			}
		}
	}

	return res;
}
