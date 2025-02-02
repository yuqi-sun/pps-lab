% ex.1.1
search(X, [X|_]).
search(X, [_|Xs]) :- search(X, Xs).

% search(a,[a,b,c]). yes
% search(a,[c,d,e]). no
% search(X,[a,b,c]). 3 yes: X=a,b,c, no
% search(a,X). yes, infinite
% search(a,[X,b,Y,Z]). 3 yes: X=a, Y=a, Z=a, no
% search(X,Y). yes, infinite

% ----------------------------------------------------------------------------------

% ex.1.2
% search2(Elem, List)
% looks for two consecutive occurrences of Elem

search2(X, [X,X|_]).
search2(X, [_|Xs]):- search2(X, Xs).

% search2(a,[b,c,a,a,d,e,a,a,g,h]). yes, yes, no
% search2(a,[b,c,a,a,a,d,e]). yes, yes, no
% search2(X,[b,c,a,a,d,d,e]). yes: X=a, yes: X=d, no
% search2(a,L). infinite yes: with new lists
% search2(a,[_,_,a,_,a,_]). five yes, replacing necessary _ to get a,a

% ----------------------------------------------------------------------------------

% ex.1.3
% search_two(Elem,List)
% looks for two occurrences of Elem with any element in between!

search(X, [X|_]).
search(X, [_|Xs]):- search(X, Xs).
search_two(X, [X|Xs]):- search(X, Xs).
search_two(X, [_|Xs]):- search_two(X,Xs).

% fully relational

% ----------------------------------------------------------------------------------

% ex.1.4
% search_anytwo(Elem,List)
% looks for any Elem that occurs two times, anywhere

search(X, [X|_]).
search(X, [_|Xs]):- search(X, Xs).
search_anytwo(X, [X|Xs]):- search(X, Xs).
search_anytwo(X, [_|Xs]):- search_anytwo(X, Xs).

% ----------------------------------------------------------------------------------

% ex.2.1
% size(List, Size)
% Size will contain the number of elements in List

size([],0).
size([_|T],M) :- size(T,N), M is N+1.

% size(X, 5) returns X=[_,_,_,_,_] and then goes time out, not fully relational

% ----------------------------------------------------------------------------------

% ex.2.2
% size(List,Size)
% Size will contain the number of elements in List,
% written using notation zero, s(zero), s(s(zero))..

size([], zero).
size([_|T], s(S)):- size(T, S).

% size( L, s(s(s(zero)))). returns yes L=[_,_,_] fully relational

% ----------------------------------------------------------------------------------

% ex.2.3
% sum(List,Sum)

sum([], 0).
sum([X|T], S):- sum(T, M), S is X+M.

% ----------------------------------------------------------------------------------

% ex.2.5
% max(List,Max)
% Max is the biggest element in List
% Suppose the list has at least one element

max([X|Xs], M):- max(Xs, M, X).
max([X], M, T):- X > T, M is X.
max([X], M, T):- X =< T, M is T.
max([X|Xs], M, T) :- X > T, max(Xs, M, X).
max([X|Xs], M, T) :- X =< T, max(Xs, M, T).

% ----------------------------------------------------------------------------------

% ex.2.6
% max(List,Max,Min)
% Max is the biggest element in List
% Min is the smallest element in List
% Suppose the list has at least one element

maxmin([X|Xs], Max, Min):- maxmin(Xs, Max, Min, X, X).
maxmin([X], Max, Min, Tmax, Tmin):- X > Tmax, Max is X, Min is Tmin.
maxmin([X], Max, Min, Tmax, Tmin):- X < Tmin, Min is X, Max is Tmax.
maxmin([X], Max, Min, Tmax, Tmin):- X =< Tmax, X >= Tmin, Max is Tmax, Min is Tmin.

maxmin([X|Xs], Max, Min, Tmax, Tmin):- X > Tmax, maxmin(Xs, Max, Min, X, Tmin).
maxmin([X|Xs], Max, Min, Tmax, Tmin):- X < Tmin, maxmin(Xs, Max, Min, Tmax, X).
maxmin([X|Xs], Max, Min, Tmax, Tmin):- X =< Tmax, X >= Tmin, maxmin(Xs, Max, Min,Tmax, Tmin).

% ----------------------------------------------------------------------------------

% ex.3.1

% same(List1,List2)
% are the two lists exactly the same?

same([],[]).
same([X|Xs],[X|Ys]):- same(Xs,Ys).

% relational
% same(X, Y). infinite yes
% same(X, [1, 2, 3]). yes
% same([1, 2, 3], X). yes

% ----------------------------------------------------------------------------------

% ex.3.2

% all_bigger(List1,List2)
% all elements in List1 are bigger than those in List2, 1 by 1
% example: all_bigger([10,20,30,40],[9,19,29,39])

all_bigger([], []).
all_bigger([X|Xs], [Y|Ys]):- X > Y, all_bigger(Xs, Ys).

% ----------------------------------------------------------------------------------

% ex.3.3

% sublist(List1,List2)
% List1 should contain elements all also in List2
% example: sublist([1,2],[5,3,2,1]).

search(X, [X|_]).
search(X, [_|Xs]) :- search(X, Xs).

sublist([], Y).
sublist([X|Xs], Y):- search(X, Y), sublist(Xs, Y).

% ----------------------------------------------------------------------------------

% ex.4.1

% seq(N,List)
% example: seq(5,[0,0,0,0,0]).

seq(0,[]).
seq(N,[0|T]):- N > 0, N2 is N-1, seq(N2,T).

% seq(X,[0,0,0,0,0]). error => not fully relational

% ----------------------------------------------------------------------------------

% ex.4.2

% seqR(N,List)
% example: seqR(4,[4,3,2,1,0]).

seqR(0, [0]).
seqR(N, [N|Xs]):- N2 is N-1, seqR(N2, Xs).

% ----------------------------------------------------------------------------------

% ex.4.3

% seqR2(N,List)
% example: seqR2(4,[0,1,2,3,4])

seqR2(N, L):- seqR2(N, 0, L).
seqR2(0, M, [M]). 
seqR2(N, M, [M|Xs]):- M2 is M+1, N2 is N-1, seqR2(N2, M2, Xs).

% ----------------------------------------------------------------------------------

% other1

% inv(List,List)
% example: inv([1,2,3],[3,2,1]).

inv([], []).
inv([X|Xs], Y):- inv(Xs, Y2), append(Y2, [X], Y).

% not fully relational

% ----------------------------------------------------------------------------------

% other2

% double(List,List)
% suggestion: remember predicate append/3
% example: double([1,2,3],[1,2,3,1,2,3]).

double([], []).
double(X, Y):- append(X, X, Y).

% fully relational

% ----------------------------------------------------------------------------------

% other3

% times(List,N,List)
% example: times([1,2,3],3,[1,2,3,1,2,3,1,2,3]).

times([], 0, []).
times(X, 1, X).
times(X, N, Y):- N2 is N-1, append(X, Y2, Y), times(X, N2, Y2).

% not fully relational

% ----------------------------------------------------------------------------------

% other4

% proj(List,List)
% example: proj([[1,2],[3,4],[5,6]],[1,3,5]).

proj([], []).
proj([[X|_]|Xs], [X|Y2]):- proj(Xs, Y2), Y = [X|Y2].

% per proj(X,  [1, 3, 5]). va in timeout, quindi not fully relational???