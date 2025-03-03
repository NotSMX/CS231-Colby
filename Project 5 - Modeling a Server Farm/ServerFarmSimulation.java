package Lab05;

/**
 * Author: Daniel Yu
 * 
 * processes the same sequence of jobs using the 4 different dispatchers and
 * compares them. While determining the 'best' dispatcher is situation
 * dependent, we will start by comparing the average waiting time for the
 * sequence of jobs across the 4 types of dispatchers.
 */

public class ServerFarmSimulation {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ServerFarmSimulation <number_of_servers>  <job_sequence_file>");
            return;
        }
        int numberOfServers = Integer.parseInt(args[0]);
        String filename = args[1];

        // int numberOfServers = 30;
        // String filename = "JobSequence_7_30.txt";

        JobDispatcher jd = new RandomDispatcher(numberOfServers, false);
        Queue<Job> jobs = JobReader.readJobFile(filename);
        jd.handleJobs(jobs);
        System.out.println("Average Waiting Time for RandomDispatcher: " + jd.getAverageWaitingTime());

        JobDispatcher rrd = new RoundRobinDispatcher(numberOfServers, false);
        Queue<Job> jobs1 = JobReader.readJobFile(filename);
        rrd.handleJobs(jobs1);
        System.out.println("Average Waiting Time for RoundRobinDispatcher: " + rrd.getAverageWaitingTime());

        JobDispatcher sqd = new ShortestQueueDispatcher(numberOfServers, false);
        Queue<Job> jobs2 = JobReader.readJobFile(filename);
        sqd.handleJobs(jobs2);
        System.out.println("Average Waiting Time for ShortestQueueDispatcher: " + sqd.getAverageWaitingTime());

        JobDispatcher lwd = new LeastWorkDispatcher(numberOfServers, false);
        Queue<Job> jobs3 = JobReader.readJobFile(filename);
        lwd.handleJobs(jobs3);
        System.out.println("Average Waiting Time for LeastWorkDispatcher: " + lwd.getAverageWaitingTime());

    }
}