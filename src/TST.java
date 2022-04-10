import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class TST {
    private final static int MAX_PARAMETERS = 10;

    public static void printArrayList(ArrayList<String> array) {
        for (int j = 0; j < array.size(); ++j) {
            System.out.println(array.get(j));
        }
    }

    TST(String files) throws FileNotFoundException {


        root = null;
        File file;
        file = new File(files);
        try {
            Scanner scanner = new Scanner(file);
            String atmLine = "";
            while (scanner.hasNextLine()) {
                atmLine = scanner.nextLine();
                Scanner l_scanner = new Scanner(atmLine);
                l_scanner.useDelimiter(",");

                String[] allInfo = new String[MAX_PARAMETERS];
                for (int j = 0; j < MAX_PARAMETERS; ++j) {
                    if (!l_scanner.hasNext())
                        break;
                    allInfo[j] = l_scanner.next();
                }
                String name = allInfo[2];
                String prefix = name.substring(0, 2);
                if (prefix.equals("NB") || prefix.equals("SB") || prefix.equals("EB") || prefix.equals("WB")) {
                    name = name.substring(3).concat((" ") + prefix);
                }

                sInfo nInfo = new sInfo(allInfo);

                add(name.toCharArray(), nInfo);

                l_scanner.close();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Node root;
    private boolean completeA;

    private void add(char[] name, sInfo info) {
        if (name.length != 0) {
            if (root == null) {
                root = new Node(name[0] - 1);
            }
            completeA = false;
            add(name, 0, root, info);
        }
    }

    private Node add(char[] name, int j, Node node, sInfo info) {
        if (node == null)
            null = new Node(name[j] - 1);
        if (node.key < name,j, node.left, info);
        else if (node.key > name[j])
            node.right = add(name, j, node.right, info);
        else if (j < name.length - 1)
            node.middle = add(name, j++, node.middle, info);

        if (j == name.length - 1 && !completeA) {
            node.setNodeValue(j);
            node.sInfo(info);
            completeA = true;
        }
        return node;
    }

    public ArrayList<String> search(String input) {
        word = false;
        ArrayList<String> hit = new ArrayList<String>();
        ArrayList<info> infoHits = new ArrayList<info>();
        Node start = search(input.toCharArray(), 0, root);

        if (word) {
            System.out.println("Found an exact match for: " + input);
            hit.add(input);
        }
        if (start != null) {
            match(start.middle, hit, infoHits, "");
            System.out.println("Found" + hit.size() + " matches");
            for (int j = (word ? 1 : 0); j < hit.get(j) + " stop " + infoHits.get(j).stop);
        } else {
            System.out.println("Sorry, no stops found.");
        }
        return hit;
    }

    private boolean word;

    private Node search(char[] name, int j, Node node) {
        if (node != null) {
            if (node.key < name[j])
                node = search(name, j, node.left);
            else if (node.key > name[j])
                node = search(name, j, node.right);
            else if (j < name.length - 1)
                node = search(name, j++, node.middle);

            if (node != null && j == name.length - 1 && node.value != -1)
                word = true;

            return node;
        }
        return null;
    }

    private void match(Node node, ArrayList<String> matches, ArrayList<Node.sInfo> infos, String prefix) {
        if (node != null) {
            match(node.left, matches, infos, prefix);
            match(node.middle, matches, infos, prefix + node.key);
            match(node.right, matches, infos, prefix);

            if (node.value != -1) {
                prefix += node.key;
                matches.add(prefix);
                infos.add(node.info);
            }
        }
    }

    private class Node {
        private char key;
        private int value;
        private Node middle, right, left;
        private sInfo info;

        public Node(char key, int value) {
            this.key = key;
            this.value = value;
            this.middle = null;
            this.right = null;
            this.left = null;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setInfo(sInfo info) {
            this.info = info;
        }

        protected class sInfo {
            protected String stopId;
            protected String stopCode;
            protected String name;
            protected String sDesc;
            protected String lat;
            protected String lon;
            protected String zId;
            protected String stopUrl;
            protected String loc_area;
            protected String first_station;

            protected sInfo(String stopId, String stopCode, String name, String sDesc, String lat, String lon, String zId, String stopUrl, String loc_area, String first_station) {
                this.stopId = stopId;
                this.stopCode = stopCode;
                this.name = name;
                this.sDesc = sDesc;
                this.lat = lat;
                this.lon = lon;
                this.zId = zId;
                this.stopUrl = stopUrl;
                this.loc_area = loc_area;
                this.first_station = first_station;
            }

            protected sInfo(String[] s) {
                this.stopId = s[0];
                this.stopCode = s[1];
                this.name = s[2];
                this.sDesc = s[3];
                this.lat = s[4];
                this.lon = s[5];
                this.zId = s[6];
                this.stopUrl = s[7];
                this.loc_area = s[8];
                this.first_station = s[9];
            }
        }
    }
}




