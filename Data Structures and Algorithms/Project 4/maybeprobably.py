# Assignment 4: Maybe Probably Logic
from stack import Stack
from binarytree import BinaryTree
import unittest
import random

# Main function to take a modal logic expression and inputs it into a parse tree
def buildMPLogicParseTree(mpexp):
    # Splits espression by spaces
    mplist = mpexp.split()
    mpstack = Stack()
    mptree = BinaryTree('')
    mpstack.push(mptree)
    # Sets current tree
    currenttree = mptree
    maybeprobably = ['M_', 'P_']  

    for i in mplist:
        if i == '(':
            # Inserts node to the left and sets the current node to the new left node
            currenttree.insertLeft('')
            mpstack.push(currenttree)
            currenttree = currenttree.getLeftChild()

        elif any(x in i for x in maybeprobably):
            # Substring splits into letter and percentage and inserts the percentage into the 
            # current node and the letter in the node to the left. If the percentage is out 
            # of range, prints an error message.
            problist = i.split('_')

            for i, j in enumerate(problist[:-1]):
                if j in ['M', 'P']:
                    
                    if j == 'M' and float(problist[i + 1]) < 0.75:
                        currenttree.setRootVal(problist[i + 1])
                        currenttree.insertLeft(j)
                        parent = mpstack.pop()
                        currenttree = parent

                    elif j == 'P' and float(problist[i + 1]) > 0.75:
                        currenttree.setRootVal(problist[i + 1])
                        currenttree.insertLeft(j)
                        parent = mpstack.pop()
                        currenttree = parent

                    if j == 'M' and float(problist[i + 1]) >= 0.75:
                        print(problist[i + 1] + ' is not a valid percentage for maybe')
                        return None
                    
                    elif j == 'P' and float(problist[i + 1]) <= 0.75:
                        print(problist[i + 1] + ' is not a valid percentage for probably')
                        return None


        elif i in ['T', 'F']:
            # Sets current node equal to value and moves current node to the parent.
            currenttree.setRootVal(i)
            parent = mpstack.pop()
            currenttree = parent
        
        elif i in ['AND', 'OR']:
            # Sets current node equal to value and inserts a node to the right. Current node set to 
            # the new right node.
            currenttree.setRootVal(i)
            currenttree.insertRight('')
            mpstack.push(currenttree)
            currenttree = currenttree.getRightChild()

        elif i == ')':
            # Sets current tree to the root node.
            currenttree = mpstack.pop()

        else:
            # Prints error message if there are any invalid characters.
            print("token '{}' is not a valid integer".format(i))
            return None

    return mptree

# Evaluates the modal logic expression to True or False
def evaluateMPLogicParseTree(mptree):
    left = mptree.getLeftChild()
    right = mptree.getRightChild()

    # Finds operator and True or False values and evalutes to True or False based on modal logic.
    if (left != None and right != None) or (left == None and right == None):        
        operator = mptree.getRootVal()
        if left.getRootVal() or right.getRootVal() == 'T':
            return True

        elif (left.getRootVal() or right.getRootVal()) == 'F':
            return False

        elif operator == 'AND':
            return evaluateMPLogicParseTree(left) and evaluateMPLogicParseTree(right)

        elif operator == 'OR':
            return evaluateMPLogicParseTree(left) or evaluateMPLogicParseTree(right)

    # Finds number values and evaluates to True or False based on pecentage
    else:
        return float(mptree.getRootVal()) > random.uniform(0, 1)

# Inorder traversal of the parse tree with parenthesis
def printMPLogicExpression(mptree):
    mpval = ''
    if mptree:
        mpval = '(' + printMPLogicExpression(mptree.getLeftChild())
        mpval += str(mptree.getRootVal())
        mpval += printMPLogicExpression(mptree.getRightChild()) + ')'
    return mpval

class TestMPLogicParseTreeFunction(unittest.TestCase):
    def testTreeConstruction(self):
        mp = buildMPLogicParseTree('( ( T OR P_0.8 ) AND M_0.25 )')
        expected = '(((T)OR((P)0.8))AND((M)0.25))'
        self.assertEqual(printMPLogicExpression(mp), expected)

    def testEvaluate(self):
        mp = buildMPLogicParseTree('( ( T OR P_0.8 ) AND M_0.25 )')
        self.assertIsNotNone(evaluateMPLogicParseTree(mp))

def main():
    mp = buildMPLogicParseTree('( ( P_0.8 AND T ) OR ( M_0.25 ) )')
    print(printMPLogicExpression(mp))
    print(evaluateMPLogicParseTree(mp))

def unittest_main():
    print("-" * 25, "running unit tests", "-" * 25)
    unittest.main()

if __name__ == '__main__':
    main()
    unittest_main()
