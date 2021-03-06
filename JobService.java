package ie.conor.services;

import java.util.List;

import ie.conor.entities.Job;

public interface JobService {
	
	Job findJob(int id);
	boolean deleteJob(Job job);
	boolean deleteJob(int id);
	Job findByName(String jobName);
	boolean existsByJobId(int jobId);
	String findJobName(int id);
	List<Job> findJobsWithBidId(int bidId);
	Job save(Job job);
	List<Job> listInAlphabeticalOrder();
	List<Job> listAllJobs();
}
