package Lab05;
/**
 * Author: Daniel Yu
 * 
 * To create a Job that has a time of arrival, completion, and current progress
 * and when it finishes.
 */

public class Job {
    double arrivalTime;
    double processingTime;
    double processedTime;
    double finishTime;

    // this constructs the job with the specified arrival time and necessary total
    // processing time.
    public Job(double arrivalTime, double processingTime) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        finishTime = 0;
        processedTime = 0;
    }

    // : this returns the arrival time of this job.
    public double getArrivalTime() {
        return arrivalTime;
    }

    // : this returns the total necessary processing time of the job.
    public double getTotalProcessingTime() {
        return processingTime;
    }

    // : this returns the time spent working on this job so far.
    public double getTimeProcessed() {
        return processedTime;
    }

    // : this method returns the necessary time remaining to spend working on this
    // job.
    public double getTimeRemaining() {
        return processingTime - processedTime;
    }

    // : returns true if this job has been run to completion.
    public boolean isFinished() {
        return getTimeProcessed() >= processingTime;
    }

    // : this sets the time when the job completed.
    public void setFinishTime(double time) {
        finishTime = time;
    }

    // : this method returns the time when the job was completed.
    public double getFinishTime() {
        return finishTime;
    }

    // : returns the difference in time between the arrival and finish times of this
    // job.
    public double timeInQueue() {
        return finishTime - arrivalTime;
    }

    // : processes this job for the specified time units of time. Make sure that all
    // necessary fields of the class are updated.
    public void process(double time) {
        processedTime += time;
    }

    public String toString() {
        return "[" + arrivalTime + ", " + processingTime + "]";
    }
}
