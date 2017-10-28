#!/bin/bash
#Author: Vivek Gawande
#Script to find the permutation nPr value given n and r

read -p "Enter n: " n
read -p "Enter r: " r

if [ $n -lt $r ]
then
  echo Invalid input
  exit
fi

factorial() {
  if [ $1 -lt 0 ]
  then
    echo Factorial of a negative number doesn\'t exist
    exit
  fi

  fact=1
  for((i=1;i<=$1;i++))
  do
    ((fact*=i))
  done 
  echo $fact
}

#nPr = n!/(n-r)!
num=$(factorial $n)
temp=$(($n-$r))
den=$(factorial $temp)

ans=$(($num/$den))
echo "$n P $r = $ans"
