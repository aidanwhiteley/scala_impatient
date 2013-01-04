<h1>Project Details</h1>
<h2>Scala For The Impatient Exercises</h1>
<p>
This repository contains some sample solutions to the exercises in Cay Hortsmann's book
Scala For The Impatient.
</p>
<p>
Like everything else, it is a work in progress. It contains progress to date noting that 
I haven't spent the time to create solutions for those exercises that either:
</p>
<ul>
<li>don't really interest me - e.g. exercises for the chapters on packages and annotations</li>
<li>exercises that don't readily allow test driven development - e.g. those that just want output printed to the console (albeit I've converted many of these to return testable Strings)</li>
</ul>
<h3>Is it any good?</h3>
<p>
Is the code in the solutions any good? I very much doubt it! As a long term Java user (and previous user of many other imperative languages), I know that I 
haven't really got the many of the Scala idioms "down" yet. It is worth mentioning that despite having just completed Martin Odersky's course  
<a href="https://www.coursera.org/course/progfun">Functional Programming Principles In Scala</a>, I am trying to create solution
answers only using concepts introduced in the chapters preceding each exercise. So, for example, the early chapter solutions contain lots of mutable 
variables, explicit looping etc
</p>
<h3>Project build</h3>
<p>
The project can be built with SBT using the build.sbt file. This should bring down the dependencies for testing using ScalaTest
and supporting running the tests as JUnits if required. Running &quot;test&quot; from sbt should run the 130 or so ScalaTest based tests
which, at the time of writing, should all pass (with 2 currently marked as ignored.
</p><p>
The repository doesn't include any Eclipse related files although Eclipse is being used during the development. The SBT eclipse plugin
can be added using 
<code>
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.0")
</code>
and then the project files created using 
<code>sbt eclipse</code>.
</p>

<h2>Git and GitHub</h2>
<p>As a long term Subversion user, I am using this project to learn Git and GitHub. Apologies for any faux pas!
</p>

<h2>Acknowledgements</h2>
<p>There are plenty of GitHub repositories that contain exercises for one or two chapters of Scala For The Impatient.
Alexander Mikhailov's <a href="https://github.com/hempalex/scala-impatient">repository</a> proved a more useful source of inspiration/ideas when stuck! 
</p>