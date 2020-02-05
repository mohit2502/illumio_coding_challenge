# Illumio-Coding-Challenge
This Repo contains solution for the Firewall coding challenge

# Testing
Testing can be done in two ways either running the Firewall.Java directly or using junit class FirewallTest.java
I made sure while testing that i will consider the edge cases for the problem for range of ports and IPaddress. 

# Code Explanation and Implementation
I came up with an initial solution where I am more focused on getting the result once the initial rules are build for the Firewall so I am using the HashMap in order to put nad get the data from rules. HasMap put and get operation time complexity is O(1) with assumption that key-value pairs are well distributed across the buckets. As I specified the hashcode fuction so distributed key value pair in hashMap will help in o(1) time complexity.Although I have sacrified the space complexity as compared to time complexity.

•Below are important parts in my code implementation:

	Firewall.Java Main class for rule and accept/reject a packet.

	NetworkRule.java Supporting wrapper for network rules

	FirewallTest.java junit test class to test functionality of code.

•Read the rules from csv line by line and break down into 4 different Maps based on direction and protocol. We are storing each network rule in the 4 maps.Use hash function to generate a hashcode about the four rules and store it in the HashMap<key,value>, Key is the hashcode of network rule from class NetworkRule.java , Value is boolean(true) for rule addition.

•After Network rule are build we use the accept function to check if certain packet falls in rules category or not.For this we again calculate the hashcode of the packet to build a rule and verify it with the existing map which contains hascode as key.


# Improvements and Refinements
Advance data structure and algorithms concepts can be included to improve and refine this solution.
1. Backed HashMap by disk storage or off-heap-memory. It is a fast, scalable and easy to use embedded Java database engine.
2. Implement cache to store the most accessed rules to improve performance.
3. Design custom data structure to beat the performance.
4. Use advance data structure like Trie to improve the space complexity.
 
# Teams
Below are my preferences from highest to least.

Platform Team(first choice)
Policy Team(second choice)
Data Team(third choice)
