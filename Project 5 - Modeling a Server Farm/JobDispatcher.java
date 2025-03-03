package Lab05;

/**
* Author: Daniel Yu
* 
* To create a base dispatcher that sends jobs to servers
*/

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class JobDispatcher {
    public ArrayList<Server> arr = new ArrayList<Server>();
    public boolean showViz;

    public float systemTime;
    public ServerFarmViz s;

    // : constructs a JobDispatcher with k total Servers. Make sure this initializes
    // all the fields of this class to appropriate values.
    public JobDispatcher(int k, boolean showViz) {
        systemTime = 0;
        for (int i = 0; i < k; i++) {
            arr.add(new Server());
        }
        this.showViz = showViz;
        if (showViz) {
            s = new ServerFarmViz(this);
        }
    }

    // : returns the time
    public float getTime() {
        return systemTime;
    }

    // : returns the jobDispatcher's collection of Servers
    public ArrayList<Server> getServerList() {
        return arr;
    }

    // : this method updates the current time to the specified time and calls the
    // processTo method for each Server it maintains.
    public void advanceTimeTo(double time) {
        systemTime = (float) time;
        for (Server s : arr) {
            s.processTo(time);
        }
    }

    // : advances the time to job's arrival time, if showViz is true, it calls the
    // ServerFarmViz object'srepaint() method, picks the Server appropriate for job
    // (whichever one is returned by the pickServer method), and adds job to the
    // chosen Server, then, if showViz is true, it calls the ServerFarmViz
    // object'srepaint() method again.
    public void handleJob(Job job) {
        advanceTimeTo(job.arrivalTime);
        if (showViz) {
            s.repaint();
        }
        pickServer(job).addJob(job);
        if (showViz) {
            s.repaint();
        }

    }

    // : advances the time to the earliest point when all jobs will have completed.
    public void finishUp() {
        double maxFinishTime = 0;
        for (Server server : arr) {
            maxFinishTime = Math.max(maxFinishTime, systemTime + server.remainingWorkInQueue());
        }
        // System.out.println("FOUND MAXIMUM TIME");
        advanceTimeTo(maxFinishTime);
    }

    // : polls each Job from the specified queue of Jobs and calls handleJob on
    // them. After all the Jobs have been handled, calls finishUp()
    public void handleJobs(Queue<Job> jobs) {
        while (!(jobs.size() == 0)) {
            handleJob(jobs.poll());
        }
        // System.out.println("ALL JOBS ADDED");
        finishUp();
    }

    // : gets the total number of jobs handled across all Servers.
    public int getNumJobsHandled() {
        int totalJobsHandled = 0;
        for (Server server : arr) {
            totalJobsHandled += server.jobsProcessed;
        }
        return totalJobsHandled;
    }

    // : gets the total waiting time for each Server, adds them together, and
    // divides it by the number of jobs handled across all Servers.
    public double getAverageWaitingTime() {
        double totalJobsHandled = getNumJobsHandled();
        double totalWaitingTime = 0;
        for (Server server : arr) {
            totalWaitingTime += server.totalWaitTime;
        }
        // System.out.println(totalWaitingTime + " " + totalJobsHandled);
        return totalWaitingTime / totalJobsHandled;
    }

    // as specified above, this method is abstract as we don't know how to implement
    // this method without knowing what particular algorithm we are following for
    // handling our jobs.
    public abstract Server pickServer(Job j);

    // : This method is necessary for the GUI we've provided. Please download the
    // main file for the GUI here: ServerFarmViz.java. For this method, you can copy
    // and paste the following method into your JobDispatcher class. Make sure to
    // import the following in order for it to
    // work:java.awt.Graphics;java.awt.Color.
    public void draw(Graphics g) {
        double sep = (ServerFarmViz.HEIGHT - 20) / (getServerList().size() + 2.0);
        g.drawString("Time: " + getTime(), (int) sep, ServerFarmViz.HEIGHT - 20);
        g.drawString("Jobs handled: " + getNumJobsHandled(), (int) sep, ServerFarmViz.HEIGHT - 10);
        for (int i = 0; i < getServerList().size(); i++) {
            getServerList().get(i).draw(g, (i % 2 == 0) ? Color.GRAY : Color.DARK_GRAY, (i + 1) * sep,
                    getServerList().size());
        }
    }

}
