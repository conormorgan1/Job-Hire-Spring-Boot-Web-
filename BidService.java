package ie.conor.services;
import java.util.List;

import ie.conor.entities.Bid;

public interface BidService {
	Bid findBid(int bidId);
	Bid save(Bid bid);
	List<Bid> getBidsWithSameAmount(int amount, int jobId);
	List<Bid> findAllBids();
}
