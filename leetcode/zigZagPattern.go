package main

import (
	"strings"
	"fmt"
)

func main() {
	problem := "AB";
	//fmt.Println(convert(problem, 3))
	fmt.Println(convert(problem, 1))
	//fmt.Println("PAHNAPLSIIGYIR" == convert(problem, 3))
	//fmt.Println("PINALSIGYAHRPI" == convert(problem, 4))
}

func convert(s string, numRows int) string {

	if numRows == 1 {
		return s;
	} else {
		values := make([][]string, numRows)
		for i := range values {
			values[i] = make([]string, len(s))
		}

		climb := false
		j := 0
		i := 0
		for _, ch := range s {
			if( climb == false ){
				values[i][j] = string(ch)
				if( i >= numRows - 1) {
					climb = true
					i -= 1;
					j += 1;
				} else {
					i ++;
				}
			} else {
				values[i][j] = string(ch)
				if ( i <= 0 ) {
					climb = false
					i += 1;
				} else {
					i -= 1;
					j += 1;
				}
			}
		}

		res := ""
		for k := 0 ; k < numRows ; k++ {
			res += strings.Join(values[k][:], "")
		}

		return strings.Replace(res, " ", "", len(res))
	}
}
