#!/bin/bash
#Author: Vivek Gawande
#Script to tell whether a number is an armstrong number

read -p "Enter a number: " n

temp=$n

while [ $n -ne 0 ]
do
  digit=$(( $n % 10 ))
  n=$(( $n / 10 ))
  ((sum+=$(($digit*$digit*$digit))))
done

if [ $sum -eq $temp ]
then
  echo "$temp is an Armstrong number"
else
  echo "$temp is not an Armstrong number"
fi
