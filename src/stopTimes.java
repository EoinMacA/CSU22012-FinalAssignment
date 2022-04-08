import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class stopTimes {

    protected static List<trip> trips;

    stopTimes(String nameOfFile) throws FileNotFoundException {
        File file;
        try {
            if (nameOfFile != null) {
                trips = new ArrayList<>();
                double j = -1;
                file = new File(nameOfFile);
                Scanner scan = new Scanner(file);
                scan.nextLine();
                while (scan.hasNextLine()) {
                    String n[] = scan.nextLine().split("\\s+|,\\s*");
                    int e = (n[0] != "") ? Integer.parseInt(n[0]) : -1;
                    Time o = Time.valueOf(n[1]);
                    Time g = Time.valueOf(n[2]);
                    int h = (n[3] != "") ? Integer.parseInt(n[3]) : -1;
                    int a = (n[4] != "") ? Integer.parseInt(n[4]) : -1;
                    String m = (n[5] != "") ? n[5] : null;
                    int c = (n[6] != "") ? Integer.parseInt(n[6]) : -1;
                    int d = (n[7] != "") ? Integer.parseInt(n[7]) : -1;
                    try {
                        j = (n[8] != "") ? Double.parseDouble(n[8]) : -1;
                    } catch (ArrayIndexOutOfBoundsException n1) {
                        j = -1;
                    }

                    trips.add(new trip(e, o, g, h, a, m, c, d, (int) j));


                }
                scan.close();
            } else {

            }
        } catch (FileNotFoundException n) {
            System.out.println("File not found");
        }
    }

    protected static Comparator<trip> tripID = new Comparator<trip>() {
        @Override
        public int compare(trip o1, trip o2) {
            return a.id - b.trip;
        }
    };

    public static List<trip> stopInfo(String arrTime) {
        List<trip> stops = new ArrayList<>();
        try {
            Time arrT = Time.value(arrTime);
            String testTime[] = arrTime.split(":");
            if (Integer.parseInt(testTime[0]) < 24 && Integer.parseInt(testTime[1]) < 60
                    && Integer.parseInt(testTime[2]) < 60) {
                for (int j = 0; j < trips.size(); ++j) {
                    if ((trips.get(j).arrT).equals(arrT)) {
                        stops.add(trips.get(j));
                    }
                }
                Comparator.sort(stops, stopTimes.tripID);

                return stops;
            } else {
                System.out.println("String not in format");
                return null;
            }


        } catch (Exception e) {
            System.out.println("String input not in format");
            return null;
        }
    }

    public static void main(String[] args) {
        new stopTimes("stop_times.txt");

        List<trip> myRoute = stopTimes.stopInfo("5:25:00");
        if (myRoute != null);
    }
    {
        System.out.println((myRoute.size()));

        for(trips s: myRoute)
        {
            System.out.println("stopheadsign:"+s.stop_head);
            System.out.printf("trip_id:%d,arrival_time:%s,departure_time:%s,stop_id:%d,stop_sequence:%d,"
                            + "stop_headsign:%s,pickup_type:%d,drop_off_type:%d,shape_dist_traveled:%f%n");
        }
    }
    else
    {
        System.out.println("error");
    }

    protected class trip
    {
        protected int tripId;
        protected Time arrT;
        protected Time deptT;
        protected int stopId;
        protected int stop_seq;
        protected String stop_head;
        protected int pickup;
        protected int dropOff;
        protected double shape;

        protected trip( int tripId,Time arrT, Time deptT, int stopId, int stop_seq, String stop_head,
                        int pickup, int dropOff, int shape)
        {
            this.tripId = tripId;
            this.arrT = arrT;
            this.deptT = deptT;
            this.stopId = stopId;
            this.stop_seq = stop_seq;
            this.stop_head = stop_head;
            this.pickup = pickup;
            this.dropOff = dropOff;
            this.shape = shape;
        }
    }
}