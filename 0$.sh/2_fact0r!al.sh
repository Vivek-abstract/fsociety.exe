#!/bin/bash
#Author: Vivek Gawande
#Script to calculate the factorial of a number less than 25.

read -p "Enter a number: " n

if [ $n -lt 0 ]
then
  echo Factorial of a negative number doesn\'t exist
  exit
fi

fact=1
for((i=1;i<=$n;i++))
do
  ((fact*=i))
done

echo "$n! = $fact"
