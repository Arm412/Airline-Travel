import java.util.Comparator;

public class EdgeWeightComparator implements Comparator<Edge>{
	public int compare(Edge one, Edge two) {
		if(one.getDistance()>two.getDistance()){
			return 1;
		} else if(one.getDistance()<two.getDistance()){
			return -1;
		} else{
			return 0;
		}
	}
	
}