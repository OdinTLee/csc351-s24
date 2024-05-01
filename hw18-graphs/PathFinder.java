import java.util.ArrayList;
import java.util.PriorityQueue;

public class PathFinder {

	public static void findPath(RoadGraph graph, int point1, String algo) {

		// Initialize the queue based on the specified algorithm
		QueueInterface<Vertex> queue;
	
		System.out.println("Traverse the graph to find a path.");
		
		if (algo.equals("bfs")){
			queue = new Queue<>();
		}
		else if (algo.equals("dfs")){
			queue = new Stack<>();
		}
		else if (algo.equals("dijkstra")){
			dijkstra(graph, point1);
			return;
		}
		else{
			System.out.println("Algorithm inputed does not match possible algorithms");
			return;
		}

		// Add the start node to the queue
		queue.push(graph.vertices.get(point1));

		//System.out.println(" this is peek;"+queue.peek());

		// Main loop for traversing the maze
		while(!queue.isEmpty()){

		    //System.out.println("YOYO : " + queue.peek());

			// pop from the queue
			Vertex vertex = queue.pop();
			//System.out.println("YOYO : " + queue.peek());
			//System.out.println(vertex.adjacent().size());
		    //System.out.println(vertex);

			// Explore edges of the current node
			for(RoadSegment edge : vertex.adjacent()){

				// Find vertex to set parent
				Vertex v;
				if( vertex.equals(graph.vertices.get(edge.point1().ID()))){
					v = graph.vertices.get(edge.point2().ID());
				}
				else{
					v = graph.vertices.get(edge.point1().ID());
				}
				
				// Check if the child has not been visited
				if( !v.visited()){
					queue.push(v);
					v.parent(vertex);
					v.visited(true);
				}
			}
		}
	}

	public static void dijkstra(RoadGraph graph, int source){
		PriorityQueue<Vertex> queue = new PriorityQueue<>();

		graph.vertices.get(source).distance(0.0);

		for (Vertex vertex : graph.vertices){
			queue.add(vertex);
		}

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

			
			// Explore edges of the current node
			for( RoadSegment edge : u.adjacent()){
				
				Vertex v = null;
				if( u.point() != edge.point1()){
					v = graph.vertices.get(edge.point1().ID());
				}
				else{
					v = graph.vertices.get(edge.point2().ID());
				}

				relax(u, v, edge, queue);
            }
        }
    }

	public static void relax(Vertex u, Vertex v, RoadSegment w, PriorityQueue<Vertex> queue){
		if( u.distance() > v.distance() + w.distance()){
			v.distance(u.distance() + w.distance());
			v.parent(u);
			queue.remove(v);
			queue.add(v);
		}
	}
}