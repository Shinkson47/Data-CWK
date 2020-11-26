# Data-CWK

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/375ff724ad74439fa5e02057cb9ce7d2)](https://app.codacy.com/gh/Shinkson47/Data-CWK?utm_source=github.com&utm_medium=referral&utm_content=Shinkson47/Data-CWK&utm_campaign=Badge_Grade_Settings)

CTEC2909 Data structures coursework

This repository is for the 2020/2021 Data Structures and Algorithyms coursework team project.
See the specification on blackboard.

# A Note on Execution

The repo provided (or found at https://github.com/Shinkson47/Data-CWK) has project metas configured for both Eclipse and IntelliJ.

The compile output was produced by IntelliJ, and seems to be the causation of corrupted text within GUIDemo when the *.class files are executed in eclipse; i couldn’t get eclipse to build the project correctly to validate this. An image of how the GUI looks when rendered correctly is provided in the repo under /Documentation/GUIDemo.png

### __The Class path requires:__
 - JUnit (4)
 - JavaFX
 - OPEX

OPEX is a library of my own creation which is used to simplify the process of showing a JavaFX window. It’s found in /lib/OPEX.jar, within the repo. 

__nb__ Showing the GUI is the only use of the library in this repo.

### __There are two mains,__ _OperationImplDemo#main, & GUIDemo#main_.
OperationImplDemo is a source only demo, showing how to invoke operations on the data structure; whilst GUIDemo is a utility which allows a user to manually manipulate it.

### __The compile output was produced by IntelliJ__,
and seems to be the causation of corrupted text within GUIDemo when the *.class files are executed in eclipse; i couldn’t get eclipse to build the project to test this.
