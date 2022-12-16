# Instructions

Consider the following JavaScript-like program to answer the items below. Note: this is the same code from activity 07 (question 2). 

```
// main program
var x, y, z;

function sub1() {
    var a, y, z;
    // ... 
}

function sub2() {
    var a, b, z;
    // ...
}

function sub3() {
    var a, x, w;
    // ...
}
```

Given the following calling sequences and assuming that dynamic scoping is used, what variables are visible during execution of the last subprogram activated? Include with each visible variable the name of the unit where it is declared. If the variable is not visible, say so.  

Note that those items match items d, e, and f (from activity 07, question 2). Help me grade faster: fill the parentheses with sub1, sub2, sub3, or "not visible". Don't change the order of the variables!

d) main calls sub3; sub3 calls sub1.  

a (sub1), b ("not visible"), x (sub3), y (sub1), z (sub1), w (sub3) 

e) main calls sub1; sub1 calls sub3; sub3 calls sub2. 

a (sub2), b (sub2), x (sub3), y (sub1), z (sub2), w (sub3) 
 
f) main calls sub3; sub3 calls sub2; sub2 calls sub1.  

a (sub1), b (sub2), x (sub3), y (sub1), z (sub1), w (sub3) 

# Submission

Submit this file (README.md) on Canvas and that's it!
