#!/bin/bash
#Author: Vivek Gawande
#Menu driven calculator

read -p "Enter number 1: " m
read -p "Enter number 2: " n

echo "1. Addition"
echo "2. Subtraction"
echo "3. Multiplication"
echo "4. Division"

read -p "Enter choice: " choice

case "$choice" in
  1)
    echo $m + $n = $(($m + $n))
  ;;
  2)
    echo $m - $n = $(($m - $n))
  ;;
  3)
    echo $m \* $n = $(($m * $n))
  ;;
  4)
    echo $m / $n = $(($m / $n))
  ;;
esac
