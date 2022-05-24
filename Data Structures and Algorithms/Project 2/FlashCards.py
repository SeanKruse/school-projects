# Initializing the Node class with fields and methods
class Node:
    # Constructor that is called when an instance of a class is created
    def __init__(self, data):
        self.data = data
        self.next = None

    # Getter for data field
    def get_data(self):
        return self.data

    # Getter for next field
    def get_next(self):
        return self.next

    # Setter for data field
    def set_data(self, data):
        self.data = data

    # Setter for next field
    def set_next(self, next):
        self.next = next


# Initializing the LinkedList class with fields and methods
class LinkedList:

    # Constructor initializes the list to be empty
    def __init__(self):
        self.head = None

    # This method is not used in the current iteration of this program, but should be used within the traversal
    def insert(self, data):
        new_node = Node(data)
        new_node.set_next(self.head)
        self.head = new_node

    # This delete method is used within the traversal method, but not to it's full potential.
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
        if previous is None:
            self.head = current.get_next()
        else:
            previous.set_next(current.get_next())

    # Return True if the list is empty, False otherwise
    def is_empty(self):
        return self.head is None


# Method to traverse the list - one loop with condition is_empty is False - and exits loop when is_empty is True
def traverse_list():
    # creating object of LinkedList class and saving it to var flashcard
    flashcard = LinkedList()

    # Creating objects of the flashcard nodes and saving them to var first, second, and third_node
    first_node = Node("FLASHCARD HINT: an ordered collection of items that can be implemented as array-based or linked")
    second_node = Node("FLASHCARD HINT: stores items in a first in / first out manner")
    third_node = Node("FLASHCARD HINT: store items in a last in / first out manner")

    # Setting the head to the first node, then pointing .next to the second node, and .next to third node from second
    flashcard.head = first_node
    first_node.next = second_node
    second_node.next = third_node

    # Setting counters for the individual nodes at 2 - Decrementing by 1 each time the answer is correct
    # First node counter
    correctcounter = 2
    # Second node counter
    correctcounter2 = 2
    # Third node counter
    correctcounter3 = 2

    # Loop while the flashcards have nodes in them - exit when is_empty is True
    while flashcard.is_empty() is False:

        # Print the data from the first node and display answer menu then save into var answer
        print(first_node.data)
        answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST ")

        # If statement for correct answer, subtract 1 from counter; if counter = 0, delete the data from the node
        if answer == "L" or answer == "l":
            correctcounter = correctcounter - 1
            print("Good Job!")
            print("\n")
            if correctcounter == 0:
                flashcard.delete(first_node.data)
        # If incorrect answer then jump to next node
        else:
            print("Try again next time :(")
            print("\n")
            first_node.get_next()

        # Print the data from the second node and display answer menu then save into var answer
        print(second_node.data)
        answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST: ")

        # If statement for correct answer, subtract 1 from counter; if counter = 0, delete the data from the node
        if answer == "Q" or answer == "q":
            correctcounter2 = correctcounter2 - 1
            print("Good Job!")
            print("\n")
            if correctcounter2 == 0:
                flashcard.delete(second_node.data)
        # If incorrect answer then jump to next node
        else:
            print("Try again next time :(")
            print("\n")
            second_node.get_next()

        # Print the data from the third node and display answer menu then save into var answer
        print(third_node.data)
        answer = input("ENTER 'S' FOR STACK, 'Q' FOR QUEUE', OR 'L' FOR LIST: ")

        # If statement for correct answer, subtract 1 from counter; if counter = 0, delete the data from the node
        if answer == "S" or answer == "s":
            correctcounter3 = correctcounter3 - 1
            print("Good Job!")
            print("\n")
            if correctcounter3 == 0:
                flashcard.delete(third_node.data)
        # If incorrect answer then jump to next node
        else:
            print("Try again next time :(")
            print("\n")
            third_node.get_next()
        # Finally, if all the data from the nodes is deleted the list is empty and the loop is exited
        if flashcard.is_empty() is True:
            print("Well done! You have answered them all correctly, and twice in a row!")
            quit()


# Driver for traversal method
traverse_list()
