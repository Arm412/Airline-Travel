


public class Edge {
		private Vertex start;
		private Vertex finish;
		private double cost;
		private int distance;
		private int beginning;
		private int end;
		public boolean dup;

		public Edge(Vertex startV,Vertex endV,int startN, int endN,int length,double money,boolean dupl) {
			start=startV;
			finish=endV;
			beginning=startN;
			end=endN;
			cost=money;
			distance=length;
			dup=dupl;
		}
		public Vertex getStartCity(){
			return start;
		}
		public Vertex getEndCity(){
			return finish;
		}
		public double getCost(){
			return cost;
		}
		public int getDistance(){
			return distance;
		}
		public int getStartNumber(){
			return beginning;
		}
		public int getEndNumber(){
			return end;
		}
		public void changeCost(double newCost){
			cost=newCost;
		}
	}