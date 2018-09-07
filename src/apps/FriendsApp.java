package apps;

import java.util.Map.Entry;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FriendsApp {
	
	static Scanner stdin = new Scanner(System.in);
	
		
	public static void main(String[] args) throws FileNotFoundException {
//		Friends test = new Friends();
//		test.shortestChain(null, "", "");
		Scanner sc = new Scanner(new File("t2.txt"));
//		Scanner sc = new Scanner(new File("t2.txt"));
//		Scanner sc = new Scanner(new File("t3.txt"));
		
		//graph takes in a scanner, which points to a file
		Graph graph = new Graph(sc);
		int counter = 0;
		int position = 1;
		
		/*---------------------------------------
		 			Display person info
		 ----------------------------------------*/
		System.out.print("[ ");
		for(Person person: graph.members) {
			System.out.print(counter + ". "+person.name + ", ");
			counter++;
		}
		counter = 0;
		System.out.print(" ]\n\n");
//		for(Person person: graph.members) {
//			System.out.println(position+".) "+"Person's name: "+person.name + "\n -Person's school: "+person.school
//					+ "\n -is Person a Student? "+person.student);
//			if(person.first != null) {
////				System.out.print(" -Person's Friend num: "+person.first.fnum+"\n");				
//				System.out.print(" -Looping through a persons friends: ");
//				Friend ptr = person.first;
//				while(ptr != null) {				
//					counter++;
//					System.out.print(ptr.fnum + " -> ");
////					System.out.print(counter + " ");
//					ptr = ptr.next;
//				}
//				//--
//				System.out.print("\n -Names of Person's friends: ");
//				ptr = person.first;
//				while(ptr != null) {				
//					counter++;
//					System.out.print(graph.members[ptr.fnum].name + " -> ");
////					System.out.print(counter + " ");
//					ptr = ptr.next;
//				}
//				//--
//				System.out.print("\n -This person has: " + counter + " Friends\n\n");
//				counter = 0;				
//			} else {
//				System.out.print(" -Person has no friends\n\n");
//			}
//			position++;
////			counter++;
//		}
////		System.out.println("Ran "+counter+" times.");		
//		
//		System.out.println();
//		for(Entry<String, Integer> entry: graph.map.entrySet()) {
//			System.out.println("Key: "+entry.getKey() + ", Value: "+entry.getValue());
//			
//		}
		
		/*------------------------------------------------------------------------------------------------------------------------------------------------------------
		   				  END
		-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		//---------------------------------------
		/*---------------------------------------
					Display Relations
		----------------------------------------*/
		System.out.println("RELATIONS");
		position = 1;
		for(Person person: graph.members) {
			Friend ptr2 = person.first;
			System.out.println(position+".) "+"Person's name: "+person.name);
			System.out.print(" -Looping through a persons friends: ");
			Friend ptr = person.first;
			while(ptr != null) {				
				//counter++;
				System.out.print(ptr.fnum + " -> ");
//				System.out.print(counter + " ");
				ptr = ptr.next;
			}
			System.out.print("\n -Person's friends: ");
			ptr2 = person.first;
			while(ptr2 != null) {				
				counter++;
				System.out.print( ("(")+ ptr2.fnum + ")_"+"("+ graph.members[ptr2.fnum].school +")_" + graph.members[ptr2.fnum].name + " -> ");
//				System.out.print(counter + " ");
				ptr2 = ptr2.next;
			}
			//--
			System.out.print("\n -This person has: " + counter + " Friends\n\n");
			counter = 0;	
			position++;
		}
		
		System.out.println();
		for(Entry<String, Integer> entry: graph.map.entrySet()) {
			System.out.println("Key: "+entry.getKey() + ", Value: "+entry.getValue());
			
		}		
		
		
		/*------------------------------------------------------------------------------------------------------------------------------------------------------------
		   				  END
		-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
		//---------------------------------------
		/*---------------------------------------
						Methods
		----------------------------------------*/
		System.out.println("-----------------------------");
		System.out.print("[ ");
		for(Person person: graph.members) {
			System.out.print(counter + ". "+person.name + ", ");
			counter++;
		}
		counter = 0;
		System.out.print(" ]\n\n");
		
		//t.txt
//		System.out.println("SHORTEST CHAIN:");		
//		System.out.println(Friends.shortestChain(graph, "sergei", "kaitlin"));
//		System.out.println();
//		System.out.println("CLIQUES:");
//		System.out.println(Friends.cliques(graph, "rutgers").toString());
//		System.out.println();
//		System.out.println("CONNECTORS:");
//		System.out.println(Friends.connectors(graph));
		
		//t2.txt
		System.out.println("SHORTEST CHAIN:");
		System.out.println("Driver: "+Friends.shortestChain(graph, "mark", "steve").toString());
		System.out.println();
		System.out.println("CLIQUES:");
		System.out.println("Driver: "+Friends.cliques(graph, "ucla").toString());
		System.out.println();
		System.out.println("CONNECTORS:");
		System.out.println("Driver: "+Friends.connectors(graph).toString());
		
		
		
		
		
		System.out.println();
		System.out.println();
		
		 

	}

}
