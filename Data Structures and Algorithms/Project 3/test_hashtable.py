from hashtable1 import HashTable

# instantiate a HashTable object
h = HashTable(7)

# store keys and values in the object
h[6] = 'cat'
h[11] = 'dog'
h[21] = 'bird'
h[27] = 'horse'

print("-"*10, "keys and values", "-"*10)
print(h.slots)
print(h.data)

# check that data was stored correctly
print("-"*10, "data check", "-"*10)
if h.data == ['bird', 'horse', None, None, 'dog', None, 'cat']:
    print("    + HashTable 'put' all items in correctly")
else:
    print("    - items NOT 'put' in correctly")

# check that 'in' operator works correctly
print("-"*10, "in operator", "-"*10)
if 27 in h:
    print("    + 'in' operator correctly implemented")
else:
    print("    - 'in' operator NOT working")

# delete operator
del h[11]

# check that len() function is implemented and works
print("-"*10, "len() function", "-"*10)
if len(h) == 3:
    print("    + 'len' function works properly")
else:
    print("    - 'len' function NOT working")

# "in" operator (returns a boolean)
print("-"*10, "len() after deletion", "-"*10)
if 11 not in h:
    print("    + 'in' operator works correctly after 11 was removed")
else:
    print("    - 'in' operator OR 'del' NOT working")

# check that data was also removed
print("-"*10, "data after deletion", "-"*10)
if h.data == ['bird', 'horse', None, None, None, None, 'cat']:
    print("    + data is correct after deletion")
else:
    print("    - data not correctly removed after deletion")
