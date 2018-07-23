package main

import (
	"sort"
	"strings"
	"strconv"
	"fmt"
)

func main() {

	times := []string{"23:59", "00:00"}

	fmt.Println(findMinDifference(times))
	
}

func findMinDifference(timePoints []string) int {
	sort.Strings(timePoints)
	timePoints = append(timePoints, timePoints[0])

	previousMin := 24 * 60
	for i := 0; i < len(timePoints) - 1; i ++ {
		diff := calcDiffTime(timePoints[i], timePoints[i+1])
		if previousMin > diff {
			previousMin = diff
		}
	}

	return previousMin;
}

func calcDiffTime(t1 string, t2 string) int {
	min1 := calcMinutes(strings.Split(t1, ":"))
	min2 := calcMinutes(strings.Split(t2, ":"))
	cal := min2 - min1;
	if cal < 0 {
		return cal + 1440;
	} else {
		return cal
	}
}

func calcMinutes(tarr []string) int {
	hour, _ := strconv.Atoi(tarr[0])
	min, _:= strconv.Atoi(tarr[1])

	return hour * 60 + min;
}