package graph;

/** Eine Klasse, die Knoten eines Graphen repräsentiert
 * 
 * @author ripphausen
 * @version 1.0
 */
public class Vertex {
	private int id;
        private double dist;
        private Vertex pred;
        

	public Vertex(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
        
        public void setDist(double dist){
            this.dist = dist;
        }
        
        public double getDist(){
            return dist;
        }
        
        public void setPred(Vertex pred){
            this.pred = pred;
        }
        
        public Vertex getPred(){
            return pred;
        }
	
	public String toString() {
		return new Integer(id).toString();
	}
}