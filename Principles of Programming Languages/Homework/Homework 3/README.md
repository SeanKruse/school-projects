# Formal Grammars

For each of the grammars from Q1-3, classify them as either regular or context-free. Then answer yes/no whether each word can be generated from the grammar. If you answer yes, show the sequence of productions to generate the word.  When you are done, submit this README.md file with your answers on Canvas. 

## Q1

```
G = (V, T, P, S) 
V = {S, NP, NOM, VP, Det, Noun, Verb, Aux}
T = {that, this, a, the, man, book, flight, meal, include, read, does}
P = {
    S → NP VP 
    S → Aux NP VP 
    S → VP 
    NP → Det NOM 
    NOM → Noun
    NOM → Noun NOM
    VP → Verb
    VP → Verb NP
    Det → that | this | a | the
    Noun → book | flight | meal | man
    Verb → book | include | read
    Aux → does
} 
```
Context-free

### a)

```
does a meal include a book 
```

Yes
S-> Aux NP VP
S-> does NP VP
S-> does Det NOM VP
S-> does a NOM VP
S-> does a Noun VP
S-> does a meal VP
S-> does a meal Verb NP
S-> does a meal include NP
S-> does a meal include Det NOM
S-> does a meal include a NOM
S-> does a meal include a Noun
S-> does a meal include a book


### b) 

```
this flight book a meal
``` 
Yes
S-> NP VP
S-> Det NOM VP
S-> this NOM VP
S-> this Noun VP
S-> this flight VP
S-> this flight Verb NP
S-> this flight book NP
S-> this flight book Det NOM
S-> this flight book a NOM
S-> this flight book a Noun
S-> this flight book a meal


### c) 

```
a man read a book
```
Yes
S-> NP VP
S-> Det NOM VP
S-> a NOM VP
S-> a Noun VP
S-> a man VP
S-> a man Verb NP
S-> a man read NP
S-> a man read Det NOM
S-> a man read a NOM
S-> a man read a Noun
S-> a man read a book

## Q2

```
G = ({S, A, B}, {a, b}, P, S) 
P = {S → aA, A → bB | ε, B → aA}    
```
Regular

### a) 

```
a
```
Yes
S->aA
S->aε

### b) 

```
aba
```
Yes
S->aA
S->abB
S->abaA
S->abaε

### c) 

```
bb
```
No

### d) 

```
abb
```
No

## Q3

```
G = ({S, A}, {0, 1}, P, S) 
P = {S → A0, A→A01|ε}           
```
Regular

### a) 

```
00
```
No

### b) 

```
ε
```
No

### c)

```
010
```
Yes
S-> A0
S-> A010
S-> εA010

### d) 

```
0101010
```
Yes
S-> A0
S-> A010
S-> A01010
S-> A0101010
S-> ε0101010
 