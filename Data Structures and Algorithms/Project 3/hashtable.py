import unittest


class HashTable():
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
    hashfunction(key, size)
    rehash(oldhash, size)
    get(key)
    delete(key)
    __contains__(self, key)
    __len__(self)
    __delitem__(self, key)
    -------
    YOU CAN LIST YOUR CLASS METHODS HERE AND DOCUMENT/DESCRIBE THEM MORE BELOW
    ...
    """

    def __init__(self, size):
        """ constructor method that is called when an instance of a class is created """
        self.size = size
        self.slots = [None] * self.size
        self.data = [None] * self.size

    def put(self, key, data):
        """ methods that inserts key value pairs into the hashtable """
        """ I could not figure out a graceful way to handle collision - I attempted using a variable (slotfull) 
        for when the slot contained data and setting it equal to next slot (similarly to our LinkedList assignment) 
        but could not get it to work without implementing resize logic that would set self.size = prime number 
        (like 11) before rehashing.  """
        hashvalue = self.hashfunction(key, len(self.slots))
        slotfull = -1  # attempting some collision logic / initializing slotfull to -1
        if self.slots[hashvalue] == None:
            self.slots[hashvalue] = key
            self.data[hashvalue] = data
        else:
            if self.slots[hashvalue] == key:  # replace data currently there
                self.data[hashvalue] = data  # since exact same key is used
                slotfull = hashvalue  # attempting some collision logic by setting hashvalue to slotfull
            else:
                nextslot = self.rehash(hashvalue, len(self.slots))
                while self.slots[nextslot] != None and self.slots[nextslot] != key:
                    nextslot = self.rehash(nextslot, len(self.slots))
                if self.slots[nextslot] == None:
                    self.slots[nextslot] = key
                    self.data[nextslot] = data
                else:
                    self.data[nextslot] = data  # replace data currently there
                    return slotfull  # return slotfull within inside else
                slotfull = nextslot  # attempting some collision logic by setting nextslot to slotfull
            return slotfull  # return slotfull within outside else

    def hashfunction(self, key, size):
        """ method that returns the key modulo size of hashtable"""
        return key % size

    def rehash(self, oldhash, size):
        """ appends the size of the hashtable by using modulo operator on the hashtable size """
        return (oldhash + 1) % size

    def get(self, key):
        """ method that retrieves the key by iterating through the hashtable slots """
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
        """ retrieves the key from the hashtable using the get method """
        return self.get(key)

    def __setitem__(self, key, data):
        """ sets the key value pair into the hashtable using the put method"""
        self.put(key, data)

    # ADD YOUR NEW METHODS TO COMPLETE ASSIGNMENT #3 (e.g. __contains__)
    # AND MODIFY put METHOD AND ANY OTHERS THAT NEED TO BE MODIFIED

    def __contains__(self, key):
        """ over-riding the in operator - checks to see if the associated key is contained in the slots """
        return key in self.slots

    def __len__(self):
        """ over-riding the len() operator - counts the slots that do not contain None """
        count = 0
        for i in self.slots:
            if i is not None:
                count = count + 1
        return count

    def delete(self, key):
        """ delete method that iterates through the hashtable and sets data
         and slots at current position equal to None if key is found"""
        startslot = self.hashfunction(key, len(self.slots))

        data = None
        stop = False
        found = False
        position = startslot  # current position of slots using hashfunction method
        while self.slots[position] != None and not found and not stop:
            if self.slots[position] == key:
                found = True
                """ set data at the current position to None"""
                self.data[position] = None
                """ set slots at the current position to None, this is to maintain the len() and in tests """
                self.slots[position] = None
            else:
                position = self.rehash(position, len(self.slots))
                if position == startslot:
                    stop = True
        return data

    def __delitem__(self, key):
        """ overrides del() - deletes the key from the hashtable using the delete method"""
        self.delete(key)


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
    # (and feel free to modify those above to do something else)


# main() - run any example/demo you want to when running as standalone program
def main():
    h = HashTable(7)
    h[16] = 'cat'
    h[11] = 'dog'
    h[21] = 'bird'
    print("-" * 25, "keys and values:", "-" * 25)
    print(h.slots)
    print(h.data)
    print(h[16] == 'cat')


# unittest_main() - run unittest's main, which will run TestHashTable's methods
def unittest_main():
    print("-" * 25, "running unit tests", "-" * 25)
    unittest.main()


# evaluates to true if run as standalone program (e.g. $ python hashtable.py)
if __name__ == '__main__':
    main()
    unittest_main()
