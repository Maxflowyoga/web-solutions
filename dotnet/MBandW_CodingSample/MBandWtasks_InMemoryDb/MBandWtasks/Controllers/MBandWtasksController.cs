using Microsoft.AspNetCore.Mvc;
using MBandWtasks.Models;
using MBandWtasks.Data;
using System.Diagnostics;
using System.Linq;


namespace MBandWtasks.Controllers
{
    [Route("api/[controller]/[action]")]
    public class MBandWtasksController : Controller
    {

        private readonly ApiContext _context;

        public MBandWtasksController(ApiContext context)
        {
            _context = context;
        }

        // Create/Update Tasks
        [HttpPost]
        public JsonResult CreateUpdate(MBandWtask task)
        {
            // validate if Date Time is
            /*
           	•	When creating a Task, the Due Date cannot be in the past 
	        •	The due date cannot be on a Holiday or weekend. 
	        •	The system should not have more than 100 High Priority tasks which have the same due date and are not finished
             * 
             */
            var now = DateTime.Now;
            DateTime? dueDate1 = task.DueDate;
            DateTime dueDate = (DateTime)dueDate1;
            Debug.WriteLine("Date Now is: " + now);
            Debug.WriteLine("Task Due Date is: " + dueDate);

            bool dueDateNotPast = dueDate > now;
            Debug.WriteLine("Due Date dueDateNotPast " + dueDate.Date + " is: " + dueDateNotPast);

            bool dueDateNotAHoliday = true;
            bool dueDateNotOnWeekend = true;
            // List of Federal Holidays based on clients locale or compile a global
            // list in the database to track all/new holidays added or holidays removed, for any years based on
            // company SOPs and further company standard requirements
            var federalUSAHolidays = new List<DateTime> {
                new DateTime(2025, 1, 1),
                new DateTime(2025, 1, 20),
                new DateTime(2025, 2, 17),
                new DateTime(2025, 5, 26),
                new DateTime(2025, 6, 19),
                new DateTime(2025, 7, 4),
                new DateTime(2025, 9, 1),
                new DateTime(2025, 10, 13),
                new DateTime(2025, 11, 11),
                new DateTime(2025, 11, 27),
                new DateTime(2025, 12, 25) };


            if (federalUSAHolidays.Contains(dueDate.Date))
            {
                Debug.WriteLine("Due Date is a USA Federal Holiday!");
                dueDateNotAHoliday = false;
            }

            Debug.WriteLine("Due Date dueDateNotAHoliday is: " + dueDateNotAHoliday);

            // Check if dueDate.Date is on Saturday or Sunday weekend day
            if (dueDate.DayOfWeek == DayOfWeek.Saturday || dueDate.DayOfWeek == DayOfWeek.Sunday)
            {
                Debug.WriteLine("Due Date " + dueDate.DayOfWeek + " is on a weekend, please update");
                dueDateNotOnWeekend = false;
            }

            Debug.WriteLine("Due Date dueDateNotOnWeekend is: " + dueDateNotOnWeekend);

            // (1) Check not more than 100 High Priority tasks
            bool noMaxHighPriority = true;

            var currentTasksList = _context.Tasks.ToList();

            Debug.WriteLine("Current tasks list count is: " + currentTasksList.Count);
            

            int highPriorityCount = 0;

            // (2) which have the same due date (3) and are not finished
            highPriorityCount = _context.Tasks
            .Where(o => o.TaskPriority == "High" & o.DueDate == task.DueDate & o.TaskStatus == "Finished")
            .Count();

            Debug.WriteLine("Valid High Priority tasks count is: " + highPriorityCount);
            if(highPriorityCount >= 100)
            {
                noMaxHighPriority = false;
            }

            // validat task priority is one of the following: High, Medium, Low
            bool isValidTaskPriority = false;
            if(task.TaskPriority.Equals("High") || task.TaskPriority.Equals("Medium") || task.TaskPriority.Equals("Low"))
            {
                isValidTaskPriority = true;
            }

            // validate task status is one of the following: New, In Progress, Finished
            bool isValidTaskStatus = false;
            if (task.TaskStatus.Equals("New") || task.TaskStatus.Equals("In Progress") || task.TaskStatus.Equals("Finished"))
            {
                isValidTaskStatus = true;
            }


            if (dueDateNotPast && dueDateNotAHoliday && dueDateNotOnWeekend && noMaxHighPriority && isValidTaskStatus && isValidTaskPriority) // & not a weekend or Holiday
            {

                if (task.Id == 0)
                {

                    _context.Tasks.Add(task);
                }
                else
                {
                    var tasksInDb = _context.Tasks.Find(task.Id);

                    if (tasksInDb == null)
                        return new JsonResult(NotFound());

                    tasksInDb = task;
                }

                _context.SaveChanges();

                return new JsonResult(Ok(task));

            } else
            {
                String returnString = "New Task";
                if(!dueDateNotPast)
                {
                    returnString = returnString + " in the Past,";
                }
                if(!dueDateNotAHoliday)
                {
                    returnString = returnString + " on a holiday,";
                }
                if(!dueDateNotOnWeekend)
                {
                    returnString = returnString + " on the weekend,";
                }
                if(!noMaxHighPriority)
                {
                    returnString = returnString + " already at its max Finished count of 100 with that same Due Date,";
                }
                if(!isValidTaskPriority)
                {
                    returnString = returnString + " must have a priority of 'High', 'Medium' or 'Low'";
                }
                if (!isValidTaskStatus)
                {
                    returnString = returnString + " must have a status of 'New', 'In Progress' or 'Finished'";
                }
                return new JsonResult(returnString + " please update your new task and resubmit, thank you.");
            }

        }

        // Get Tasks
        [HttpGet]
        public JsonResult Get(int id)
        {
            var result = _context.Tasks.Find(id);

            if (result == null)
                return new JsonResult(NotFound());

            return new JsonResult(Ok(result));
        }


        // Delete Tasks
        [HttpDelete]
        public JsonResult Delete(int id)
        {
            var result = _context.Tasks.Find(id);

            if (result == null)
                return new JsonResult(NotFound());

            _context.Tasks.Remove(result);
            _context.SaveChanges();

            return new JsonResult(NoContent());
        }


        //Get All Tasks
        [HttpGet()]
        public JsonResult GetAll()
        {
            var result = _context.Tasks.ToList();

            return new JsonResult(Ok(result));
        }


    }
}

