package ie.conor.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int jobId;
	@Column(nullable=false, unique=true)
	private String jobName;
	
	@Column(nullable=false, unique=false)
	private String description;
	
	@ManyToOne
	private MyUser addedBy; 
	
	@Column(nullable=true, unique=false)
	private LocalDate dateFirstPublished = LocalDate.now();
	
	@OneToMany(mappedBy="job", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JsonIgnore
    private List<Bid> bids = new ArrayList<>();
	
	public Job(String jobName, String description, LocalDate dateFirstPublished, MyUser addedBy) {
		this.jobName = jobName;
		this.description = description;
		this.dateFirstPublished = dateFirstPublished;
		this.addedBy = addedBy;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("Job [jobId=" + jobId + ", jobName=" + jobName + ", description="+ description+", dateFirstPublished="+ dateFirstPublished+", added by="+ addedBy +"]");
		for(Bid bid: this.bids)
			s.append("\n\t" + bid.getAmount());
		return s.toString();
	}

}
