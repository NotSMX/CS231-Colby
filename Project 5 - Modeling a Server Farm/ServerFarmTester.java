package Lab05;
/**
 * Author: Daniel Yu
 * 
 * This will show a visualization of how dispatchers handle the queue of jobs.
 */

public class ServerFarmTester {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ServerFarmTester <dispatcher_type>");
            System.out.println("Valid options: random, round, shortest, least");
            return;
        }

        // take in a string command line argument with valid options: random, round,
        // shortest and least. Your program should run the corresponding dispatcher to
        // the string passed in and visualize the output. Make sure to include a
        // usage statement explaining your command line parameter to the user!
        String dispatcherType = args[0].toLowerCase();
        switch (dispatcherType) {
            case "random":
                JobDispatcher jd = new RandomDispatcher(4, true);
                Queue<Job> jobs = JobReader.readJobFile("jobs.txt");
                jd.handleJobs(jobs);
                // System.out.println(jd.getAverageWaitingTime());
                break;
            case "round":
                JobDispatcher rrd = new RoundRobinDispatcher(4, true);
                Queue<Job> jobs1 = JobReader.readJobFile("jobs.txt");
                rrd.handleJobs(jobs1);
                // System.out.println(rrd.getAverageWaitingTime());
                break;
            case "shortest":
                JobDispatcher sqd = new ShortestQueueDispatcher(4, true);
                Queue<Job> jobs2 = JobReader.readJobFile("jobs.txt");
                sqd.handleJobs(jobs2);
                // System.out.println(sqd.getAverageWaitingTime());
                break;
            case "least":
                JobDispatcher lwd = new LeastWorkDispatcher(4, true);
                Queue<Job> jobs3 = JobReader.readJobFile("jobs.txt");
                lwd.handleJobs(jobs3);
                // System.out.println(lwd.getAverageWaitingTime());
                break;
            default:
                System.out.println("Invalid dispatcher type. Valid options: random, round, shortest, least");
        }
    }

}