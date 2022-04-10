import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(("Welcome to Vancouver Bus Services, this app will help you find your next route!"));

        shortestPath graph = new shortestPath("stop_times.txt", "transfers.txt");
        myRoute theStops = new Main().new myRoute("stop_times.txt");
        TST TST = new TST("stops.txt");
        Scanner input = new Scanner (System.in);

        System.out.println("Enter 1 for shortest path between busstops");
        System.out.println("Enter 2 if you would like to search up a bus stop");
        System.out.println("Enter 3 if you would like to see all bus' at a time");
        System.out.println("Type out quit if you want to exit");

        boolean finished = false;
        while(!finished)
        {
            if (input.hasNextLine())
            {
                if (input.hasNext("quit"))
                {
                    System.out.println("Services are finished");
                    finished = true;
                }
                else if(input.hasNext("1"))
                {
                    input.hasNextLine();
                    boolean one = true;
                    while(one == true)
                    {
                        System.out.println("Enter the two busstop ID's you wish to find the shortest path between");
                        if (input.hasNextLine())
                        {
                            String user = input.nextLine();
                            String[] stops = user.split(",");
                            if (user.equalsIgnoreCase("quit"))
                            {
                                finished = true;
                                one = false;
                                System.out.println("Services are finished");
                            }
                            else if(user.equalsIgnoreCase("back"))
                            {
                                System.out.println("Enter 1: to find the shortest path. Enter 2 to find a bus stop. Enter 3 to find a bus at a given time");
                                one = false;
                            }
                            else if(stops.length < 2 || stops.length > 2)
                            {
                                System.out.println("Please enter two valid busstop ID numbers");
                            }
                            else if (stops.length ==2)
                            {
                                try
                                {
                                    int stopA= Integer.parseInt(stops[0]);
                                    int stopB = Integer.parseInt(stops[1]);
                                    System.out.println("The shortest distance from stop 1: "+ stopA + "to" + stopB + "is" + graph.shortestTime(stopA, stopB));
                                }
                                catch(NumberFormatException n)
                                {
                                    System.out.println("Please enter numbers only");
                                }
                            }
                            else
                            {
                                System.out.println("Please enter two numbers with commas separating them");
                            }
                        }
                    }
                }
                else if(input.hasNext("2"))
                {
                    input.nextLine();
                    boolean two = true;
                    while(two == true)
                    {
                        System.out.println("Enter your desired bus stop");
                        if(input.hasNextLine())
                        {
                            String user = input.nextLine();
                            if(user.equalsIgnoreCase("quit"))
                            {
                                finished = true;
                                two = false;
                                System.out.println("Services are finished");
                            } else if (user.equalsIgnoreCase("back"))
                            {
                                System.out.println("Enter 1: to find the shortest path. Enter 2 to find a bus stop. Enter 3 to find a bus at a given time");
                                two = false;
                            }
                            else
                            {
                                ArrayList<String> search = TST.search(user.toUpperCase());
                                for(int j = 0; search.size > j; ++j)
                                {
                                    System.out.println(search.get(j));
                                }
                            }
                        }
                    }
                }
                else if(input.hasNext("3"))
                {
                    input.nextLine();
                    boolean three = true;
                    while(three == true)
                    {
                        System.out.println(("Please enter your desired arrival time"));
                        if(input.hasNextLine())
                        {
                            String user = input.nextLine();
                            String[] time = user.split(":");
                            if(user.equalsIgnoreCase("quit"))
                            {
                                finished = true;
                                three = false;
                                System.out.println("Services are finished");
                            }
                            else if(user.equalsIgnoreCase("back"))
                            {
                                System.out.println("Enter 1: to find the shortest path. Enter 2 to find a bus stop. Enter 3 to find a bus at a given time");
                                three = false;
                            }
                            else if(time.length < 3 || time.length > 3)
                            {
                                System.out.println("Please enter the correct time, by hour minute and second");
                            }
                            else if(time.length == 3)
                            {
                                try
                                {
                                    int h = Integer.parseInt(time[0]);
                                    int m = Integer.parseInt(time[1]);
                                    int s = Integer.parseInt(time[2]);
                                    if (h < 0 || h > 23)
                                    {
                                        System.out.println("Sorry, enter the hour in 24 hour time frame");
                                    }
                                    else if(m < 0 || m > 59)
                                    {
                                        System.out.println("Sorry, please enter the minutes in a 60 minute hour");
                                    }
                                    else if(s < 0 || s > 59)
                                    {
                                        System.out.println("Sorry, please enter the seconds in a 60 second minute.");
                                    }
                                    else
                                    {
                                        List<stopTimes.info> stops = stopTimes.getStopsInfo(user);
                                        if(myRoute!= null && myRoute.size()>0)
                                        {
                                            System.out.println("Arrival time: " + myRoute.size() + "");
                                            int j = 1;
                                            for(stopTimes.info s:myRoute)
                                            {
                                                System.out.println(j+".)" + "Trip ID:" + s:myRoute);
                                                System.out.printf("Departure Time:%s %nStop Id:%d %nStop Sequence:%d" +"+ %nStop Headsign:%s %nPickup Type:%d %nDrop Off Type:%d %nShape Distance Traveled:%.3f%n," + "s.departure_time,s.stop_id,s.stop_sequence,s.stop_headsign,s.pickup_type," + "s.drop_off_type,s.shape_dist_traveled");
                                                System.out.println();
                                                j++;
                                            }
                                        }
                                          else
                                        {
                                            System.out.println("No trips available");
                                        }
                                    }
                                }
                                catch(NumberFormatException n)
                                {
                                    System.out.println("Error");
                                }
                            }
                            else
                            {
                                System.out.println("Error, please try again.");
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Please enter 1, 2, or 3");
                    input.nextLine();
                }
            }
        }
        input.close();
    }
    class myRoute extends stopTimes
    {
        myRoute(String filename) throws FileNotFoundException {
            super(filename);
        }
    }
}