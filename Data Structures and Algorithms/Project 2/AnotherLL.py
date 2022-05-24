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

    # constructor initializes the list to be empty
    def __init__(self):
        self.head = None

    def insert(self, data):
        new_node = Node(data)
        new_node.set_next(self.head)
        self.head = new_node

    def delete(self, data):
        current = self.head
        previous = None
        found = False
        while current and found is False:
            if current.get_data() == data:
                found = True
            else:
                previous = current
                current = current.get_next()
        if current is None:
            raise ValueError("Data not in list")
        if previous is None:
            self.head = current.get_next()
        else:
            previous.set_next(current.get_next())

    # return the number of Nodes currently in the list
    def size(self):
        current = self.head
        count = 0
        while current is not None:
            count += 1
            current = current.getNext()
        return count

    # return True if the list is empty, False otherwise
    def is_empty(self):
        return self.head is None


def traverse_list():
    # creating instance of LinkedList class
    flashcard = LinkedList()

    # Creating variables of the flashcard nodes
    first_node = Node("FLASHCARD HINT: an ordered collection of items that can be implemented as array-based or linked")
    second_node = Node("FLASHCARD HINT: stores items in a 'first in / first out' manner")
    third_node = Node("FLASHCARD HINT: store items in a 'last in / first out' manner")
    flashcard.head = first_node
    first_node.next = second_node
    second_node.next = third_node

    correctcounter = 2
    correctcounter2 = 2
    correctcounter3 = 2

    while flashcard.is_empty() is False:
        if flashcard.head.get_next(second_node):
            
            # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
            print(first_node.data)
            answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST ")

            # if statement for correct answer. If correct add 1 to counter, If counter = 2, delete the data from the node
            if answer == "L" or answer == "l":
                correctcounter = correctcounter - 1
                print("Good Job!")
                print("\n")
                if correctcounter == 0:
                    flashcard.delete(first_node.data)
                    flashcard.head.set_next(second_node)
            else:
                print("Try again next time :(")
                print("\n")
                first_node.set_next()

            # If else for node one to save correct answers to counter. If counter reaches two the node is deleted
            print(second_node.data)
            answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST: ")

            # if statement for correct answer. If correct add 1 to counter, If counter = 2, delete the data from the node
            if answer == "Q" or answer == "q":
                correctcounter2 = correctcounter2 - 1
                print("Good Job!")
                print("\n")
                if correctcounter2 == 0:
                    flashcard.delete(second_node.data)
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
                print("Good Job!")
                print("\n")
                if correctcounter3 == 0:
                    flashcard.delete(third_node.data)
            else:
                print("Try again next time :(")
                print("\n")
                third_node.get_next()

            if flashcard.is_empty() is True:
                print("Well done! You have answered them all correctly, and twice!")
                quit()


# drivers
traverse_list()
