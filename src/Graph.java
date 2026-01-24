public class Graph
{
    public class Node implements Comparable
    {
        private Comparable info;
        private Vector<Edge> edges;
        private Node previous; //adding this for the parent in FindPath
        private boolean visited; //prevent loops

        public Node(Comparable label)
        {
            info = label;
            edges = new Vector<>(1000);
        }

        public void addEdge(Edge e)
        {
            edges.addLast(e);
        }

        public Vector<Edge> getEdges() {
            return edges;
        }

        public boolean isVisited()
        {
            return visited;
        }

        public void setVisited(boolean visited)
        {
            this.visited = visited;
        }
        
        public int compareTo(Object o)
        {
            //casting the object to node
            // two nodes are equal if they have the same label
            Node n = (Node)o;
            return this.info.compareTo(n.info);
        }
        
        public Comparable getLabel()
        {
            return info;
        }
        
    }

    //i added a helper method to get edges but didn't change the accessibility to public here!
    private class Edge implements Comparable
    {
        private Node toNode;
        private double weight;

        //adding an edge and its weight
        public Edge(Node to,  double weight)
        {
            toNode = to;
            this.weight = weight;
        }

        public double getWeight()
        {
            return weight;
        }

        public Node getToNode()
        {
            return toNode;
        }
        
        public int compareTo(Object o)
        {
            // two edges are equal if they point
            // to the same node.
            // this assumes that the edges are
            // starting from the same node !!!
            Edge n = (Edge)o;
            return n.toNode.compareTo(toNode);
        }

    }
    
    private Vector<Node> nodes;
    
    public Graph()
    {
        nodes = new Vector<>(1000);
    }
    
    public void addNode(Comparable label)
    {
        nodes.addLast(new Node(label));
    }
    
    public Node findNode(Comparable nodeLabel)
    {
        Node res = null;
        for (int i=0; i<nodes.size(); i++)
        {
            Node n = nodes.get(i);
            if (n.getLabel().equals(nodeLabel))
            {
                res = n;
                break;
            }
        }
        return res;
    }

    public void addEdge(Comparable nodeLabel1, Comparable nodeLabel2, double weight)
    {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);

        if (n1 == null || n2 == null)
            return;

        //for adding an edge first we create the edge on Edge class
        //then added it to the edge lists of the node
        n1.addEdge(new Edge(n2, weight));
    }


    //using the Queue will ensure that we are gonna explore the graph layer by layer which makes it possible to find the shortest path
    //this is BFS
    public int distance(Comparable nodeLabel1, Comparable nodeLabel2)
    {
        Node start = findNode(nodeLabel1);
        Node end   = findNode(nodeLabel2);

        if (start == null || end == null)
            return -1;

        // reset nodes for reusing it
        for (int i = 0; i < nodes.size(); i++)
        {
            Node n = nodes.get(i);
            //this will prevent loops and in the project also we can use bidirectional edges with this
            n.setVisited(false);
            n.previous = null;
        }

        Queue queue = new Queue();
        queue.push(start);
        start.setVisited(true);

        while (!queue.empty())
        {
            Node current = (Node) queue.pop();

            if (current == end)
            {
                // count distance by walking back
                int dist = 0;
                Node step = end;

                //here we used the parents to count the distance
                while (step.previous != null)
                {
                    dist++;
                    step = step.previous;
                }
                return dist;
            }

            for (int i = 0; i < current.edges.size(); i++)
            {
                Node next = current.edges.get(i).getToNode();

                if (!next.isVisited())
                {
                    next.setVisited(true);
                    //this is the key part to add the parent of the node
                    next.previous = current;
                    queue.push(next);
                }
            }
        }

        return -1; // no path
    }

    //gives the label of all edges of a given node
    public Vector<String> getEdges(Comparable label)
    {
        Node node = findNode(label);
        if (node == null) return null;

        Vector<String> neighbors = new Vector<>(100);

        for (int i = 0; i < node.edges.size(); i++)
        {
            neighbors.addLast((String) node.edges.get(i).getToNode().getLabel());
        }

        return neighbors;
    }

    //the key here is to use the previous variable which determines the node's parent and using queue also will guarantee shortest path
    public void printPath(Comparable nodeLabel1, Comparable nodeLabel2)
    {
        Node start = findNode(nodeLabel1);
        Node end   = findNode(nodeLabel2);

        if (start == null || end == null)
        {
            System.out.println("No path found.");
            return;
        }

        // reset nodes
        for (int i = 0; i < nodes.size(); i++)
        {
            Node n = nodes.get(i);
            n.setVisited(false);
            // the first node will not have any parent
            n.previous = null;
        }

        Queue queue = new Queue();
        queue.push(start);
        start.setVisited(true);

        while (!queue.empty())
        {
            Node current = (Node) queue.pop();

            if (current == end)
            {
                Vector path = new Vector(1000);
                //we start from the end node to come to the start node step by step
                Node step = end;

                //reconstructing the whole path step by step from node's parents
                while (step != null)
                {
                    path.addFirst(step);
                    step = step.previous;
                }

                //iterate through the path that we made
                for (int i = 0; i < path.size(); i++)
                {
                    Node n = (Node) path.get(i);
                    System.out.print(n.getLabel());
                    if (i < path.size() - 1)
                        System.out.print(" -> ");
                }
                System.out.println();
                return;
            }

            //exploring the graph
            for (int i = 0; i < current.edges.size(); i++)
            {
                Node next = current.edges.get(i).getToNode();

                if (!next.isVisited())
                {
                    //setting visited flag, adding parent of the node and, pushing to the queue
                    next.setVisited(true);
                    next.previous = current;
                    queue.push(next);
                }
            }
        }

        System.out.println("There is not any path between them!");
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        // Loop over all nodes in the graph
        for (int i = 0; i < nodes.size(); i++)
        {
            Node node = (Node) nodes.get(i);

            // Print the node label
            sb.append(node.getLabel());
            sb.append(" -> ");

            // Print all outgoing edges from this node
            for (int j = 0; j < node.edges.size(); j++)
            {
                Edge e = (Edge) node.edges.get(j);
                sb.append(e.toNode.getLabel())
                        .append("(")
                        .append(e.getWeight())
                        .append(") ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
