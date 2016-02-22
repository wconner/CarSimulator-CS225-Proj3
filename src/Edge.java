
/**
 * An Edge represents a relationship between two
 *  vertices.
 */
public class Edge {
    private int source;
    private int dest;
    private double weight;

    /**
     * Construct a weighted edge with a source
     * of from and a destination of to. Set the
     * weight to w.
     *
     * @param source - The source vertix
     * @param dest   - The destination vertix
     * @param w      - The weight
     */
    public Edge(int source, int dest, double w) {
        this.source = source;
        this.dest = dest;
        weight = w;
    }

    /**
     * Get the source
     *
     * @return The value of source
     */
    public int getSource() {
        return source;
    }

    /**
     * Get the destination
     *
     * @return The value of dest
     */
    public int getDest() {
        return dest;
    }

    /**
     * Get the weight
     *
     * @return the value of weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Return a String representation of the edge
     *
     * @return A String representation of the edge
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[(");
        sb.append(Integer.toString(source));
        sb.append(", ");
        sb.append(Integer.toString(dest));
        sb.append("): ");
        sb.append(Double.toString(weight));
        sb.append("]");
        return sb.toString();
    }
}


