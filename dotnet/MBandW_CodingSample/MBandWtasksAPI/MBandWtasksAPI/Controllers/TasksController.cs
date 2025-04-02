using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MBandWtasksAPI.Data;
using MBandWtasksAPI.Models;
using System.Diagnostics;

namespace MBandWtasksAPI.Controllers
{
    [Route("api/[controller]/[action]")]
    [ApiController]
    public class TasksController : ControllerBase
    {
        private readonly TasksContext _context;


        private readonly HolidayContext _holiday_context;


        public TasksController(TasksContext context, HolidayContext holidayContext)
        {
            _context = context;
            _holiday_context = holidayContext;

        }


        // GET: api/Tasks
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Tasks>>> GetTasks()
        {
          if (_context.Tasks == null)
          {
              return NotFound();
          }
            return await _context.Tasks.ToListAsync();
        }

        // GET: api/Tasks/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Tasks>> GetTasks(int id)
        {
          if (_context.Tasks == null)
          {
              return NotFound();
          }
            var tasks = await _context.Tasks.FindAsync(id);

            if (tasks == null)
            {
                return NotFound();
            }

            return tasks;
        }

        // PUT: api/Tasks/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTasks(int id, Tasks tasks)
        {
            if (id != tasks.id_task)
            {
                return BadRequest();
            }

            _context.Entry(tasks).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TasksExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Tasks
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Tasks>> PostTasks(Tasks tasks)
        {
          if (_context.Tasks == null)
          {
              return Problem("Entity set 'TasksContext.Tasks'  is null.");
          }
            _context.Tasks.Add(tasks);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTasks", new { id = tasks.id_task }, tasks);
        }



        // Create/Update Tasks
        [HttpPost]
        public JsonResult CreateUpdate(Tasks task)
        {
            // validate if Date Time is
            /*
           	•	When creating a Task, the Due Date cannot be in the past 
	        •	The due date cannot be on a Holiday or weekend. 
	        •	The system should not have more than 100 High Priority tasks which have the same due date and are not finished
             * 
             */
            var now = DateTime.Now;
            DateTime? dueDate1 = task.task_due_date;
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
            /*
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
            */
            int matchingHoliday = 0;

            matchingHoliday = _holiday_context.Holiday.Where(o => o.Holiday_Date == task.task_due_date).Count();

            string holidayName = "";

            var holidayNames = _holiday_context.Holiday.Where(o =>
                    o.Holiday_Date == task.task_due_date).Select(o => o.Holiday_Name);

            foreach (var x in holidayNames)
            {
                holidayName = x;
            }

            if (matchingHoliday > 0)
            {
                Debug.WriteLine("Due Date, " + task.task_due_date + " " + holidayName
                    + ", is a USA Federal Holiday with Matching count of: " + matchingHoliday);
                dueDateNotAHoliday = false;
            }
            
            /*
            if (federalUSAHolidays.Contains(dueDate.Date))
            {
                Debug.WriteLine("Due Date is a USA Federal Holiday!");
                dueDateNotAHoliday = false;
            }*/

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
            .Where(o => o.task_priority == "High" & o.task_due_date == task.task_due_date & o.task_status == "Finished")
            .Count();

            Debug.WriteLine("Valid High Priority tasks count is: " + highPriorityCount);
            if (highPriorityCount >= 100)
            {
                noMaxHighPriority = false;
            }

            // validat task priority is one of the following: High, Medium, Low
            bool isValidTaskPriority = false;
            if (task.task_priority.Equals("High") || task.task_priority.Equals("Medium") || task.task_priority.Equals("Low"))
            {
                isValidTaskPriority = true;
            }

            // validate task status is one of the following: New, In Progress, Finished
            bool isValidTaskStatus = false;
            if (task.task_status.Equals("New") || task.task_status.Equals("In Progress") || task.task_status.Equals("Finished"))
            {
                isValidTaskStatus = true;
            }


            if (dueDateNotPast && dueDateNotAHoliday && dueDateNotOnWeekend && noMaxHighPriority && isValidTaskStatus && isValidTaskPriority) // & not a weekend or Holiday
            {

                if (task.id_task == 0)
                {

                    _context.Tasks.Add(task);
                }
                else
                {
                    var tasksInDb = _context.Tasks.Find(task.id_task);

                    if (tasksInDb == null)
                        return new JsonResult(NotFound());

                    tasksInDb = task;
                }

                _context.SaveChanges();

                return new JsonResult(Ok(task));

            }
            else
            {
                String returnString = "New Task";
                if (!dueDateNotPast)
                {
                    returnString = returnString + " in the Past,";
                }
                if (!dueDateNotAHoliday)
                {
                    returnString = returnString + " on a holiday (" + holidayName + "),";
                }
                if (!dueDateNotOnWeekend)
                {
                    returnString = returnString + " on the weekend,";
                }
                if (!noMaxHighPriority)
                {
                    returnString = returnString + " already at its max Finished count of 100 with that same Due Date,";
                }
                if (!isValidTaskPriority)
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

        // DELETE: api/Tasks/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTasks(int id)
        {
            if (_context.Tasks == null)
            {
                return NotFound();
            }
            var tasks = await _context.Tasks.FindAsync(id);
            if (tasks == null)
            {
                return NotFound();
            }

            _context.Tasks.Remove(tasks);
            await _context.SaveChangesAsync();

            return NoContent();
        }






        private bool TasksExists(int id)
        {
            return (_context.Tasks?.Any(e => e.id_task == id)).GetValueOrDefault();
        }
    }
}
