import unittest


class HashTable:
    """
    A class to represent a basic HashTable, which is essentially
    a crude implementation of Python's dict(tionary) class

    Attributes/Fields
    -----------------
    size : int
        size of hashtable instance representing total capacity
    slots: list
        list object to store keys after hashing - at location: hash(key)
    data: list
        list object to store data after hashing - at location: hash(key)

    Methods
    -------
    put(key, data)
    len()
    contains()
    del()
    checkIfFull()
    reSize()
    numEmptySlots()
    ...
    YOU CAN LIST YOUR CLASS METHODS HERE AND DOCUMENT/DESCRIBE THEM MORE BELOW
    ...
    """

    def __init__(self, size):
        """Constructor for a Hashtable. contains slots with 'key's that correspond with their entered data."""
        self.size = size
        self.slots = [None] * self.size
        self.data = [None] * self.size

    def put(self, key, data):
        """Method to enter data into a Hashtable. If hashtable is full then it will be resized to accomadate new data. """
        if self.checkIfFull():
            print("This hashtable is already full, and will be resized to push in '",data, "' data. Note: the index's of key slots will change"
                  " as a result of this")
            newTable = self.reSize() #------Uses reSize() method below to create a new larger table, and copy current data to it
            newTable.put(key, data)
            self.slots = newTable.slots #sets slot pointer to point to new, larger table
            self.data = newTable.data #sets data pointer to point to new, larger table
            #print(self.size)
            #print(self.size) testing
            #print(self.slots)
            #print(self.data)
            return newTable
        else:
            hashvalue = self.hashfunction(key, len(self.slots))
            if self.slots[hashvalue] == None:
                self.slots[hashvalue] = key
                self.data[hashvalue] = data
            else:
                if self.slots[hashvalue] == key:  # replace data currently there
                    self.data[hashvalue] = data  # since exact same key is used
                else:
                    nextslot = self.rehash(hashvalue, len(self.slots))
                    while self.slots[nextslot] != None and self.slots[nextslot] != key:
                        nextslot = self.rehash(nextslot, len(self.slots))

                    if self.slots[nextslot] == None:
                        self.slots[nextslot] = key
                        self.data[nextslot] = data
                    else:
                        self.data[nextslot] = data  # replace data currently there

    def hashfunction(self, key, size):
        """This is an algorithm used to quickly find an availible slot for any 'int' key parameter upon first pass when storing in a hashtable"""
        # print("current key: ", key)
        # print("key % size = ", key % size)
        return key % size

    def rehash(self, oldhash, size):
        """This method is used in the instance that the first desired slot for some key/data combo is already filled. The oldhash = previousKey%size (the remeinder of the calculation).
        if that slot is occupied then this incriments it by 1 and does the calculation again to try to find an empty slot."""
        # print("current oldhash: ", oldhash)
        # print("oldhash+1 % size = ", oldhash+1 % size)
        return (oldhash + 1) % size

    def get(self, key):
        """"This method iterates through the index's of the objects 'slots' until it finds one that equals the key parameter. Once found
        it returns the data of the corresponding key"""
        startslot = self.hashfunction(key, len(self.slots))

        data = None
        stop = False
        found = False
        position = startslot
        while self.slots[position] != None and not found and not stop:
            if self.slots[position] == key:
                found = True
                data = self.data[position]
            else:
                position = self.rehash(position, len(self.slots))
                if position == startslot:
                    stop = True
        return data

    def __getitem__(self, key):
        """returns the data from the Hashtable with the corresponding key"""
        return self.get(key)

    def __setitem__(self, key, data):
        """Overrides python to enter data into a Hashtable with a corresponding key. Acts like a 'dic' object"""
        self.put(key, data)


    # ADD YOUR NEW METHODS TO COMPLETE ASSIGNMENT #3 (e.g. __contains__)
    def __len__(self):
        """This returns the amount of elements in the HashTable that contain data. If you wish find the size of the hashtable, incuding
        elements that do not contain data, use 'size()'"""
        slotIndex = 0
        count = 0
        size = self.size - 1
        done = False
        while not done:
            slotData = self.slots[slotIndex]
            if slotData is not None:
                count += 1
            if slotIndex >= size:
                done = True
            slotIndex = slotIndex + 1
            # print("slotIndex = ", slotIndex) testing
            # print("count:",count) testing
            # print("size:", size) testing
        # print("amount of slots filled: ", count) testing
        return count

    def __contains__(self, key):
        """This method overrides 'in' function in python and now checks to see if data is in the hashtable based
        on the existence of it's 'key', returning true if it is"""
        # print("You are now in 'contains'") testing
        isIn = False
        keepGoing = True
        index = 0
        while keepGoing == True:
            slot = self.slots[index]
            # print("current hashdata: ", hashdata)
            if key == slot:
                isIn = True
                return isIn
            if index >= self.size - 1:
                keepGoing = False
            index += 1
        return isIn

    def __delitem__(self, key):
        """overrides del() function in python and now removes key/value pairs from an in stance in a hashtable. If they key is not
         in a slot then this will print an error message. First checks to see if key is in the table using 'in' over-written method. Next
         it iterates through the slots to find the index with the corresponding key. Then removes the data from that key. Finally it
         removes the key value from the slots. (Both data and key are replaced with 'None')"""
        if key not in self:
            print(key, " is not a correct key for the HashTable")
        else:
            index = 0
            #print(self.slots) testing
            while self.slots[index] != key:
                index += 1
            self.__setitem__(key, None)
            hashvalue = self.hashfunction(key, len(self.slots))
            self.data[hashvalue] = None
            self.slots[index] = None
            #print("After delection data: ",self.get(key))
            #print("After delection slot: ",self.slots)
            #print("All data after deletion: ",self.data)

    def checkIfFull(self):
        """Additonal method I added just to check to see if the hashtable is full. This helps avoid infinite loops when trying
         to add data if it is full"""
        length = len(self)
        size = self.size
        if length < size:
            return False
        else:
            return True

    def reSize(self):
        """This method creates a new hashtable that is 2 slots larger than the previous, copies and rehashes all data, and stores it in the new, larger, table"""
        newSize = self.size + 2 #After a bit of testing, for smaller data sets this seems like a sufficent way to resize to avoid problems
        newTable = HashTable(newSize)
        done = False
        index = 0
        while not done: #copy/paste data from old table to new larger one
            key = self.slots[index]
            if key is not None:
                data = self.get(key)
                newTable[key] = data
            if index >= self.size - 1:
                return newTable
                done = True
            else:
                index = index + 1

    def numEmpySlots(self):
        """Quality of life method: Counts the number of available slots in the Hashtable and returns an int. Returns 0 if
        the hashtable is full"""
        slotIndex = 0
        count = 0
        size = self.size - 1
        done = False
        if self.checkIfFull() is False:
            while not done:
                slotData = self.slots[slotIndex]
                if slotData is None:
                    count += 1
                if slotIndex >= size:
                    done = True
                slotIndex = slotIndex + 1
        return count


    # AND MODIFY put METHOD AND ANY OTHERS THAT NEED TO BE MODIFIED
    help(reSize)

class TestHashTable(unittest.TestCase):
    """ Extend unittest.TestCase and add methods to test HashTable """

    def testKeysAfterPuts(self):
        """ Check that hashtable keys are as expected for simple case """
        h = HashTable(7)
        h[6] = 'cat'
        h[11] = 'dog'
        h[21] = 'bird'
        h[27] = 'horse'
        expected = [21, 27, None, None, 11, None, 6]
        self.assertEqual(h.slots, expected)

    def testDataAfterPuts(self):
        """ Check that hashtable data is as expected for simple case """
        h = HashTable(7)
        h[6] = 'cat'
        h[11] = 'dog'
        h[21] = 'bird'
        h[27] = 'horse'
        expected = ['bird', 'horse', None, None, 'dog', None, 'cat']
        self.assertEqual(h.data, expected)
    # ADD MORE TESTS HERE TO TEST YOUR NEW HASHTABLE METHODS
    #Testing done primarily in main()
    # (and feel free to modify those above to do something else)


# main() - run any example/demo you want to when running as standalone program
def main():

    """Bunch of testing to make sure each method functions as expected"""
    h = HashTable(7)
    h[16] = 'cat'
    h[11] = 'dog'
    h[21] = 'bird'
    h[56] = 'owl' #testing

    h[44] = 'bear'
    h[40] = 'gorilla'
    h[31] = 'tulip'
    print("number of empty slots: ", h.numEmpySlots())
    h[89] = 'TESTINGPUSHFULL'
    print(h.slots)
    print("should have new data: ", h.data)
    print(h.get(44))

    print("-" * 25, "keys and values:", "-" * 25)
    print(h.slots)
    print(h.data)
    print(h[16] == 'cat')
    del h[16]
    print("Is this hashtable full: ", h.checkIfFull())

    h[34] = 'dumbalon'
    print(h.slots)
    print(h.data)

#main()
# unittest_main() - run unittest's main, which will run TestHashTable's methods
def unittest_main():
    print("-" * 25, "running unit tests", "-" * 25)
    unittest.main()


# evaluates to true if run as standalone program (e.g. $ python hashtable.py)
if __name__ == '__main__':
    main()
    unittest_main()

