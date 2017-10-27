#!/bin/bash
#Author: Vivek Gawande
#Script to check if number/string is palindrome


read -p "Enter number/string: " s

temp=$s
reverse=$($s | rev) 
