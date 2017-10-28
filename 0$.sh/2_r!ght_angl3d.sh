#!/bin/bash
#Author: Vivek Gawande
#Script to print a right angled pattern ¯\_(ツ)_/¯

read -p "Enter number of rows: " n

for((i=1;i<=n;i++))
do
  for((j=1;j<=i;j++))
  do
    echo -n "#"
  done
  echo
done
