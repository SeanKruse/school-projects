% CS3210 - Principles of Programming Languages - Fall 2022
% Instructor: Thyago Mota
% Description: Homework 11 - Prolog Lists
% Student Name: Sean Kruse

% Knowledge Base
sum([], 0).
sum([H | T], S) :- sum(T, X), S is H+X.


max([],0).
max([H|T], M) :- max(T, TailMax), H > TailMax, M is H.
max([H|T], M) :- max(T, TailMax), H =< TailMax, M is TailMax.

% Queries / Tests
%sum([], 0). 
%true
%sum([1, 2, 5], 8). 
%true
%sum([1, 2, 5], X). 
%x = 8
%max([1, 2, 5], 5). 
%true
%max([1, 2, 5], X). 
%x = 5