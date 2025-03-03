package Lab05;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

public class Server {
    // this constructor initializes whatever fields the Server will need in order
    // for it to support the other methods. The Server can assume that the system
    // time, number of jobs processed, and total wait time will all start at 0 at
    // its initialization. The Queue of jobs will be empty.
    LinkedList<Job> queue;
    double systemTime;
    int jobsProcessed;
    float totalWaitTime;
    float remainingWorkInQueue;

    // this constructor initializes whatever fields the Server will need in order
    // for it to support the other methods. The Server can assume that the system
    // time, number of jobs processed, and total wait time will all start at 0 at
    // its initialization. The Queue of jobs will be empty.
    public Server() {
        queue = new LinkedList<>();
        systemTime = 0;
        jobsProcessed = 0;
        totalWaitTime = 0;
        remainingWorkInQueue = 0;
    }

    // : adds the specified Job job into the queue.
    public void addJob(Job job) {
        remainingWorkInQueue += job.getTotalProcessingTime();
        queue.offer(job);

    }
    // : advances the system time of this queue to the specified time time. We'll
    // use this method to process work in the Queue until the next event--in this
    // case, a new job arriving, happens. When doing this, we must also spend that
    // time processing the current job or jobs that the server is working on. We
    // must keep in mind that a Server can only process one job at a time, but that
    // once a job is finished, it will immediately move onto the next job in the
    // Queue. Using a Queue for this ensures that the Server processes the jobs in
    // the order of arrival. Practically, what processing a job means is that we
    // call the current Job's process method with the amount of time we want to
    // spend processing it. When the time passed into the processTo method is less
    // than the remaining processing time for the current Job, we simply call the
    // current Job's process method with the amount of time passed in. Otherwise, we
    // need to process the current Job to completion, then spend the remaining time
    // processing the next Job(s). (Note that this method should be able to handle
    // processing multiple Jobs to completion one after another). When a job has
    // completed, we must track 2 things: 1) we must make sure to call the Job's
    // setFinishTime method with the appropriate value, and 2) we must update the
    // Server's counter for the number of jobs processed, and the Server's field
    // tracking the total waiting time for Jobs in its Queue.

    public void processTo(double time) {
        double elapsedTime = time - systemTime;
        while (elapsedTime > 0 && queue.size() != 0) {
            Job currentJob = queue.peek();
            if (elapsedTime < currentJob.getTimeRemaining()) {
                currentJob.process(elapsedTime);
                remainingWorkInQueue -= elapsedTime;
                break; // Job processed partially, exit the loop
            } else {
                double deductedTime = currentJob.getTimeRemaining();
                currentJob.setFinishTime(systemTime + deductedTime);
                currentJob.process(elapsedTime);
                jobsProcessed++;
                totalWaitTime += (currentJob.timeInQueue());
                queue.poll(); // Job processed completely, remove from the queue
                remainingWorkInQueue -= deductedTime;
                systemTime += deductedTime;
                elapsedTime -= deductedTime;
            }
        }
        systemTime = time;
    }

    // : returns the total remaining processing time in this Server's queue.
    public double remainingWorkInQueue() {
        return remainingWorkInQueue;

    }

    // : returns the number of Jobs in this Server's queue.
    public int size() {
        return queue.size();
    }

    // : This is necessary for the GUI we've provided. For this method, you can copy
    // and paste the following method into your Server class. Make sure to import
    // the following in order for it to work: java.awt.Graphics; java.awt.Color;
    // java.awt.Toolkit; java.awt.Font.
    public void draw(Graphics g, Color c, double loc, int numberOfServers) {
        double sep = (ServerFarmViz.HEIGHT - 20) / (numberOfServers + 2.0);
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(),
                (int) (72.0 * (sep * .5) / Toolkit.getDefaultToolkit().getScreenResolution())));
        g.drawString("Work: " + (remainingWorkInQueue() < 1000 ? remainingWorkInQueue() : ">1000"), 2,
                (int) (loc + .2 * sep));
        // System.out.println(queue);
        g.drawString("Jobs: " + (size() < 1000 ? size() : ">1000"), 5, (int) (loc + .55 * sep));
        g.setColor(c);
        g.fillRect((int) (3 * sep), (int) loc, (int) (.8 * remainingWorkInQueue()), (int) sep);
        g.drawOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
        if (remainingWorkInQueue() == 0)
            g.setColor(Color.GREEN.darker());
        else
            g.setColor(Color.RED.darker());
        g.fillOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
    }
}
