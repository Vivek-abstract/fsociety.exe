#!/bin/bash
#Author: Vivek Gawande
#Script to check whether a number is prime

read -p "Enter a number: " n

if [ $n -le 1 ]
then
  echo "$n is not a prime number"
  exit
elif [ $n -eq 2 ]
then
  echo "$n is a prime number"
  exit
elif [ $(( $n % 2 )) -eq 0 ]
then
  echo "$n is not a prime number"
  exit
fi

for((i=3;i<=$n/2;i++))
do
  if [ $(($n%$i)) -eq 0 ]
  then
    echo "$n is not a prime number"
    exit
  fi
done

echo "$n is a prime number"
