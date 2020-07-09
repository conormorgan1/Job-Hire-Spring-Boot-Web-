package ie.conor.formobjects;


import java.time.LocalDate;

import javax.validation.constraints.Size;

import ie.conor.entities.MyUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobForm {
	
	@Size(min=4, max=20)
	private String jobName;	
	
	@Size(min=4, max=50)
	private String description;

	private LocalDate dateFirstPublished;
	
	private MyUser addedBy; 


}

