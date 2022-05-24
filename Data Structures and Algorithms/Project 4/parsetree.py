from stack import Stack
from binarytree import BinaryTree
import operator
import unittest

def buildParseTree(fpexp):
    """
    your documentation should go here
    (we don't have much in our examples to keep them clean but yours should)
    """
    fplist = fpexp.split()
    pStack = Stack()
    eTree = BinaryTree('')
    pStack.push(eTree)
    currentTree = eTree

    for i in fplist:
        if i == '(':
            currentTree.insertLeft('')
            pStack.push(currentTree)
            currentTree = currentTree.getLeftChild()

        elif i in ['+', '-', '*', '/']:
            currentTree.setRootVal(i)
            currentTree.insertRight('')
            pStack.push(currentTree)
            currentTree = currentTree.getRightChild()

        elif i.isnumeric():
            currentTree.setRootVal(int(i))
            parent = pStack.pop()
            currentTree = parent

        elif i == ')':
            currentTree = pStack.pop()

        else:
            print("token '{}' is not a valid integer".format(i))
            return None

    return eTree


def evaluate(parseTree):
    """
    your documentation should go here
    (we don't have much in our examples to keep them clean but yours should)
    """
    opers = {'+':operator.add, '-':operator.sub, '*':operator.mul, '/':operator.truediv}

    leftC = parseTree.getLeftChild()
    rightC = parseTree.getRightChild()

    if leftC and rightC:
        fn = opers[parseTree.getRootVal()]
        return fn(evaluate(leftC),evaluate(rightC))
    else:
        return parseTree.getRootVal()


def printExpression(tree):
    """
    your documentation should go here
    (we don't have much in our examples to keep them clean but yours should)
    """
    sVal = ""
    if tree:
        sVal = '(' + printExpression(tree.getLeftChild())
        sVal = sVal + str(tree.getRootVal())
        sVal = sVal + printExpression(tree.getRightChild())+')'
    return sVal


class TestMPLogicParseTreeFunction(unittest.TestCase):
    """ your unit tests should be in here """
    pass


# code you want to run when this is exectuted as a standalone program should be in here
def main():
    pass
    pt = buildParseTree("( ( 3 + 7 ) * ( 4 + 5 ) )")
    print("Evaluating parse tree...", evaluate(pt))
    print(printExpression(pt))

# unittest_main() - run unittest's main, which runs TestMPLogicParseTreeFunction's methods
def unittest_main():
    print("-"*25, "running unit tests", "-"*25)
    unittest.main()

# evaluates to true if run as standalone program (e.g. $ python hashtable.py)
if __name__ == '__main__':
    main()
    unittest_main()
