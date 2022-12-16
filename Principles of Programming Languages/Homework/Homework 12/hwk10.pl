% CS3210 - Principles of Programming Languages - Fall 2022
% Instructor: Thyago Mota
% Description: Homework 10 - A KB in Prolog with queries
% Student Name: Sean Kruse

% Knowledge Base

% Mountains
mountain(mteverest).
mountain(aconcagua).
mountain(mtmckinley).
mountain(kilimanjaro).
mountain(mtelbrus).
mountain(mtvinson).
mountain(puncakjaya).

% elevation in feet
elevation(29029).
elevation(22841).
elevation(20312).
elevation(19340).
elevation(18510).
elevation(16050).
elevation(16023).

% Continents
continent(asia).
continent(southamerica).
continent(northamerica).
continent(africa).
continent(europe).
continent(antartica).
continent(australia).

% climbers
climber(john).
climber(maria).
climber(kelly).
climber(derek).
climber(thyago).

% certified
cert(john).
cert(maria).
cert(kelly).
cert(derek).

% certified climbers
certclimber(john, cert) :- climber(john), cert(john).
certclimber(maria, cert) :- climber(maria), cert(maria).
certclimber(kelly, cert) :- climber(kelly), cert(kelly).
certclimber(derek, cert) :- climber(derek), cert(derek).
    
% cutoff
cutoff(mteverest, 29029) :- mountain(mteverest),elevation(29029).
cutoff(aconcagua, 22841) :- mountain(aconcagua),elevation(22841).
cutoff(mtmckinley, 20312) :- mountain(mtmckinley),elevation(20312).
cutoff(kilimanjaro, 19340) :- mountain(kilimanjaro), elevation(19340).
cutoff(mtelbrus, 18510) :- mountain(mtelbrus), elevation(18510).
cutoff(mtvinson, 16050) :- mountain(mtvinson), elevation(16050).
cutoff(puncakjaya, 16023) :- mountain(puncakjaya), elevation(16023).


% location
location(mteverest, asia) :- mountain(mteverest),continent(asia).
location(aconcagua, southamerica) :- mountain(aconcagua),continent(southamerica).
location(mtmckinley, northamerica) :- mountain(mtmckinley),continent(northamerica).
location(kilimanjaro, africa) :- mountain(kilimanjaro), continent(africa).
location(mtelbrus, europe) :- mountain(mtelbrus), continent(europe).
location(mtvinson, antartica) :- mountain(mtvinson), continent(antartica).
location(puncakjaya, australia) :- mountain(puncakjaya), continent(australia).

% Who would climb what mountains based on certification and location
climbwilling(john, X) :- certclimber(john, cert),location(X, northamerica).
climbwilling(kelly, X) :- certclimber(kelly, cert),cutoff(X, Y), Y > 20000.
climbwilling(maria, X) :- certclimber(maria, cert),mountain(X).
climbwilling(derek, X) :- certclimber(derek, cert),(location(X, europe);location(X, africa);location(X, australia)),cutoff(X, Y), Y=< 19000.
climbwilling(thyago, X) :- cutoff(X, 0).

% all queries
% cutoff(X,Y), max_member(Key-Value,[X-Y]).
% location(kilimanjaro, africa).
% cutoff(mtelbrus, X), X > 18000.
% certclimber(thyago, cert).
% certclimber(john, cert).
% certclimber(X, cert). 
% climbwilling(john, X).
% climbwilling(kelly, X).
% climbwilling(maria, X).
% climbwilling(derek, X).
% climbwilling(thyago, X).
