#!/bin/bash
#Author: Vivek Gawande
#Script to check if number/string is palindrome


read -p "Enter number/string: " s

reverse=$(echo $s | rev) 

if [ $s = $reverse ]
then
  echo "$s is a Palindrome"
else
  echo "$s is not a palindrome"
fi
