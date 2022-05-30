# Priority Queue Manager
A program designed to simulate and manipulate an emergency room queue, with patients sorted by an assigned numerical priority value.

The program can: 
- Add patients to the queue (identified by name), along with an assigned priority value
- Remove patients by name
- Change the priority value of a patient
- Identify the patient with highest priority in the line
- Check whether a patient is in the queue (by name identifier)
- Remove all patients over/under a certain priority value
- Add patients to the end of the queue (i.e. with the lowest priority in the line
- List all patients in line, ordered by priority

*Note: All methods listed above maintain the ordering of the queue


# How the software works:
- A minHeap is created using a Java ArrayList to permit easy organisation and sorting of patients
- An interactive Menu class is used to permit users to access the program's functionality
- To reduce runtime, a HashMap and recursive methods are employed where possible
- Most functionality relies on retrieving info from the minHeap and maintaining its structure

# Testing: 
- The JUnit 5 framework was employed to create unit tests for all main methods of the PriorityQueue class, and to ensure proper functionality throughout the project's development.

