# Graph-Assignment

<h2 data-unsp-sanitized="clean">Programming Assignment 5</h2>
<h2 data-unsp-sanitized="clean">Relationship Graph Algorithms</h2>
<h4 data-unsp-sanitized="clean">In this assignment, you will implement some useful algorithms that apply
to relationship graphs of the Facebook kind.</h4>
<h3 data-unsp-sanitized="clean">Worth 100 points (10% of course grade)</h3> 
<h3 data-unsp-sanitized="clean">Posted Monday, July 30</h3>
<h3 data-unsp-sanitized="clean">Due Tuesday, August 14, 1:15 AM 
(<font color="red">WARNING!! NO GRACE PERIOD</font>)</h3>
<h3 data-unsp-sanitized="clean"><font color="red">There is NO extended deadline!!</font></h3>
</center>


<hr>

<ul>
<li>You will work on this assignment individually.
Read <a href="https://www.cs.rutgers.edu/academic-integrity/programming-assignments" data-unsp-sanitized="clean">
DCS Academic Integrity Policy for Programming Assignments</a> - you are responsible for abiding 
by the policy. In particular, note that "<b>All Violations of the Academic Integrity Policy will 
be reported by the instructor to the appropriate Dean</b>".

</li><li><h3 data-unsp-sanitized="clean">IMPORTANT - READ THE FOLLOWING CAREFULLY!!!</h3>

<p><font color="red">Assignments emailed to the instructor or TAs will
be ignored--they will NOT be accepted for grading. <br>
We will only grade submissions in Sakai.</font><br>

</p><p><font color="red">If your program does not compile, you will not get any credit.</font> 

</p><p>Most compilation errors occur for two reasons: 
</p><ol>
<li> You 
are programming outside Eclipse, and you delete the "package" statement at the top of the file. 
If you do this, you are changing the program structure, and it will not compile when we
test it.
</li><li> You make some last minute 
changes, and submit without compiling. 
</li></ol>

<h3 data-unsp-sanitized="clean">To avoid these issues, (a) START EARLY, and
give yourself plenty of time to work through the assignment, and (b) Submit a version well
before the deadline so there is at least something in Sakai for us to grade. And you can
keep submitting later versions (up to 10) - we will 
accept the LATEST version.</h3>
</li></ul>

<hr>
<ul>
<li><a href="https://www.ilab.cs.rutgers.edu/~hc691/112/summer_2018/friend_description.html#bg" data-unsp-sanitized="clean">Background</a>
</li><li><a href="https://www.ilab.cs.rutgers.edu/~hc691/112/summer_2018/friend_description.html#algo" data-unsp-sanitized="clean">Algorithms</a>
</li><li><a href="https://www.ilab.cs.rutgers.edu/~hc691/112/summer_2018/friend_description.html#impl" data-unsp-sanitized="clean">Implementation</a>
</li><li><a href="https://www.ilab.cs.rutgers.edu/~hc691/112/summer_2018/friend_description.html#submit" data-unsp-sanitized="clean">Submission</a>
</li></ul>

<p></p><hr>
<p><a name="bg" data-unsp-sanitized="clean"></a></p><h3 data-unsp-sanitized="clean"><a name="bg" data-unsp-sanitized="clean">Background</a></h3>

<p>In this program, you will implement some useful algorithms for
graphs that represent relationships (e.g. Facebook). A relationship graph
is an undirected graph without any weights on the edges. It is a simple
graph because there are no self loops (a self loop is an edge from a vertex to
itself), or multiple edges (a multiple edge means more than edge between a
pair of vertices).

</p><p>The vertices in the graphs for this assignment represent two kinds of people:
students and non-students. Each vertex will store the name of the person.
If the person is a student, the name of the school will also be stored.

</p><p>Here's a sample relationship graph:
</p><pre>     (sam,rutgers)---(jane,rutgers)-----(bob,rutgers)   (sergei,rutgers)
                          |                 |             |
                          |                 |             |
                     (kaitlin,rutgers)   (samir)----(aparna,rutgers)
                          |                            |
                          |                            |
  (ming,penn state)----(nick,penn state)----(ricardo,penn state)
                          |
                          |
                     (heather,penn state)


                   (michele,cornell)----(rachel)     
                          | 
                          | 
     (rich,ucla)---(tom,ucla)
</pre>
Note that the graph may not be connected, as seen in this example in which there
are two "islands" or cliques that are not connected to each other by any edge.
Also see that all the vertices represent students with names of schools, except for
<tt>rachel</tt> and <tt>samir</tt>, who are not students.

<p></p><hr>
<p><a name="algo" data-unsp-sanitized="clean"></a></p><h3 data-unsp-sanitized="clean"><a name="algo" data-unsp-sanitized="clean">Algorithms</a></h3>

<ol>
<li>
<p><a name="" data-unsp-sanitized="clean"></a></p><h4 data-unsp-sanitized="clean"><a name="" data-unsp-sanitized="clean">Shortest path: Intro chain </a></h4>

<p><tt>sam</tt> wants an intro to <tt>aparna</tt> through friends and friends
of friends. There are two possible chains of intros:
</p><pre>  sam--jane--kaitlin--nick--ricardo--aparna

            or

  sam--jane--bob--samir--aparna
</pre>
The second chain is preferable since it is shorter. 

<p>If <tt>sam</tt> wants to be introduced to <tt>michele</tt> through a chain of
friends, he is out of luck since there is no chain that leads from <tt>sam</tt> to
<tt>michele</tt> in the graph.

</p><p><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-yellow-aaddcf5c-0e41-4f83-8a64-58c91f7c6250 closeable" id="f0b5f499-2b09-4284-b55e-404b647e2bec" tabindex="0">Note that this algorithm does NOT have any restriction on the composition of the
vertices: a vertex along the shortest chain need NOT be a student at a particular
school, or even a student<span class="close"></span></span>. In other words, this algorithm is not about
students, let alone students at a particular school.
So, for instance, you may need to find the shortest path (intro
chain) from <tt>nick</tt> to <tt>samir</tt>, which will be:
</p><pre>   nick--ricardo--aparana--samir
</pre>
which consists of two penn state students, one rutgers student, and one non-student.

</li><li><p><a name="" data-unsp-sanitized="clean"></a></p><h4 data-unsp-sanitized="clean"><a name="" data-unsp-sanitized="clean">Cliques: Student cliques at a 
school</a></h4>

<p>Students tend to form cliques with their friends, which creates
islands that do not connect with each other. If these cliques could be identified,
particularly in the student population at a particular school,
introductions could be made between people in different cliques to build larger
networks of relationships at that school. 

</p><p>In the sample graph, there are two cliques of students at rutgers:
</p><pre>     (sam,rutgers)---(jane,rutgers)-----(bob,rutgers)    (sergei,rutgers)
                          |                                |
                          |                                |
                     (kaitlin,rutgers)             (aparna,rutgers)
</pre>
<p><font color="red">Note that in the graph these are not islands since
<tt>samir</tt> connects them. However, since <tt>samir</tt> is not a student
at rutgers, it results in two cliques of rutgers students that
don't know each other through another rutgers student.</font>

</p><p>At penn state, there is a single clique of students:
</p><pre>   (ming,penn state)----(nick,penn state)----(ricardo,penn state)
                          |
                          |
                     (heather,penn state)
</pre>
<p>Also, a single clique of students at ucla:
</p><pre>     (rich,ucla)---(tom,ucla)
</pre>
<p>And a single clique of students at cornell:
</p><pre>             (michele,cornell)
</pre>

<p></p></li><li><p><a name="" data-unsp-sanitized="clean"></a></p><h4 data-unsp-sanitized="clean"><a name="" data-unsp-sanitized="clean">Connectors: Friends who keep friends together </a></h4>

<p>If <tt>jane</tt> were to leave rutgers, <tt>sam</tt> would no longer be able to
connect with anyone else--<tt>jane</tt> was the "connector" who could pull
<tt>sam</tt> in to hang out with her other friends. Similarly, <tt>aparna</tt>
is a connector, since without her, <tt>sergei</tt> would not be able to 
"reach" anyone else. (And there are more connectors in the graph...)

</p><p>On the other hand, <tt>samir</tt> is not a connector. Even if he were to leave,
everyone else could still "reach" whoever they could when <tt>samir</tt> was there,
even though they may have to go through a longer chain.

</p><p><b><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce closeable" id="becf28dc-7bce-4e68-a531-9689b7c69f73" tabindex="0">Definition: In an undirected graph, vertex <span class="close"></span></span><tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">v</span></tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce"> is a connector if
there are at least two other vertices </span><tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">x</span></tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce"> and </span><tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">w</span></tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce"> for which 
</span><em><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">every</span></em><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce"> path between </span><tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">x</span></tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce"> and </span><tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">w</span></tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce"> goes through </span><tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">v</span></tt><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-red-aa94e3d5-ab2f-4205-b74e-18ce31c7c0ce">.</span> </b>

</p><p>For example, <tt>v=jane</tt>, <tt>x=sam</tt>, and <tt>w=bob</tt>.

</p><p>Finding all connectors in an undirected graph can be done using DFS (depth-first search),
by keeping track of two additional
quantities for every vertex <tt>v</tt>. These are:
</p><ul>
<li><tt>dfsnum(v)</tt>: This is the dfs number, assigned when a vertex is visited, dealt out
in increasing order.
</li><li><tt>back(v)</tt>: This is a number that is initially assigned when a vertex is
visited, and is equal to <tt>dfsnum</tt>, but can be changed later 
as follows:
<ul>
<li>When the DFS backs up from a neighbor, <tt>w</tt>, to <tt>v</tt>,
if <tt>dfsnum(v)</tt> &gt; 
<tt>back(w)</tt>, then <tt>back(v)</tt> is set to min(<tt>back(v)</tt>,<tt>back(w)</tt>)
</li><li>If a neighbor, <tt>w</tt>, is already visited then
<tt>back(v)</tt> is set to min(<tt>back(v)</tt>,<tt>dfsnum(w)</tt>)
</li></ul>
</li></ul>

<p></p><div class="conndef">
When the DFS backs up from a neighbor, <tt>w</tt>, to <tt>v</tt>,
if <tt>dfsnum(v)</tt> ≤ <tt>back(w)</tt>, then <tt>v</tt> is identified as
a connector, IF <tt>v</tt> is NOT the starting point
for the DFS.

<p>If <tt>v</tt> is a starting point for DFS, it can be a connector, but
another check must be made - see the examples below. The examples don't tell
you how to identify such cases--you have to figure it out.
</p></div>

<p></p><p>Here are some examples that show how this works. 

</p><ul>
<li>Example 1: (B is a connector)
<pre>    A--B--C
</pre>
Neighbors for a vertex are stored in adjacency linked lists like this:
<pre>  
  A: B
  B: C,A
  C: B
</pre>
The DFS starts at A. 
<pre>  dfs @ A  1/1  (dfsnum/back)
      dfs @ B 2/2
          dfs @ C 3/3
              neighbor B is already visited =&gt; C 3/2
          dfsnum(B) &lt;= back(C) <font color="red">[B is a CONNECTOR]</font>
          nbr A is already visited =&gt; B 2/1
      dfsnum(A) &lt;= back(B) <font color="red">[A is starting point of DFS, NOT connector in this case]</font>
</pre>

<p></p></li><li>Example 2: (B is a connector)
<pre>   A--B--C
</pre>
The same example as the first, except DFS starts at B. This shows how even thought B is
the starting point, it is still identified (correctly) as a connector. The 
trace below is not complete because it does not show HOW B is determined to be a 
connector in the last line - that's for you to figure out. Neighbors are
stored in adjacency linked lists as in Example 1.
<pre>  dfs @ B  1/1 
      dfs @ C 2/2
          nbr B is already visited =&gt; C 2/1
      dfsnum(B) &lt;= back(C) <font color="red">[B is starting point, NOT connector]</font>
      dfs @ A 3/3
          nbr B is already visited =&gt; A 3/1
      dfsnum(B) &lt;= back(A) <font color="red">[B is starting point, but IS a CONNECTOR in this case]</font>
</pre>

<p></p></li><li>Example 3: (B and D are connectors)
<pre>    A---B---C
        |   |
        E---D---F
</pre>
<p>Neighbors stored in adjacency linked lists like this:
</p><pre>  A: B
  B: E,C,A
  C: D,B
  D: F,E,C
  E: D,B
  F: D
</pre>
DFS starts at A.
<pre>  dfs @ A 1/1
      dfs @ B 2/2
          dfs @ E 3/3
              dfs @ D 4/4
                  dfs @ F 5/5
                      nbr D is already visited =&gt; F 5/4
                  dfsnum(D) &lt;= back(F) <font color="red">[D is a CONNECTOR]</font>
                  nbr E already visited =&gt; D 4/3
                  dfs @ C 6/6
                      nbr D already visited =&gt; C 6/4
                      nbr B already visited =&gt; C 6/2
                  dfsnum(D) &gt; back(C) =&gt; D 4/2
              dfsnum(E) &gt; back(D) =&gt; E 3/2
              nbr B is already visited =&gt; E 3/2
          dfsnum(B) &lt;= back(E) <font color="red">[B is a CONNECTOR]</font>
          nbr C is already visited =&gt; B 2/2
          nbr A is already visited =&gt; B 2/1
      dfsnum(A) &lt;= back(B) <font color="red">[A is starting point, NOT a connector in this case]</font>
</pre>

<p></p></li><li>Example 4: (B and D are connectors)
<pre>    A---B---C
        |   |
        E---D---F
</pre>
<p>Same graph as in Example 3, but neighbors are stored in adjacency
linked lists in a different sequence:
</p><pre>  
  A: B
  B: A,C,E
  C: B,D
  D: C,E,F
  E: B,D
  F: D
</pre>
DFS starts at D, Connectors are still correctly identified as B and D.
<pre>  dfs @ D 1/1
      dfs @ C 2/2
          dfs @ B 3/3
              dfs @ A 4/4
                  nbr B is already visited =&gt; A 4/3
              dfsnum(B) &lt;= back(A) <font color="red">[B is a CONNECTOR]</font>
              nbr C is already visited =&gt; B 3/2
              dfs @ E 5/5
                  nbr B is already visited =&gt; E 5/3
                  nbr D is already visited =&gt; E 5/1
              dfsnum(B) &gt; back(E) =&gt; B 3/1
          dfsnum(C) &gt; back(B) =&gt; C 2/1
          nbr D is already visited =&gt; C 2/1
      dfsnum(D) &lt;= back(C) <font color="red">[D is starting point, NOT connector]</font>
      dfs @ F 6/6
          nbr D is already visited =&gt; F 6/1
      dfsnum(D) &lt;= back(F) <font color="red">[D is starting point, is a CONNECTOR]</font>
</pre></li></ul>

</li></ol>

<p></p><hr>
<a name="impl" data-unsp-sanitized="clean"><h3 data-unsp-sanitized="clean">Implementation</h3></a>

<p>Download the attached <tt>friends_project.zip</tt> file to your
computer. DO NOT unzip it. Instead, follow the instructions on the Eclipse page 
under the section "Importing a Zipped Project into Eclipse" to get the entire
project, called <tt>Friends</tt>, into your Eclipse workspace.

</p><p>Here are the contents of the project:
</p><ul>
<li> A class, <tt>apps.Friends</tt>. This is where you will
fill in your code, details follow.
</li><li>A class, <tt>apps.Graph</tt>, that holds the graph on which the
the friends algorithms operate. 
<ul>
<li>The file <tt>Graph.java</tt> defines
supporting classes <tt>Friend</tt> and <tt>Person</tt> that are used
to store a graph in adjacency linked lists format.
</li><li>The file <tt>Graph.java</tt> also defines a class called <tt>Edge</tt>
that you are free to use in your implementation in the <tt>Friends</tt>
class.
</li></ul>
You will NOT change ANY of the contents of <tt>Graph.java</tt>.
</li><li>Classes <tt>structures.Queue</tt> and
<tt>structures.Stack</tt> that you may use in your implementation
in the <tt>apps.Friends</tt> class. You will NOT change ANY of the contents of 
<tt>Stack.java</tt> and <tt>Queue.java</tt>.
</li></ul>

<p>NOTE: You will need to write your own driver to test your implementation.
</p>

<p>Every graph that on which you might want to run your algorithms
will have the following input format - the sample graph input here
is for the relationship graph shown in the <tt>Background</tt> section
above. (The <tt>Graph</tt> class constructor should be passed
a <tt>Scanner</tt> with the input file as its target.)
</p><pre>   
   15
   sam|y|rutgers
   jane|y|rutgers
   michele|y|cornell
   sergei|y|rutgers
   ricardo|y|penn state
   kaitlin|y|rutgers
   samir|n
   aparna|y|rutgers
   ming|y|penn state
   nick|y|penn state
   bob|y|rutgers
   heather|y|penn state
   rachel|n
   rich|y|ucla
   tom|y|ucla
   sam|jane
   jane|bob
   jane|kaitlin
   kaitlin|nick
   bob|samir
   sergei|aparna
   samir|aparna
   aparna|ricardo
   nick|ricardo
   ming|nick
   heather|nick
   michele|rachel
   michele|tom
   tom|rich
</pre>
<p>The first line has the number of people in the graph (15 in this case). 

</p><p>The next set of lines has information about the people in the graph, one line per
person (15 lines in this example), with the '|' used to separate the fields. 
In each line, the first field is the name of
the person. <span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-yellow-aaddcf5c-0e41-4f83-8a64-58c91f7c6250 closeable" id="fe29317d-dd19-4a33-9d67-34dad39595a4" tabindex="0">Names of people can have any character except '|', and are case
<span class="close"></span></span><em><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-yellow-aaddcf5c-0e41-4f83-8a64-58c91f7c6250">insensitive</span></em><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-yellow-aaddcf5c-0e41-4f83-8a64-58c91f7c6250">. The second field is 'y' if
the person is a student, and 'n' if not</span>. The third field is 
only present for students, and is the name of the school the student attends.
The name of a school can have any character except '|', and is
case <em>insensitive</em>. <font color="red">No two people will have the
same name.</font>

</p><p><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-yellow-aaddcf5c-0e41-4f83-8a64-58c91f7c6250 closeable" id="af96fcf5-2173-4e0c-b8ac-8a0b383e0928" tabindex="0">The last set of lines, following the people information, lists the relationships
between people, one relationship per line<span class="close"></span></span>.<span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-yellow-aaddcf5c-0e41-4f83-8a64-58c91f7c6250 closeable" id="b2aab0f0-d8f6-41f9-9a26-867e4c9bfea2" tabindex="0"> Since relationship works both ways, any
relationship is only listed once, and the order in which the names of the friends
is listed does not matter<span class="close"></span></span>.



</p><p>You will complete the following static methods
in the <tt>apps.Friends</tt> class, to 
implement the three algorithms in the previous section.
(All of these methods take a <tt>Graph</tt> instance as a parameter, aside
from other possible inputs detailed below.)

</p><p>
</p><h4 data-unsp-sanitized="clean">Methods</h4>

<ol>
<p></p><li><a name="" data-unsp-sanitized="clean"><h4 data-unsp-sanitized="clean">(35 pts) shortestPath</h4></a>
<ul>
<li>Input: Name of person who wants the intro, and the name of the other person.
For instance, inputs could be "sam" and "aparna" for the graph in the 
<tt>Background</tt> section. 
(Neither of these, nor any of the intermediate people in the chain, are
required to be students, in the same school or otherwise.)
</li><p></p><li>Result: The shortest chain (list) of people in the graph
starting at the first and ending at the second, returned in an array list.

<p>For example, if the inputs are <tt>sam</tt> and <tt>aparna</tt>
(<tt>sam</tt> wants an intro to <tt>aparna</tt>), then the shortest
chain from <tt>sam</tt> to <tt>aparna</tt> is
<tt>[sam,jane,bob,samir,aparna]</tt>

</p><p>(This represents the path <tt>sam--jane--bob--samir--aparna</tt>)</p>

<p><font color="red">If there
is more than one shortest path, ANY of them is acceptable.</font>

</p><p>If there is no way to get from the first person to the second person, then the 
returned list is empty (null or zero-sized array list).
</p></li></ul>
<p></p></li><li><a name="" data-unsp-sanitized="clean"><h4 data-unsp-sanitized="clean">(25 pts) cliques</h4></a>
<ul>
<li>Input: Name of school for which cliques are to be found, e.g. "rutgers"
<p></p></li><li>Result: The names of people in each of the cliques, in any order,
returned in an array list of array lists. Moreover, the cliques themselves could
be in any order in the top level array list.

<p>For the example cited in the <tt>Cliques</tt> part  of the 
<tt>Algorithms</tt> section above, with input <tt>rutgers</tt>,
the result is:
</p><pre>   [[sam,jane,bob,kaitlin],[sergei,aparna]]
</pre>
In other words, an array list that has two cliques, each of which is
an array list. 

<p>The names in the clique array list can be in any order.
So, the same cliques could have been returned as:
</p><pre>   [[jane,sam,kaitlin,bob],[aparna,sergei]]
</pre>
and it would be correct. 

<p>The cliques themselves can be in any order within the top level array
lists, so the following variation (for example) is also acceptable:
</p><pre>   [[sergei,aparna],[sam,jane,bob,kaitlin]]
</pre>

<p><font color="red">However, names must not be repeated in a clique.</font></p>

<p><span class="bbe146e9-2a0c-4ae8-b644-8f3c8d83bcf0 default-yellow-aaddcf5c-0e41-4f83-8a64-58c91f7c6250 closeable" id="d1be3644-5ca3-4365-b71a-7988dc852649" tabindex="0">If there are no students in the input school, the result is empty
(null or zero-sized array list).<span class="close"></span></span>

</p></li></ul>
<p></p></li><li><a name="" data-unsp-sanitized="clean"><h4 data-unsp-sanitized="clean">(40 pts) connectors</h4></a>

<ul>
<li>Input: None
</li><li>Result: Names of all connectors, in any order, returned in an array list.
<p>In the sample relationship graph of the <tt>Background</tt> section,
the connectors list is <tt>[jane,aparna,nick,tom,michele]</tt>. Any 
other perumtation of the names in the list is fine, since the order
does not matter.

</p><p><font color="red">Names in the list must not be repeated.</font></p>
</li></ul></li></ol>

<h4 data-unsp-sanitized="clean">Implementation Rules</h4>
Do NOT change ANY of the contents of <tt>Graph.java</tt>,
<tt>Queue.java</tt>, and <tt>Stack.java</tt>.
<p>In <tt>Friends.java</tt>:
</p><ul>
<li>Do NOT change the package name on the first line.
</li><li>Do NOT add or remove any import statements. (The existing <tt>import java.util.*</tt>
statement allows you to use any class in the <tt>java.util</tt>
package.
<br> Note: <tt>java.util</tt> defines a <tt>Stack</tt> class, but
it will be overridden by the <tt>structures.Stack</tt> class that is
imported.
</li><li>Do NOT change the headers of any of the existing methods in 
ANY way.
</li><li>Do NOT add any class fields or inner classes.
</li><li>You MAY add helper methods, but you must make them
<tt>private</tt>.
</li></ul>

<ul>

</ul>

<p></p><hr>

<p></p><h3 data-unsp-sanitized="clean"><a name="submit" data-unsp-sanitized="clean">Submission</a></h3>

<p>Submit your <tt>Friends.java</tt> file.

</p>
