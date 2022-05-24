#Expression Z=A + (B/C) - 2
#Sean Kruse, 08/31/20, Time Taken: 1 hour, Attempt 2
.data
	prompt:.asciiz "Enter the First Number: "
	prompttwo:.asciiz "Enter the Second Number: "
	promptthree:.asciiz "Enter the Third Number: "
	answerprompt:.asciiz "Your answer from the expression is: "
	
	
.text
	#Ask for 1st number
	
	li $v0,4
	la $a0,prompt
	syscall
	#Accepting A
	li $v0,5
	syscall
	move $t1,$v0 #First value in t1
	
	#Ask for 2nd number
	
	li $v0,4
	la $a0,prompttwo
	syscall
	#Accepting B
	li $v0,5
	syscall
	move $t2,$v0 #Second value in t2
	
	#Ask for 3rd number
	
	li $v0,4
	la $a0,promptthree
	syscall
	#Accepting C
	li $v0,5
	syscall
	move $t3,$v0 #Third value in t3
	
	#Divide B/C
	div $t4,$t2, $t3
	#Add A to result
	add $t5,$t1,$t4
	#Subtract constant 2
	subi $t6,$t5,2
	
	#Display
	
	#Division
	li $v0,1
	move $a0,$t4
	#Addition
	li $v0,1
	move $a0,$t5
	#Subtraction
	li $v0,4 
	la $a0, answerprompt
	syscall
	li $v0,1
	move $a0,$t6
	syscall
	
	#So far the most difficult section has been displaying the correct value, I'm making a mistake somwhere with the display. #Now the biggest problem I have is thinking about how The remainder is figured into the arithmetic.
	#Errors so far...
	#Error in C:\Users\seank\Desktop\School\Fall 2020\Comp Org 2\Sean1.asm line 47 column 2: "subi": Too few or incorrectly formatted operands. Expected: subi $t1,$t2,-100
	#Assemble: operation completed with errors.
	
	
	
	
	
