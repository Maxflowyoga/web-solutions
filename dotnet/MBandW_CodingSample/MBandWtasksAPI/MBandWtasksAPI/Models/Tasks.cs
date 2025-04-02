using System.ComponentModel.DataAnnotations;

namespace MBandWtasksAPI.Models
{
	public partial class Tasks
	{
        // Task Id, NOT NULL
        [Key]
        public int id_task { get; set; }

        // Task Name, NOT NULL
        public string? task_name { get; set; }

        // Task Description
        public string? task_description { get; set; }

        // Due Date, NOT NULL
        public DateTime? task_due_date { get; set; }

        // Start Date
        public DateTime? task_start_date { get; set; }

        // End Date
        public DateTime? task_end_date { get; set; }

        // Priority (High, Medium, Low), NOT NULL
        public string? task_priority { get; set; }

        // Status (New, In Progress, Finished), NOT NULL
        public string? task_status { get; set; }


        public Tasks()
		{
		}

	}
}
// The date will be formatted as: yyyy-MM-ddTHH:mm:ss
// example:  2025-03-22
