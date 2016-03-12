import java.util.ArrayList;

/**
 * Created by Bill on 2/22/16.
 * See Graph for method descriptions.
 */
public class MapGraph{

    private ArrayList<Edge> edges;

    public MapGraph(){
        edges = new ArrayList<Edge>();
    }

    public void insert(Edge edge) {
        if (! isEdge(edge.getSource(), edge.getDest())) {
            edges.add(edge);
            edges.add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
        }
    }

    private boolean isEdge(int source, int dest) {
        for (Edge e : edges)
            if ((e.getSource() == source) && e.getDest() == dest)
                return true;
        return false;
    }

    public ArrayList<Edge> getEdges(int source) {
        ArrayList<Edge> c = new ArrayList<Edge>();
        for (Edge e : edges)
            if (e.getSource() == source)
                c.add(e);
        return c;
    }
}