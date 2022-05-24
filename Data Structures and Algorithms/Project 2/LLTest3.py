# Python program to delete a node from linked list

# Node class
class Node:

    # constructor to initialize node class
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

    # Function to initialize head
    def __init__(self):
        self.head = None

    # Function to insert a new node at the beginning
    def push(self, new_data):
        new_node = Node(new_data)
        new_node.next = self.head
        self.head = new_node

        # Given a reference to the head of a list and a key,

    # delete the first occurence of key in linked list
    def delete_node(self, key):

        # Store head node
        temp = self.head

        # If head node itself holds the key to be deleted
        if (temp is not None):
            if (temp.data == key):
                self.head = temp.next
                temp = None
                return

        # Search for the key to be deleted, keep track of the
        # previous node as we need to change 'prev.next'
        while (temp is not None):
            if temp.data == key:
                break
            prev = temp
            temp = temp.next

        # if key was not present in linked list
        if (temp == None):
            return

        # Unlink the node from linked list
        prev.next = temp.next

        temp = None

    # Utility function to print the linked LinkedList
    def printList(self):
        temp = self.head
        while (temp):
            print(" %d" % (temp.data)),
            temp = temp.next

    # return True if the list is empty, False otherwise
    def is_empty(self):
        return self.head is None

# Drivers

# creating instance of LinkedList class
flashcard = LinkedList()
# Creating variables of the flashcard nodes
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

    if correctcounter != 0:
        # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
        print(first_node.data)
        answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST ")

        # if statement for correct answer. If correct add 1 to counter, If counter = 2, delete the data from the node
        if answer == "L" or answer == "l":
            correctcounter = correctcounter - 1
            print ("Good Job!")
            print("\n")
            if correctcounter == 0:
                flashcard.delete_node(first_node.data)
        else:
            print("Try again next time :(")
            print("\n")
            first_node.get_next()

    else:
        continue

    if correctcounter2 != 0:
        # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
        print(second_node.data)
        answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST: ")

        # if statement for correct answer. If correct sub 1 from counter, If counter = 0, delete the data from the node
        if answer == "Q" or answer == "q":
            correctcounter2 = correctcounter2 - 1
            print ("Good Job!")
            print("\n")
            if correctcounter2 == 0:
                flashcard.delete_node(second_node.data)
        else:
            print("Try again next time :(")
            print("\n")
            second_node.get_next()
    else:
        continue
    if correctcounter3 != 0:
        # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
        print(third_node.data)
        answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST: ")

        # if statement for correct answer. If correct add 1 to counter, If counter = 2, delete the data from the node
        if answer == "S" or answer == "s":
            correctcounter3 = correctcounter3 - 1
            print ("Good Job!")
            print("\n")
            if correctcounter3 == 0:
                flashcard.delete_node(third_node.data)
        else:
            print("Try again next time :(")
            print("\n")
            third_node.get_next()
    else:
        continue

    if flashcard.is_empty() is True:
        print("Well done! You have answered them all correctly, and twice!")
        quit()