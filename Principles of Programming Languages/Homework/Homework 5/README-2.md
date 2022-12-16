# Left Recursion Elimination

Eliminate any left-recursive productions (including indirect ones). When you are done, submit this README.md file with your answers on Canvas. 

## Q1

```
X -> XYz | Xw | w
Y -> Yp | q
```

Simplify:

X -> XYz | Xw | w
Y -> Yp | q

X -> Xpz | Xq | Xw | w

Simplified Production:
X -> Xpz | Xq | Xw | w
X' -> pzX' | qX' | wX' | w | E

Final Production without simplification: 
X -> wX'
X' -> YzX' | wX' | E
Y -> qY'
Y' -> pY' | E

## Q2

```
S -> aA | Sd
A -> b
```

Simplify: 

S-> aA | Sd 
A-> b 

S -> ab | Sd

Simplified Production:
S -> ab | Sd
S' -> ab | dS' | E

Final Production without simplification: 
S -> aAS'
S' -> dS' | E
A -> b

## Q3

```
A -> Bxy | x
B -> CD
C -> A | c
D -> d           
```
Simplify:

A -> Bxy | x
B -> CD
C -> A | c
D -> d

A -> CDxy | x
C -> A | c
D -> d

C -> CDxy | Cx | Cc
D -> d

C -> Cdxy | Cx | Cc

Simplified Production:
C -> Cdxy | xC | cC
C' -> dxyC' | xC' | cC' | E

Final Production without simplification: 
A -> Bxy | x
B -> CD 
C -> xC' | cC'
C' -> DxyC' | E
D -> d 
