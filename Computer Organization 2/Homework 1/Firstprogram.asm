# My First Program

#Data Section
.data
	prompt: .asciiz "Enter the number:\n"
	myMessage: .asciiz "The number is:\n"
	
	
#code section
.text
	# To Prompt the User
	li $v0, 4  #system call code for print_str
	la $a0, prompt  # address of string to print
	syscall
	
	# To get value from user
	li $v0,5 #load user value
	syscall
	
	#Store the result in a temp reg
	move $t0,$v0
		

	# To Print Message
	li $v0, 4  #system call code for print_str
	la $a0, myMessage  # address of string to print
	syscall
	
	#To Print a number
	li $v0,1 # system call for int
	move $a0,$t0 
	syscall
	
		