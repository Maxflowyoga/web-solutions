using System;


namespace MBandWtasks.Models
{
	public class MBandWtask
	{
        // Task Id
        public int Id { get; set; }

        // Task Name
        public string? TaskName { get; set; }

        // Task Description
        public string? TaskDescription { get; set; }

        // Due Date
        public DateTime? DueDate { get; set; }

        // Start Date
        public DateTime? StartDate { get; set; }

        // End Date
        public DateTime? EndDate { get; set; }

        // Priority (High, Medium, Low) 
        public string? TaskPriority { get; set; }

        // Status (New, In Progress, Finished) 
        public string? TaskStatus { get; set; }


        public MBandWtask()
		{
		}
	}
}

// The date will be formatted as: yyyy-MM-ddTHH:mm:ss
// example:  2025-03-22