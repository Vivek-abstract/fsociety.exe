#!/bin/bash
#Author: Vivek Gawande
#Script to find gcd and lcm of 2 numbers

read -p "Enter a number: " m
read -p "Enter another number: " n

a=$m
b=$n

if [ $m -lt $n ]
then
  t=$m
  m=$n
  n=$t
fi

while [ $n -ne 0 ]
do
  t=$n
  n=$(( $m%$n ))
  m=$t
  # gcd(a,b) = gcd(b,a%b)
done

echo "GCD = $m"
# LCM = m*n/gcd(m,n)
echo "LCM = $(( ($a*$b)/$m ))"
