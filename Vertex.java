import java.util.ArrayList;

public class Vertex {
		private String place;
		private int number;
		private ArrayList<Edge>allEdges=new ArrayList<Edge>();
		private boolean visited=false;
		private ArrayList<Edge>mstEdges=new ArrayList<Edge>();
		ArrayList<Vertex>bestPath=new ArrayList<Vertex>();
		private int set;
		public int pathLength;
		public Edge dPrevious=null;
		
		public Vertex(String destinations,int numb) {
			place = destinations;
			number=numb;
			set=numb;
		}
		public Vertex(String location){
			number=0;
		}
		public Vertex(){
			place=null;
			number=0;
		}
		public void addEdge(Edge path){
			allEdges.add(path);
			return;
		}
		public int getCityNumber(){
			return number;
		}
		public Edge getEdge(int index){
			return allEdges.get(index);
		}
		public String getPlace(){
			return place;
		}
		public int numberOfEdges(){
			return allEdges.size();
		}
		public void setVisit(){
			visited=true;
		}
		public boolean checkVisit(){
			return visited;
		}
		public void addMSTEdge(Edge e){
			mstEdges.add(e);
		}
		public ArrayList<Edge> getEdgeList(){
			return allEdges;
		}
		public int getSet(){
			return set;
		}
		public void setSet(int another) {
			set=another;
		}
		public Edge getMSTEdge(int i) {
			return mstEdges.get(i);
		}
		public int getMSTEdgeLength() {
			return mstEdges.size();
		}
		public void resetVisit() {
			visited=false;
		}
//		public int getPathLength(){
//			return pathLength;
//		}
	}