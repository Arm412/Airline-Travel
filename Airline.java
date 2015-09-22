import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Airline {
	public static void main(String[] args) {
		System.out.println("Please input an input file of the city routes:");
		Scanner in=new Scanner(System.in);
		String fileHold=in.nextLine();
		Scanner file=null;
		ArrayList<Edge>everyEdge=new ArrayList<Edge>();
		ArrayList<Edge>queue=new ArrayList<Edge>();
		try {
			file = new Scanner(new FileInputStream(fileHold));
		} catch (FileNotFoundException e) {
			System.out.println("Please input a file name.");
			e.printStackTrace();
			System.exit(0);
		}
		ArrayList<Vertex> allVertex=new ArrayList<Vertex>();
		int h=-1;
		int totalPlaces=0;
		while(file.hasNext()){
			h++;
			if(h==0){
				totalPlaces=file.nextInt();
				//System.out.println(totalPlaces);
			} else if(h>0 && h<=totalPlaces){
				Vertex city=new Vertex(file.next(),h);
				allVertex.add(city);
			} else{
				while(file.hasNextDouble() || file.hasNextInt()){
					makeAdjList(file,allVertex,everyEdge);
				}
			}
		}
		while(1<100){
			System.out.println("Please input what you would like to do: ");
			System.out.println("Show possible trips with distances and costs. (Type '1')");
			System.out.println("Show the minimum spanning tree of the trips. (Type '2')");
			System.out.println("Finding an optimal route to a destination. (Type '3')");
			System.out.println("Remove a route. (Type '4')");
			System.out.println("Add a route. (Type '5')");
			System.out.println("Show possible trips with certain amount of money. (Type '6')");
			System.out.println("Update original file and exit. (Type '7')");
			System.out.println("Exit without saving. (Type '8')");
			int input=in.nextInt();
			if(input==1){
					showTripsAndCost(totalPlaces,allVertex);
			}
			if(input==2){
					doPrims(allVertex.get(0),queue,totalPlaces,0);
					printMST(allVertex);
			}	
			if(input==3){
					System.out.println("Show optimal route based on distance (1), cost (2), or hops between cities (3).");
					int hold=in.nextInt();
						
					if(hold==1){
							Vertex startL=null;
							Vertex endL=null;
							boolean good=false;
							in = new Scanner(System.in);
							while(good==false){
								System.out.println("Where is your starting location:");
								String start=in.nextLine().toLowerCase();
								for(int i=0;i<allVertex.size();i++){
									if(allVertex.get(i).getPlace().toLowerCase().equals(start)){
										good=true;
										startL=allVertex.get(i);
									}
								}
								if(good==false){
									System.out.println("That is not a valid location.\n\n");
								}
							}
							good=false;
							while(good==false){
								System.out.println("Where is your ending location:");
								String end=in.nextLine().toLowerCase();
								for(int i=0;i<allVertex.size();i++){
									if(allVertex.get(i).getPlace().toLowerCase().equals(end)){
										good=true;
										endL=allVertex.get(i);
									}
								}
								if(good==false){
									System.out.println("That is not a valid location.\n\n");
								}
							}
							resetVisit(allVertex);
							DijkstraDistance(startL, endL);
					}	
					if(hold==2){
							Vertex startL=null;
							Vertex endL=null;
							boolean good=false;
							in = new Scanner(System.in);
							while(good==false){
								System.out.println("Where is your starting location:");
								String start=in.nextLine().toLowerCase();
								for(int i=0;i<allVertex.size();i++){
									if(allVertex.get(i).getPlace().toLowerCase().equals(start)){
										good=true;
										startL=allVertex.get(i);
									}
								}
								if(good==false){
									System.out.println("That is not a valid location.\n\n");
								}
							}
							good=false;
							while(good==false){
								System.out.println("Where is your ending location:");
								String end=in.nextLine().toLowerCase();
								for(int i=0;i<allVertex.size();i++){
									if(allVertex.get(i).getPlace().toLowerCase().equals(end)){
										good=true;
										endL=allVertex.get(i);
									}
								}
								if(good==false){
									System.out.println("That is not a valid location.\n\n");
								}
							}
							resetVisit(allVertex);
							DijkstraMoney(startL, endL);
					}
					if(hold==3){
							Vertex startL=null;
							Vertex endL=null;
							boolean good=false;
							in = new Scanner(System.in);
							while(good==false){
								System.out.println("Where is your starting location:");
								String start=in.nextLine().toLowerCase();
								for(int i=0;i<allVertex.size();i++){
									if(allVertex.get(i).getPlace().toLowerCase().equals(start)){
										good=true;
										startL=allVertex.get(i);
									}
								}
								if(good==false){
									System.out.println("That is not a valid location.\n\n");
								}
							}
							good=false;
							while(good==false){
								System.out.println("Where is your ending location:");
								String end=in.nextLine().toLowerCase();
								for(int i=0;i<allVertex.size();i++){
									if(allVertex.get(i).getPlace().toLowerCase().equals(end)){
										good=true;
										endL=allVertex.get(i);
									}
								}
								if(good==false){
									System.out.println("That is not a valid location.\n\n");
								}
							}
							resetVisit(allVertex);
							resetTotal(allVertex);
							Hops(startL, endL);
						}
					}
				
					if(input==4){	
					Vertex startL=null;
					Vertex endL=null;
					String start=null;
					boolean good=false;
					in = new Scanner(System.in);
					while(good==false){
						System.out.println("Where is your starting location:");
						start=in.nextLine().toLowerCase();
							for(int i=0;i<allVertex.size();i++){
								if(allVertex.get(i).getPlace().toLowerCase().equals(start)){
									good=true;
									startL=allVertex.get(i);
								}
							}
							if(good==false){
								System.out.println("That is not a valid location.\n\n");
							}
					}
					good=false;
					while(good==false){
						System.out.println("Where is your ending location:");
						String end=in.nextLine().toLowerCase();
						for(int i=0;i<allVertex.size();i++){
							if(allVertex.get(i).getPlace().toLowerCase().equals(end)){
								good=true;
								endL=allVertex.get(i);
							}
						}
						if(good==false){
							System.out.println("That is not a valid location.\n\n");
						}
					}
					removeRoute(startL,endL);
					}
					if(input==5){
					//add
						Vertex startL=null;
						Vertex endL=null;
						boolean good=false;
						in = new Scanner(System.in);
						while(good==false){
							System.out.println("Where is your starting location:");
							String start=in.nextLine().toLowerCase();
							for(int i=0;i<allVertex.size();i++){
								if(allVertex.get(i).getPlace().toLowerCase().equals(start)){
									good=true;
									startL=allVertex.get(i);
								}
							}
							if(good==false){
								System.out.println("That is not a valid location.\n\n");
							}
						}
						good=false;
						while(good==false){
							System.out.println("Where is your ending location:");
							String end=in.nextLine().toLowerCase();
							for(int i=0;i<allVertex.size();i++){
								if(allVertex.get(i).getPlace().toLowerCase().equals(end)){
									good=true;
									endL=allVertex.get(i);
								}
							}
							if(good==false){
								System.out.println("That is not a valid location.\n\n");
							}
						}
						System.out.println("Enter the distance of this trip: ");
						int distance=in.nextInt();
						System.out.println("Enter the cost of this trip: ");
						double cost=in.nextDouble();
						addRoute(startL,endL,cost,distance);
					}
					if(input==6){
						boolean good=false;
						Vertex startL;
						in = new Scanner(System.in);
						System.out.println("How much money are you willing to spend on a trip: ");
						double cost=in.nextDouble();
						//Find all trips within money range
						for(int i=0;i<allVertex.size();i++){
							allVertex.get(i).dPrevious=null;
							findTripsWithCost(cost,allVertex.get(i),0);
						}
						System.out.println("\n");
					}
					if(input==7){
						updateFile(allVertex,totalPlaces,fileHold);
						System.exit(0);
					}
					if(input==8){
					System.exit(0);
					}
			
		}
		
	}
	
	
	
	private static void printMST(ArrayList<Vertex> allVertex) {
		int i=0;
		while(i<allVertex.size()){
			for(int j=0;j<allVertex.get(i).getMSTEdgeLength();j++){
				System.out.println(allVertex.get(i).getPlace() + " to " + allVertex.get(i).getMSTEdge(j).getEndCity().getPlace() + " is " + allVertex.get(i).getMSTEdge(j).getDistance());
			}
			i++;
		}
	}

	private static void resetVisit(ArrayList<Vertex> allVertex) {
		for(int i=0;i<allVertex.size();i++){
			allVertex.get(i).resetVisit();
		}
		return;
	}

	private static void resetTotal(ArrayList<Vertex> allVertex) {
		for(int i=0;i<allVertex.size();i++){
			allVertex.get(i).pathLength=-1;
		}
		return;
	}

	private static boolean doPrims(Vertex current,ArrayList<Edge>queue, int totalPlaces,int hold) {
		boolean done=false;
		int i=0;
		current.setVisit();
		current.setSet(-1);
		hold++;
		if(hold==totalPlaces){
			done=true;
		}
		while(i<current.getEdgeList().size()){ //puts every edge in the queue
			queue.add(current.getEdge(i));
			i++;
		}
		Collections.sort(queue, new EdgeWeightComparator()); //sorts the queue
		for(int j=0;j<queue.size();j++){ //checks queue for shortest edge
			if(queue.get(j).getEndCity().checkVisit()==false){ //enters if the edge in the queue is shortest and hasn't been visited.
				current=queue.get(j).getStartCity(); //Sets current location of next shortest edge.
				current.addMSTEdge(queue.get(j));

				done=doPrims(queue.get(j).getEndCity(),queue, totalPlaces,hold);
				if(done==true){
					return done;
				}
			}
		}
		return done;
	}


	public static void makeAdjList(Scanner file,ArrayList<Vertex> allVertex, ArrayList<Edge> everyEdge){
		int startNum=file.nextInt(); //Number representing start City
		int endNum=file.nextInt(); //Number representing the end city after route from start city.
		int distance=file.nextInt(); //Holds distance of edge.
		double cost=file.nextDouble(); //Cost of trip between cities
		boolean no=false;
		boolean yes=true;
		Vertex startCity=allVertex.get(startNum-1); 
		Vertex endCity=allVertex.get(endNum-1);
		Edge route=new Edge(startCity, endCity,startNum,endNum,distance,cost,no); //Route from start city to end city.
		Edge routeBack=new Edge(endCity, startCity,endNum,startNum,distance,cost,yes); //Route from end city to start city.
		everyEdge.add(route);
		startCity.addEdge(route); //Creates edge to city.
		endCity.addEdge(routeBack); //Creates edge for returning route.
	}
	public static void showTripsAndCost(int totalPlaces,ArrayList<Vertex> allVertex){
		int i=0;
		while(i!=totalPlaces){
			Vertex look=allVertex.get(i);
			for(int j=0;j<look.numberOfEdges();j++){
				Edge path=look.getEdge(j);
				System.out.println("The trip from " + path.getStartCity().getPlace() + " to " + path.getEndCity().getPlace() + " has a distance of " + path.getDistance() + " and costs $" + path.getCost() + "0");
				System.out.println("\n(" + path.getStartCity().getPlace() + ")-----" + path.getDistance() + "-----$" + path.getCost() + "0-----(" + path.getEndCity().getPlace() + ")\n\n");
			}
			
			i++;
		}
		System.out.println("\n");
	}
	
	public static void DijkstraDistance(Vertex start, Vertex finish){
		ArrayList<Vertex>paths=new ArrayList<Vertex>(); //holds end vertex of a path.
		paths.add(start);
		int holdV=0;
		int holdE=0;
		start.pathLength=0;
		start.setVisit();
		boolean found=false;
		Vertex currentShortest=null;
		int shortestPath=0;
		while(found==false){
			boolean s=false;
			shortestPath=-1;
			for(int i=0;i<paths.size();i++){ //Goes through all end vertex's
				int sizeofEdges=paths.get(i).getEdgeList().size();
				for(int j=0;j<sizeofEdges;j++){ //puts possible edges into queue
					if(paths.get(i).getEdge(j).getEndCity().checkVisit()!=true){ //if it finds an unvisited city
						//---------Attempt to hold shortest path-------------------------
						if(shortestPath!=-1 && shortestPath>paths.get(i).pathLength+paths.get(i).getEdge(j).getDistance()){ //check if shortest path
							shortestPath=paths.get(i).pathLength+paths.get(i).getEdge(j).getDistance();
							currentShortest=paths.get(i).getEdge(j).getEndCity();
							holdV=i;
							holdE=j;
							s=true;
						}else if(shortestPath==-1){ //sets shortestPath of this run to the first edge looked at.
							shortestPath=paths.get(i).pathLength+paths.get(i).getEdge(j).getDistance();
							currentShortest=paths.get(i).getEdge(j).getEndCity();
							holdV=i;
							holdE=j;
							s=true;
						}
					}
				}
			}
			currentShortest.setVisit();
			currentShortest.dPrevious=paths.get(holdV).getEdge(holdE);
			currentShortest.pathLength=shortestPath;
			paths.add(currentShortest);
			if(currentShortest.getPlace()==finish.getPlace()){
				found=true;
				System.out.println("Found " + finish.getPlace() + " with a distance of " + finish.pathLength + " from " + start.getPlace() + "\n");
				System.out.println("Route to get there with edges: (Reversed)\n");
				Vertex current=finish;
				while(current.dPrevious!=null){
					System.out.print(current.getPlace() + " " + current.dPrevious.getDistance() + " ");
					current=current.dPrevious.getStartCity();
					if(current.dPrevious==null){
						System.out.println(current.getPlace() + "\n");
					}
				}
				return;
			}
			if(s==false){
				System.out.println("City has no possible route.");
				return;
			}
		}
	}
	
	public static void DijkstraMoney(Vertex start, Vertex finish){
		ArrayList<Vertex>paths=new ArrayList<Vertex>(); //holds end vertex of a path.
		paths.add(start);
		int holdV=0;
		int holdE=0;
		start.pathLength=0;
		start.setVisit();
		boolean found=false;
		Vertex currentShortest=null;
		int shortestPath=0;
		while(found==false){
			boolean s=false;
			shortestPath=-1;
			for(int i=0;i<paths.size();i++){ //Goes through all end vertex's
				int sizeofEdges=paths.get(i).getEdgeList().size();
				for(int j=0;j<sizeofEdges;j++){ //puts possible edges into queue
					if(paths.get(i).getEdge(j).getEndCity().checkVisit()!=true){ //if it finds an unvisited city
						if(shortestPath!=-1 && shortestPath>paths.get(i).pathLength+paths.get(i).getEdge(j).getCost()){ //check if shortest path
							shortestPath=(int) (paths.get(i).pathLength+paths.get(i).getEdge(j).getCost());
							currentShortest=paths.get(i).getEdge(j).getEndCity();
							holdV=i;
							holdE=j;
							s=true;
						}else if(shortestPath==-1){ //sets shortestPath of this run to the first edge looked at.
							shortestPath=(int) (paths.get(i).pathLength+paths.get(i).getEdge(j).getCost());
							currentShortest=paths.get(i).getEdge(j).getEndCity();
							holdV=i;
							holdE=j;
							s=true;
						}
					}
				}
			}
			currentShortest.setVisit();
			currentShortest.dPrevious=paths.get(holdV).getEdge(holdE);
			currentShortest.pathLength=(int) shortestPath;
			paths.add(currentShortest);
			if(currentShortest.getPlace()==finish.getPlace()){
				found=true;
				System.out.println("Found " + finish.getPlace() + " with a cost of $" + finish.pathLength + " from " + start.getPlace() + "\n");
				System.out.println("Route to get there with edges: (Reversed)\n");
				Vertex current=finish;
				while(current.dPrevious!=null){
					System.out.print(current.getPlace() + " $" + current.dPrevious.getCost() + "0 ");
					current=current.dPrevious.getStartCity();
					if(current.dPrevious==null){
						System.out.println(current.getPlace() + "\n");
					}
				}
				return;
			}
			if(s==false){
				System.out.println("City has no possible route.");
				return;
			}
		}
	}

	public static void Hops(Vertex start, Vertex finish){
		ArrayList<Vertex>paths=new ArrayList<Vertex>(); //holds end vertex of a path.
		paths.add(start);
		start.pathLength=0;
		start.setVisit();
		boolean found=false;
		while(found==false){
			for(int i=0;i<paths.size();i++){ //Goes through all end vertex's
				int sizeofEdges=paths.get(i).getEdgeList().size();
				for(int j=0;j<sizeofEdges;j++){ //puts possible edges into queue
					if(paths.get(i).getEdge(j).getEndCity().checkVisit()==false){ //if it finds an unvisited city
						if(paths.get(i).getEdge(j).getEndCity().pathLength==-1){
							paths.add(paths.get(i).getEdge(j).getEndCity());
							paths.get(i).getEdge(j).getEndCity().dPrevious=paths.get(i).getEdge(j);
							paths.get(i).getEdge(j).getEndCity().pathLength=paths.get(i).pathLength+1;
							if(paths.get(i).getEdge(j).getEndCity().getPlace()==finish.getPlace()){
								paths.get(i).getEdge(j).getEndCity().setVisit();
								System.out.println("Found " + finish.getPlace() + " with " + finish.pathLength + " hops.\n");
								System.out.println("Route: (Reversed)\n");
								Vertex current=finish;
								while(current.dPrevious!=null){
									System.out.print(current.getPlace() + " ");
									current=current.dPrevious.getStartCity();
									if(current.dPrevious==null){
										System.out.println(current.getPlace() + "\n");
									}
								}
								return;
							}
						}
					}
				}
			}
			System.out.println("There is no route connecting the cities.");
			return;
		}
	}
	
	
	public static void removeRoute(Vertex start, Vertex end){
		for(int i=0;i<start.getEdgeList().size();i++){
			if(start.getEdge(i).getEndCity()==end){
				start.getEdgeList().remove(i);
			}
		}
		for(int i=0;i<end.getEdgeList().size();i++){
			if(end.getEdge(i).getEndCity()==start){
				end.getEdgeList().remove(i);
			}
		}
	}
	
	public static void addRoute(Vertex start, Vertex end, double cost, int distance){
		boolean no=false;
		boolean yes=true;
		Edge hold=new Edge(start,end,start.getCityNumber(),end.getCityNumber(),distance,cost,no);
		start.getEdgeList().add(hold);
		hold=new Edge(end,start,end.getCityNumber(),start.getCityNumber(),distance,cost,yes);
		end.getEdgeList().add(hold);
	}
	
	
	public static void findTripsWithCost(double cost, Vertex current,double totalCost){
			if(totalCost>cost){ //returns if the cost of current path is going passed the original cost
				return;
			}
			current.setVisit();
			Vertex back=current; //used in while loop so it keeps current city
			if(current.dPrevious!=null){ //makes sure it doesn't print out first location only
				System.out.print("Cost: $" + (int) totalCost + " Path:(Reversed) ");
				while(back.dPrevious!=null){ //prints out cost and path until it hits the starting city
					System.out.print(" " + back.getPlace() + " $" + (int) back.dPrevious.getCost());
					back=back.dPrevious.getStartCity();
				}
				System.out.println(" " + back.getPlace());
			}
			for(int i=0;i<current.getEdgeList().size();i++){
				if(current.getEdge(i).getEndCity().checkVisit()==false){
					current.getEdge(i).getEndCity().dPrevious=current.getEdge(i);
					findTripsWithCost(cost,current.getEdge(i).getEndCity(),totalCost+current.getEdgeList().get(i).getCost());
				}
			}
			current.resetVisit();
		}
	
	
	public static void updateFile(ArrayList<Vertex>allVertex, int totalPlaces, String filehold){
		PrintWriter up=null;
		try {
			up=new PrintWriter(filehold);
			up.print("");
			up.println(totalPlaces);
			for(int i=0;i<allVertex.size();i++){
				up.println(allVertex.get(i).getPlace());
			}
			for(int i=0;i<allVertex.size();i++){
				for(int j=0;j<allVertex.get(i).getEdgeList().size();j++){
					if(allVertex.get(i).getEdge(j).dup==false){
						up.println(allVertex.get(i).getCityNumber() + " " + allVertex.get(i).getEdge(j).getEndCity().getCityNumber() + " " + allVertex.get(i).getEdge(j).getDistance() + " " + allVertex.get(i).getEdge(j).getCost());
					}
				}
			}
			up.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
