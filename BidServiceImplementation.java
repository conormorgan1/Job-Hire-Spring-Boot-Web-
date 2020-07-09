package ie.conor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.conor.dao.BidDao;
import ie.conor.entities.Bid;

@Service
public class BidServiceImplementation implements BidService{

	@Autowired
	private BidDao bidDao;

	@Override
	public List<Bid> getBidsWithSameAmount(int amount, int jobId) {
		return null;
	}

	@Override
	public Bid findBid(int bidId) {
		if (bidDao.existsById(bidId))
			return bidDao.findById(bidId).get();
		return null;
	}

	@Override
	public Bid save(Bid bid) {
		if (bidDao.existsByAmountAndJob_JobId(bid.getAmount(), bid.getJob().getJobId()))
			return null;
		return bidDao.save(bid);	
	}

	@Override
	public List<Bid> findAllBids() {
		return bidDao.findAll();
	}

	
}
