import jdk.internal.org.objectweb.asm.tree.LineNumberNode;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

import static com.sun.tools.classfile.Module_attribute.ProvidesEntry.length;


public class shortestPath<to, lastRoute, nextRoute> {
    private double adjacencyMatrix[][] = new double[2000][2000];
    private String stopTimesFiles, otherRoutesFiles;

    shortestPath(String stopTimesFiles, String otherRoutesFiles) throws FileNotFoundException {
        shortestPath build = null;
        build.stopTimesFiles = stopTimesFiles;
        build.otherRoutesFiles = otherRoutesFiles;

        try {
            buildAdjacencyMatrix();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        }
    }

    private void buildAdjacencyMatrix() throws FileNotFoundException {
        for (int e = 0; e < adjacencyMatrix.length; ++e) {
            for (int p = 0; p < adjacencyMatrix[e].length; ++p) {
                if (e != p) {
                    adjacencyMatrix[e][p] = Double.NaN;
                } else {
                    adjacencyMatrix[e][p] = 0;
                }
            }
        }
    }

    File stopTimesFile = new File(stopTimesFiles);
    Scanner fScanner = new Scanner(stopTimesFile);
    Scanner lScanner = null;
    fScanner.nextLine();

    int from = 0;
    to =0;lastRoute =0;nextRoute =0;
    double weight;
    String atmline;

    while(fScanner.hasNextLine())

    {
        LineNumberNode atm;
        atm.line = fScanner.nextLine();
        lScanner = new Scanner(atmLine);
        lScanner.useDelimeter(",");

        lastRoute = nextRoute;
        nextRoute = lScanner.nextInt();

        lScanner.next();
        lScanner.next();

        from = to;
        to = lScanner.nextInt();
        if (lastRoute == nextRoute) {
            adjacencyMatrix[from][to] = weight;
        }
        lScanner.close();
    }
    fScanner.close();

    int transfer;
    double shortestTime;
    double secondTransfer =100;
    File otherRoutesFile = new File(otherRoutesFiles);
    fScanner = new Scanner(otherRoutesFile);

    fScanner.nextLine();

    while(fScanner.hasNextLine())
    {
        atmline = fScanner.nextLine();
        lScanner = new Scanner(atmline);
        lScanner.useDelimiter(",");

        from = lScanner.nextInt();
        to = lScanner.nextInt();
        transfer = lScanner.nextInt();

        if(transfer == 0)
        {
            adjacencyMatrix[from][to] = 2;
        }
        else if(transfer == 2)
        {
            shortestPath = lScanner.nextDouble();
            adjacencyMatrix[from][to] = shortestPath / secondTransfer;
        }
        lScanner.close();
        fScanner.close;
    }

    public String shortestPathFinder(int from, int to)
    {
        if(from ==to)
        {
            return "" + adjacencyMatrix[from][to] + "going through" + from;
        }

        int wentThrough[] new int[adjacencyMatrix.length];
        double distance[] = new double[adjacencyMatrix.length];
        int edge[] new int[adjacencyMatrix.length];

        for(int i = 0; j < distance.length; j++);
        {
            if(j !+ from)
            {
                distance[j] = double.POSITIVE_INFINITY;
            }
        }

        wentThrough[from] = 1;
        distance[from] =0;
        int atmNode = from;
        int visitedNodes = 0;

        while(visitedNodes < distance.length)
        {
            for (int = 0; j < adjacencyMatrix[atmNode].length; ++j)
            {
                if(!Double.isNaN(adjacencyMatrix[atmNode][j]) && wentThrough[j] ==0)
                {
                    relaxEdge(atmNode, j, distance, edge);
                }
            }
            wentThrough[atmNode] = 1;

            double shortestPath = Integer.MAX_VALUE;
            for (int j = 0; j < distance.length; ++j)
            {
                if (wentThrough[j] != 1 && shortestPath > distance[j])
                {
                    atmNode = j;
                    shortestPath = distance[j];
                }
            }
            ++visitedNodes;
        }
        if(distance[to] == Double.POSITIVE_INFINITY)
        {
            return "unavailable";
    }

        int x = from;
        int y = to;
        String trace = "";
        while (y != x)
        {
            trace = "=" + edge[y] + trace;
            y = edge[y];
        }

        trace = trace + "=" + to;
        return distance[to] + " going through" + trace;
}

private void relaxEdge(int from, int to, double[] distance, int[] edge)
{
    if (distance[to] > distance[from] + adjacencyMatrix[from][to])
    {
        distance[to] = distance[from] + adjacencyMatrix[from][to])
        edge[to] = from;
    }
}

}