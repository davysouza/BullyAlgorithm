# Bully Algorithm

##### Federal University of São Carlos, campus Sorocaba / SP - Brazil

##### Bachelor of Computer Science

	Course: 
		Distributed Systems

	Professor: 
		Fábio Verdi, PhD

	Students:
		- Davy A. Souza
		- Douglas Santos

This project is a implementation of the Bully Algorithm. 

The Bully Algorithm is a programming mechanism that applies a hierarchy to nodes on a system, making a process coordinator or slave. This is used as a method in distributed computing for dynamically electing a coordinator by process ID number. The process with the highest process ID number is selected as the coordinator.

## Compiling

	javac valentao/*.java

## Running

Open 4 terminals:

	java valentao/Run 1

	java valentao/Run 2

	java valentao/Run 3

	java valentao/Run 4
  
Ctrl+C to kill a process.

If the leader falls, there will be a new election.

If some process back, he calls for a new election.

## References

##### Theory: [Bully Algorithm](https://en.wikipedia.org/wiki/Bully_algorithm)
##### Distributed Systems: [Distributed Systems: Principles and Paradigms (2nd Edition) - Andrew S. Tanenbaum](http://www.amazon.com/gp/product/0132392275?keywords=distributed%20system%20tanenbaum&qid=1454262678&ref_=sr_1_1&sr=8-1)

