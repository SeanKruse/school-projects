#Arithmetic operations
.data
	prompt:.asciiz "Enter Number"
	d: .word 4 
	lbr: .asciiz "\n"
	

.text
	#Ask for 1st number
	
	li $v0,4
	la $a0,prompt
	syscall
	
	li $v0,5
	syscall
	move $t1,$v0
	
	#Ask for 2nd number
	
	#li $v0,4
	#la $a0,prompt
	#syscall
	
	#Load word
	lw $t2,d
	
	#li $v0,5
	#syscall
	#move $t2,$v0
	
	#Sum
	
	#Subtraction
	
	#subi $t0,$t1,4 #t1-t2
	#add $t0,$t1,d #t1 + d
	
	#multiplication
	
	#mul $t0,$t1,10
	
	#Division
	
	div $t0,$t1,$t2	
	
	#Display
	
	li $v0,1
	mflo $a0 #Q
	syscall
	
	li $v0,4
	la $a0, lbr
	syscall
	
	li $v0,1
	mfhi $a0 #R
	syscall
	
	