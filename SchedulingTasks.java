package ie.conor;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.conor.entities.Bid;
import ie.conor.entities.Job;
import ie.conor.services.BidService;
import ie.conor.services.JobService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingTasks {
	
	@Autowired
	JobService jobService;
	
	private LocalDate date;
	long daysToSubtract = 20;
	private LocalDate oldDate;
	private int jobId;
	 
	@Scheduled(cron = "0 0 15 * * *")
	public boolean findJobToClose() {
		List<Job> jobs = jobService.listAllJobs();
		for (Job j: jobs) {
			date = j.getDateFirstPublished();
			jobId= j.getJobId();
			oldDate = LocalDate.now().minusDays(daysToSubtract);
			if( oldDate == date)
				return jobService.deleteJob(jobId);
		}
		return false;
		
	}

}
