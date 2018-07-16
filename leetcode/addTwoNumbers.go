package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func main() {
	//num1 := ListNode{2, &ListNode{4, &ListNode{3, nil}}}
	//num2 := ListNode{5, &ListNode{6, &ListNode{4, nil}}}

	num1 := ListNode{3, &ListNode{7, nil}}
	num2 := ListNode{9, &ListNode{2, nil}}

	res := addTwoNumbers(&num1, &num2)

	fmt.Println(getNumber(res, 1, 0))
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	dummy := &ListNode{0, nil}
	return calc(l1, l2, 0, dummy, dummy)
}

func calc(l1 *ListNode, l2 *ListNode, carry int, tail *ListNode, head *ListNode) *ListNode {
	if l1 == nil && l2 == nil {
		if carry != 0  {
			tail.Next = &ListNode{1, nil};
		}
		return head.Next
	} else if l1 == nil && l2 != nil {
		tail.Next = &ListNode{(l2.Val + carry) % 10, nil};
		return calc(nil, l2.Next, (l2.Val + carry)/10, tail.Next, head)
	} else if l1 != nil && l2 == nil {
		tail.Next = &ListNode{(l1.Val + carry) % 10, nil};
		return calc(l1.Next, nil, (l1.Val + carry)/10, tail.Next, head)
	} else {
		tail.Next = &ListNode{(l1.Val + l2.Val + carry) % 10, nil};
		return calc(l1.Next, l2.Next, (l1.Val+l2.Val+ carry)/10, tail.Next, head)
	}
}

func getNumber(li *ListNode, exponent int, acc int) int {
	if li == nil {
		return acc
	} else {
		return getNumber(li.Next, exponent*10, li.Val*exponent+acc);
	}
}

/*
func main() {
	num1 := ListNode{2, &ListNode{4, &ListNode{3, nil}}}
	num2 := ListNode{5, &ListNode{6, &ListNode{4, nil}}}

	res := addTwoNumbers(&num1, &num2)

	fmt.Println(getNumber(res, 1, 0))
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	sum := getNumber(l1, 1, 0)+getNumber(l2, 1, 0)
	if sum == 0 {
		return &ListNode{0, nil}
	} else {
		return makeNode(sum, nil, nil)
	}
}

func getNumber(li *ListNode, exponent int, acc int) int {
	if li == nil {
		return acc
	} else {
		return getNumber(li.Next, exponent*10, li.Val*exponent+acc);
	}
}

// 807
func makeNode(i int, tail *ListNode, acc *ListNode) *ListNode {
	if i <= 0 {
		return acc;
	} else {
		if acc == nil {
			res := &ListNode{i % 10, nil}
			return makeNode(i/10, res, res)
		} else {
			res := &ListNode{i % 10, nil}
			tail.Next = res
			return makeNode(i/10, res, acc)
		}
	}
}
*/
