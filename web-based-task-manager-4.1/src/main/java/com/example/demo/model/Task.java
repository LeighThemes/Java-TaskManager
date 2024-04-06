package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Calendar;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date dueDate;
    private boolean isComplete;

    public Task() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

	public void setDueDate(Date dueDate) {
		
		Calendar calendar = Calendar.getInstance();
	    // Set the calendar time to the given dueDate
	    calendar.setTime(dueDate);
	    // Add 12 hours to the calendar (half a day)
	    calendar.add(Calendar.HOUR_OF_DAY, 12);
	    // Get the updated dueDate after adding half a day
	    Date newDueDate = calendar.getTime();
	    // Assign the updated dueDate to the instance variable
	    this.dueDate = newDueDate;
	    //this.dueDate = dueDate;
	    System.out.println(dueDate);
	}
	
	public boolean getIsComplete() {
	    return isComplete;
	}
	
	public void setIsComplete(boolean complete) {
	    isComplete = complete;
	}
    
	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean complete) {
		this.isComplete = complete;
	}
}

