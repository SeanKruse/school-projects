
#Program to accept string from user

.data
	prompt: .asciiz "Enter name: "
	message: .asciiz "\n Name is: "
	userInput: .space 20
	
.text
	#To prompt
	li $v0,4
	la $a0,prompt 
	syscall
	
	#To accept string from user 
	li $v0,8 
	la $a0,userInput
	li $a1,10		
	syscall
	
	# To Prompt the User
	li $v0, 4  #system call code for print_str
	la $a0, message  # address of string to print
	syscall
	li $v0, 4  #system call code for print_str
	la $a0, userInput  # address of string to print
	syscall