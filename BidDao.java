package ie.conor.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ie.conor.entities.Bid;

public interface BidDao extends JpaRepository<Bid, Integer> {
	List<Bid> findByJob_JobName(String jobName);
	List<Bid> findByJob_JobId(int jobId);
	List<Bid> findBidByBidId(int bidId);
	boolean existsByAmountAndJob_JobId(int amount, int jobId);
}
