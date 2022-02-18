# Not another Tic Tac Toe

I know, I know. Yet another coding challenge, and it is Tic Tac Toe - again.

But wait - don't go. 

Yes, you are right, it sucks that we still get this stuff when we are applying for another job.

And yes I just could copy and paste one of the million existing examples.

But f*ck it!

Let's have some fun, but building something strange.

## How to build something strange & funny

(1) No UI

Sorry dear frontend colleagues but as a backend dev ... Most likely you already know my pain, let's go on.
But how will one or two play the game then you are wondering? They will use the url input field and beautiful JSON will appear
in its rawest form.

(2) No DB

I love Spring Data but to maximize the fun lets ignore it for this one time.

(3) Let us troll REST

One can find so many opinions about REST and there will be always this one person ...
again you probably already know where this path will lead us.
Therefore, grab your troll helmet, this will be fun.
I am going to use GET only! Yes GET only, no discussion about PUT vs PATCH, there will be only one http verb to rule them all.
And my media type will be JSON as mentioned above, as JSON is all I need, all I want, not only for Christmas.
BTW: If you every saw some real nice usecase for HATEOAS, then please PLEASE write me a DM, I still don't get it - 
so please convert me.

(4) A player can also win by smashing F5

As we don't provide a UI but still want to use the browser for playing - one have to refresh the page manually - over and over again.
Guess what, this is not a bug, it is a feature. Why you are wondering? Are you familiar with a chess clock? 
In a nutshell each player in a game of chess has a limited amount of time for his/her turn(s), and a chess clock tracks it.
As this isn't chess, we don't care about time and will use "number of reloads" instead.
For example, if I have made my genius move, then it is time for you to respond. In the meantime I have to press F5 to reload the page
to see if you made your turn. As I don't want to wait forever, the game will track my numbers of reload and if this number hits a
specific threshold then I will win automatically. Your bad, but keep in mind, you can return this favor it is my turn.

(5) Be nice

My first idea was to name this topic "No errors", but who am I kidding here? Of course, you will find edge cases to trigger a nasty error.
Users will always do. But let us try to provide funny and strange feedback as far as we can,
so one will smile instead of running away frustrated.

(6) Event sourcing

Let us try to use events to track the state of the game.

(7) Open API aka Swagger

Finally, I would like to include Swagger, just to get some experience with it.
No idea if we will get a benefit from it, but why not?



