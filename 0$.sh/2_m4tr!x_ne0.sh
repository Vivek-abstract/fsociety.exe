#!/bin/bash
#Author: Vivek Gawande
#Script to multiply two matrices r1xc1 and r2xc2.
#Proceed with caution

declare -A a
declare -A b
declare -A c

read -p "Enter number of rows in A: " r1
read -p "Enter number of columns in A: " c1
read -p "Enter number of rows in B: " r2
read -p "Enter number of columns in B: " c2

if [ $c1 -ne $r2 ]
then
  echo Invalid input. The two matrices cannot be multiplied.
  exit
fi

echo "Enter elements of matrix A: "
for((i=0;i<r1;i++))
do
  for((j=0;j<c1;j++))
  do
    read a[$i,$j]
  done
done

echo "Enter elements of matrix B: "
for((i=0;i<r2;i++))
do
  for((j=0;j<c2;j++))
  do
    read b[$i,$j]
  done
done

echo "The matrix A is: "
for((i=0;i<r1;i++))
do
  for((j=0;j<c1;j++))
  do
    echo -n "${a[$i,$j]} "
  done
  echo
done

echo "The matrix B is: "
for((i=0;i<r2;i++))
do
  for((j=0;j<c2;j++))
  do
    echo -n "${b[$i,$j]} "
  done
  echo
done

for((i=0;i<r1;i++))
do
  for((j=0;j<c1;j++))
  do
    c[$i,$j]=0
    for((k=0;k<c1;k++))
    do
      ((c[$i,$j]+=$((a[$i,$k]*b[$k,$j]))))
    done
  done
done

echo "The answer of AxB is: "
for((i=0;i<r1;i++))
do
  for((j=0;j<c2;j++))
  do
    echo -n "${c[$i,$j]} "
  done
  echo
done

