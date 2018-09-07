package apps;

import structures.Queue;
import structures.Stack;

import java.util.*;

public class Friends {
	
	private static boolean bfs(Graph g, String p1, String p2) {
		ArrayList<String> list = new ArrayList<String>();
		
		int p1Index = g.map.get(p1);	//index of the first word in array		
		
		ArrayList<Person> visited = new ArrayList<Person>();
		Queue<Person> queue = new Queue<Person>();
		if(p1.equals(p2)) {
			return true;
		}
		//Enque start onto process
		queue.enqueue(g.members[p1Index]);
		//Add start to visited
		visited.add(g.members[p1Index]);
		//While queue is not empty
		while(!queue.isEmpty()) {
			//Dequeue the current node from queue
			Person currentNode = queue.dequeue();
			//For all neighbors of current
			for(Friend ptr = currentNode.first; ptr != null; ptr = ptr.next) {
				if(visited.contains(g.members[ptr.fnum])) {
					continue;
				}			
				if(g.members[ptr.fnum].name.equals(p2)) {
					return true;
				}
				queue.enqueue(g.members[ptr.fnum]);
			}
			
			visited.add(currentNode);
		}
		return false;
	}
	
	private static boolean dfs(Graph g, String p1, String p2) {
		ArrayList<String> list = new ArrayList<String>();
		
		int p1Index = g.map.get(p1);	//index of the first word in array		
		int p2Index = g.map.get(p2);	//index of the second word in array
		
		Person p1StringAsPerson = g.members[p1Index];
		Person p2StringAsPerson = g.members[p2Index];
		
		
		//BFS
		ArrayList<Person> visited = new ArrayList<Person>();
		Stack<Person> stack = new Stack<Person>();
		if(p1.equals(p2)) {
			return true;
		}
		//Enque start onto process
		stack.push(g.members[p1Index]);
		//Add start to visited
		visited.add(g.members[p1Index]);
		//While queue is not empty
		while(!stack.isEmpty()) {
			//Dequeue the current node from queue
			Person currentNode = stack.pop();
			//For all neighbors of current
			for(Friend ptr = currentNode.first; ptr != null; ptr = ptr.next) {
				//if neighbor is in visited, continue
				if(visited.contains(g.members[ptr.fnum])) {
					continue;
				}
				//if neighbor == finish
				if(g.members[ptr.fnum].name.equals(p2)) {					
//					for(Person person: visited) {
//						System.out.print(person.name + ", ");
//					}
					return true;
				}
				//Enqueue neighbor onto queue
				stack.push(g.members[ptr.fnum]);
			}
			
			visited.add(currentNode);
		}		
		return false;
	}
	
	private static ArrayList<Person> reverseList(ArrayList<Person> list) {
		for(int i = 0, j = list.size() - 1; i < j; i++) {
   	        list.add(i, list.remove(j));
   	    }
   	    return list;
	}
	
	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {		
		/** COMPLETE THIS METHOD **/
		if(bfs(g, p1, p2) == false) {
//			System.out.println("A route does not exsist");
			return null;
		}
		
		ArrayList<Person> result = new ArrayList<Person>();
		ArrayList<String> names = new ArrayList<String>();				
		
		int p1Index = g.map.get(p1);	//index of the first word in array		
		int p2Index = g.map.get(p2);	//index of the second word in array
		
		Person start = g.members[p1Index]; //first name as Person
		Person end = g.members[p2Index]; //Second name as Person
		
		HashMap<Person, Boolean> visited = new HashMap<Person, Boolean>();
		HashMap<Person, Person> previousNodes = new HashMap<Person, Person>(); //nodes before current
		
	    Queue<Person> queue = new Queue<Person>();
	    Person current = start;
	    queue.enqueue(current);
	    visited.put(current, true);
	    while(!queue.isEmpty()){
	        current = queue.dequeue();
	        if (current.name.equals(end.name)){
	            break;
	        } else {
	        		Friend node = current.first;
	        		while(node != null) {
	        			if(!visited.containsKey(g.members[node.fnum])){
	        				queue.enqueue(g.members[node.fnum]);
	        				visited.put(g.members[node.fnum], true);
	        				previousNodes.put(g.members[node.fnum], current);
		             }	        			
	        			node = node.next;
	        		}
	        }
	    }
	    Person node = end;
	    while(node != null) {
	    		result.add(node);
	    		node = previousNodes.get(node);
	    }

	    result = reverseList(result);
	    
	    for(Person person: result) {
	    		names.add(person.name);
	    }
	   
//	    System.out.println(names.toString());
//	    System.out.println();
		return names;
	}
	
	private static boolean listContains(String target, ArrayList<ArrayList<String>> list) {
		
		int counter = 0;
		int slowPtr;
		int fastPtr;
		for(slowPtr = 0; slowPtr < list.size(); slowPtr++) {
			for(fastPtr = 0; fastPtr < list.get(counter).size(); fastPtr++) {
//				System.out.println(list.get(slowPtr).get(fastPtr).equals(target));
				if(list.get(slowPtr).get(fastPtr).equals(target)) {
					return true;
				}
			}	
			fastPtr = 0;
			counter++;
		}
		
		return false;
	}
	
	private static int getIndexOfNameInList(String target, ArrayList<ArrayList<String>> list) {
		int counter = 0;
		while(counter<list.size()) {
			ArrayList<String> currentListPosition = list.get(counter); 
			if(currentListPosition.contains(target)) {
				return counter;
			}
			counter++;
		}
		
		return -1;
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {		
		/** COMPLETE THIS METHOD **/	
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		//fill hash
		int counter = 0;
		while(counter < g.members.length) {
			visited.put(counter, false);
			counter++;
		}
		
		for(Person person: g.members) {
			int personIndex = g.map.get(person.name);
			if(!person.student || visited.get(personIndex)) {
				continue;
			}			
			ArrayList<String> clique = new ArrayList<String>();			
			cliqueRec(g, person, school, visited, !visited.get(personIndex), clique);

			if(clique.size() > 0) {
				result.add(clique);
			}
			
		}
//		System.out.println(result.toString());
		return result;
		
	}
	
	private static void cliqueRec(Graph graph, Person person, String school, HashMap<Integer, Boolean> visited, boolean personHasNotBeenVisited, ArrayList<String> clique) {		
		if(personHasNotBeenVisited) {
			//if the person hasn't been visited...
			if(person.student && person.school.equals(school)) {
				//...and the person is a student that goes to the same school, add the person to the clique
//				System.out.println(person.name);
				clique.add(person.name);
			}
		}		
		//System.out.println("Person has been visited: "+visited[graph.map.get(person.name)]);
		visited.put(graph.map.get(person.name), true);
		//loop through the persons friends
		Friend friend = person.first;
		while(friend != null) {
			Person current = graph.members[friend.fnum];
//			System.out.println("Current friend: "+current.name);
			//if the friend hasn't been visited...
			if(!visited.get(friend.fnum)) {
				//...and the friend is a student that goes to the same school
				if(current.student && current.school.equals(school)) {
					//call cliqueRec with the current friend as person, and whether or not that person has not been visited already
					//System.out.println("************ New recursive call *************");
					cliqueRec(graph, current, school, visited, !visited.get(friend.fnum), clique);
				}
			}			
			friend = friend.next;
//			if(friend.next != null) {
//				System.out.println(graph.members[friend.fnum] + "--Going To-->" + graph.members[friend.next.fnum] );
//			}
		}		
		
	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {		
		/** COMPLETE THIS METHOD **/
		ArrayList<String> connectors = new ArrayList<String>();
		
		for(Person person: g.members) {
			//check if that person has only 1 friend
			int counter = 0;
			Friend friendPtr = person.first;			
			while(friendPtr != null) {								
				counter++;
				friendPtr = friendPtr.next;
			}
			//if they do, that friend is a connector
			if(counter == 1) {
				if(!connectors.contains(g.members[person.first.fnum].name)) {
					connectors.add(g.members[person.first.fnum].name);
				}
			}
		}
//		System.out.println(connectors.toString());
		return connectors;
		
	}
	
}

