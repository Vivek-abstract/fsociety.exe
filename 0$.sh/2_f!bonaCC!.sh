#!/bin/bash
#Author: Vivek Gawande
#Script to print fibonacci series till n terms

read -p "Enter number of terms in fibonacci series: " n

if [ $n -le 0 ]
then
  echo Invalid input
fi

if [ $n -ge 1 ]
then
  echo 0 
fi

if [ $n -ge 2 ]
then
  echo 1
fi

a=0
b=1
count=2

while [ $count -lt $n ]
do
  c=$(( $a+$b ))
  echo $c 
  a=$b
  b=$c
  ((count++))
done
