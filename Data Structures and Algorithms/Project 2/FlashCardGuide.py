class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

    def get_data(self):
        return self.data

    def get_next(self):
        return self.next

    def set_data(self, data):
        self.data = data

    def set_next(self, next):
        self.next = next

class LinkedList:
    def __init__(self):
        self.head = None

    # return True is the list is empty, False otherwise
    def is_empty(self):
       return self.head == None

    def push(self, newdata):
        NewNode = Node(newdata)
        if self.head is None:
            self.head = NewNode
            return
        laste = self.head
        while(laste.next):
            laste = laste.next
        laste.next=NewNode
# Function to remove node
    def pop(self, Removekey):

        HeadVal = self.head

        if (HeadVal is not None):
            if (HeadVal.data == Removekey):
                self.head = HeadVal.next
                HeadVal = None
                return

        while (HeadVal is not None):
            if HeadVal.data == Removekey:
                break
            prev = HeadVal
            HeadVal = HeadVal.next

        if (HeadVal == None):
            return

        prev.next = HeadVal.next

        HeadVal = None

    def print(self):
        printv = self.head
        while (printv):
            print(printv.data),
            printv = printv.next

# creating instance of LinkedList class
flashcard = LinkedList()
#Creating variables of the flashcard nodes
first_node = Node("FLASHCARD HINT: does not need to occupy contiguous memory")
flashcard.head = first_node
second_node = Node("FLASHCARD HINT: has a 'front' and 'back' ")
third_node = Node("FLASHCARD HINT: has a 'top' and 'bottom' ")
first_node.next = second_node
second_node.next = third_node

correctcounter = 2
correctcounter2 = 2
correctcounter3 = 2

while flashcard.is_empty() is False:

    # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
    print(first_node.data)
    answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST ")

    # if statement for correct answer. If correct add 1 to counter, If counter = 2, delete the data from the node
    if answer == "L" or answer == "l":
        correctcounter = correctcounter - 1
        print ("Good Job!")
        print("\n")
        if correctcounter == 0:
            flashcard.head = second_node
            flashcard.pop(first_node.data)
    else:
        print("Try again next time :(")
        print("\n")
        first_node.get_next()

    # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
    print(second_node.data)
    answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST: ")

    # if statement for correct answer. If correct add 1 to counter, If counter = 2, delete the data from the node
    if answer == "Q" or answer == "q":
        correctcounter2 = correctcounter2 - 1
        print ("Good Job!")
        print("\n")
        if correctcounter2 == 0:
            flashcard.head = third_node
            flashcard.pop(second_node.data)
    else:
        print("Try again next time :(")
        print("\n")
        second_node.get_next()

    # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
    print(third_node.data)
    answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST: ")

    # if statement for correct answer. If correct add 1 to counter, If counter = 2, delete the data from the node
    if answer == "S" or answer == "s":
        correctcounter3 = correctcounter3 - 1
        print ("Good Job!")
        print("\n")
        if correctcounter3 == 0:
            flashcard.pop(third_node.data)
    else:
        print("Try again next time :(")
        print("\n")
        third_node.get_next()

    if flashcard.is_empty() is True:
        print("Well done! You have answered them all correctly, and twice!")
        quit()
#flashcard = myll()
#while(1):
    #print("1. Add flash card")
    #print("2. remove flash card")
    #print("3. print flash deck")
    #print("4. Exit")
    #op = int(input());
    #if(op == 1):
        #print("Enter data : ")
        #data = input();
        #flashcard.push(data)
    #if(op == 2):
        #print("Enter data : ")
        #data = input();
        #flashcard.pop(data)
    #if(op == 3):
        #flashcard.print();
    #if(op == 4):
        #break;