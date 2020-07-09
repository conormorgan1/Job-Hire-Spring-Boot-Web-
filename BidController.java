package ie.conor.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.conor.entities.Job;
import ie.conor.entities.MyUser;
import ie.conor.entities.Bid;
import ie.conor.formobjects.BidForm;
import ie.conor.services.JobService;
import ie.conor.services.BidService;

@Controller
public class BidController {

	@Autowired
	BidService bidService;
	
	@Autowired 
	JobService jobService;
	
	@GetMapping("/bids")
	public String showBids(Model model, Locale locale)
	{
		List<Bid> bids = bidService.findAllBids();
		model.addAttribute("bids", bids);
		return "bids";
	}
	
	
	@GetMapping("/bid")
	public String showBidById(@RequestParam(name="id") int id, Model model, Locale locale)
	{
		Bid bid = bidService.findBid(id);
		if (bid == null){
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("bid", bidService.findBid(id));
		return "bid";
	}
	
	@GetMapping("/bidsinjob/{id}")
	public String showBidsInJob(@PathVariable(name="id") int id, Model model, Locale locale)
	{
		Job job = jobService.findJob(id);
		if ( job == null) {
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("job", job);
		return "bidsInJob";
	}
	
	@GetMapping("newbid")
	public String addNewBid(Model model)
	{
		model.addAttribute("bidForm", new BidForm());
		model.addAttribute("jobs", jobService.listInAlphabeticalOrder());
		return "newbid";
	}

	@PostMapping("newbid")
	public String addNewBid(Model model, @Valid BidForm bidForm, BindingResult binding, RedirectAttributes redirectAttributes)
	{
		if (binding.hasErrors()){
			model.addAttribute("jobs", jobService.listInAlphabeticalOrder());
			return "newbid";
		}
		Bid bid = new Bid(bidForm.getAmount(), jobService.findJob(bidForm.getJobId()));
		if (bidService.save(bid) == null){
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:/newbid";
		}
		return "redirect:/bid?id="+bid.getBidId();
	}

}
