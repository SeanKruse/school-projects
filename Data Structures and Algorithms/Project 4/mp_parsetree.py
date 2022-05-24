from binarytree import BinaryTree
from stack import Stack
import operator
import random
import unittest


# noinspection PyArgumentList
def buildMPLogicParseTree(s):
    """ Creating the logic of parsing floats as probabilities to be the children of the operators AND and OR.
    Floats are between 0.0 and 1.0 (utilizing random.random to generate number between 0.0 and 1.0)
    Returning T or True for 1.0, P or Probably for .75 - .99, M or Maybe for 0.1 to .74, F or False for 0.0 """

    slist = s.split('')  # separate by space
    pStack = Stack()  # create a Stack object variable
    eTree = BinaryTree('')  # create a BinaryTree object variable that is empty
    pStack.push(eTree)  # create a variable to utilize push from stack by incorporating BinaryTree object
    currentTree = eTree  # save the BinaryTree object into currentTree variable

    for i in slist:
        """ Insert left parentheses as LeftChild, update position by pushing currentTree, 
        save the current position with getter into currentTree  """
        if i == '(':
            currentTree.insertLeft('')
            pStack.push(currentTree)
            currentTree = currentTree.getLeftChild()
            """ AND and OR are operators, so set to RootVal. Insert left of current tree position
            update position by pushing currentTree, save the current position with getter into currentTree  """
        elif i in ['AND', 'OR']:
            currentTree.setRootVal(i)
            currentTree.insertLeft('')
            pStack.push(currentTree)
            currentTree = currentTree.getLeftChild()
            """ Create probability logic, Returning T or True for 1.0, P or Probably for .75 - .99, 
            M or Maybe for 0.1 to .74, F or False for 0.0  """
        elif i in ['T', 'F'] or i[0] in ['M', 'P']:
            if i == 'T':
                value = 1.0
            elif i == 'F':
                value = (0.0, 0.0)
            elif i[0] == 'M':
                bound = float(i.split("_")[1])  # split underscore from current expression. Index 1 to separate float
                if bound >= 0.75:
                    print("token '{}' is not a valid integer".format(i))
                    return None
                else:
                    value = bound
            elif i[0] == 'P':
                bound = float(i.split("_")[1])  # split underscore from current expression. Index 1 to separate float
                if bound < 0.75:
                    print("token '{}' is not a valid integer".format(i))
                    return None
                else:
                    value = bound

            currentTree.setRootVal()
            parent = pStack.pop()
            currentTree = parent
            """ Last token from expression, so pop() to return the right parentheses """
        elif i == ')':
            currentTree = pStack.pop()

        else:
            print("token '{}' is not a valid integer".format(i))
            return None
    return eTree


def evaluateMPLogicParseTree(t):
    """ Saving AND and OR to operators, modifying parsetree.py code to utilize these operators"""
    opers = {'AND': operator.and_, 'OR': operator.or_}

    leftC = t.getLeftChild()
    rightC = t.getRightChild()

    if leftC and rightC:
        fn = opers[t.getRootVal()]
        return fn(evaluateMPLogicParseTree(leftC), evaluateMPLogicParseTree(rightC))
    else:
        bound = t.getRootVal()
        return random.random() < bound  # returning Boolean from random.random(), False if less than bound variable


def printMPLogicExpression(t):
    """ Print logic - Left parentheses from LeftChild, then RootVal Node, lastly right parentheses from Rightchild"""
    tree = t
    sVal = ""
    if tree:
        sVal = '(' + printMPLogicExpression(t.getLeftChild())
        sVal = sVal + str(t.getRootVal())
        sVal = sVal + printMPLogicExpression(t.getRightChild()) + ')'
    return sVal


class TestParseTree(unittest.TestCase):
    """ I really struggled with the unittest portion of this assignment,
    I commented out test cases that straight up weren't working or seemed to throw up IDE errors.
    It was difficult to test cases against this assignment without examples. I created a separate file for testing
    similar to the HashTable program, but would really like to spend some class time on unittest.
    As someone coming from learning Java, I really have no experience with unittest"""
    bt = BinaryTree()

    # for x in slist:
    # eTree.insertLeft(x)
    # eTree.insertRight(x)

    def test_buildMPLogicParseTree(self):
        self.assertEqual(buildMPLogicParseTree('T'), 1.0)
        self.assertEqual(buildMPLogicParseTree('P'), 0.75)
        self.assertEqual(buildMPLogicParseTree('M'), 0.5)
        self.assertEqual(buildMPLogicParseTree('F'), 0.0)

    def test_evaluateMPLogicParseTree(self):
        pt = buildMPLogicParseTree("( T AND F )" + "( T OR F )" + "( M_0.7 )" + "( M_0.9 )" + "( ( P_0.8 AND T ) OR ( M_0.25 ) )")
        print("Evaluating parse tree...", evaluateMPLogicParseTree(pt))

    def test_printMPLogicExpression(self):
        pt = buildMPLogicParseTree("( T AND F )" + "( T OR F )" + "( M_0.7 )" + "( M_0.9 )" + "( ( P_0.8 AND T ) OR ( M_0.25 ) )")
        print(printMPLogicExpression(pt))

    def test_display(self):
        print("Print Tree")
        self.bt.printTree()
        print("Preorder Traversal: ")
        self.bt.preOrder()
        print("Postorder Traversal: ")
        self.bt.postOrder()
        print("Inorder Traversal: ")
        self.bt.inOrder()


def unittest_main():
    print("-" * 25, "running unit tests", "-" * 25)
    unittest.main()


def main():
    pass
    pt = buildMPLogicParseTree(" ( ( T AND F ) OR M_0.3 ) ")
    print("Evaluating parse tree...", evaluateMPLogicParseTree(pt))
    print(printMPLogicExpression(pt))


# only executed if run as a standalone program
if __name__ == '__main__':
    main()
    unittest_main()



