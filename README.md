# Data-CWK

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/375ff724ad74439fa5e02057cb9ce7d2)](https://app.codacy.com/gh/Shinkson47/Data-CWK?utm_source=github.com&utm_medium=referral&utm_content=Shinkson47/Data-CWK&utm_campaign=Badge_Grade_Settings)

CTEC2909 Data structures coursework

This repository is for the 2020/2021 Data Structures and Algorithyms coursework team project.
See the specification on blackboard.

# A Note on Execution
The repo provided (or found at https://github.com/Shinkson47/Data-CWK) has project metas configured for both Eclipse and IntelliJ. 

### __The Class path requires:__
- JUnit (4)
- JavaFX
- OPEX

OPEX is a library of my own creation which is used to simplify showing a JavaFX utility window. It’s found in /lib/OPEX.jar, within the repo. 

__nb__ Showing the GUI is the only use of the library in this repo.

### __There are two mains,__ _OperationImplDemo#main_, & _GUIDemo#main_.
_OperationImplDemo_ is a __source only demo__, showing how to invoke operations on the data structure; whilst GUIDemo is a utility which allows a user to manually manipulate it.

### __The compile output was produced by IntelliJ__,
and seems to be the causation of corrupted text within GUIDemo when the *.class files are executed in eclipse; i couldn’t get eclipse to build the project to test this.


# Repository guidelines
All work must be completed on relavent branches, and never directly to master.
Only completed content that's been peer reviewed by at least two others will be allowed to be merged to the master.

Code must also pass codacy checks before it may be merged. 
Check what codacy says about your pull request, and ensure your code is top notch.
