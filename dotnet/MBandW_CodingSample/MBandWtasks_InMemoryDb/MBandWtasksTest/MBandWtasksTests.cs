using MBandWtasks.Models;

namespace MBandWtasksTest;

[TestClass]
public class MBandWtasksTests
{
    /*
   	+	Priority (High, Medium, Low) 
	+	Status (New, In Progress, Finished)

    Sample URL to test solution Via SwaggerUI:
        https:// localhost:7092/swagger/index.html
    */

    [TestMethod]
    public void testAddNewTask()
    {
        int id = 0;
        String taskName = "Task Name";
        String taskDescription = "Task Description";
        DateTime dueDate = DateTime.Parse("2025-04-7");
        DateTime startDate = DateTime.Parse("2025-03-12");
        DateTime endDate = DateTime.Parse("2025-03-22");
        String taskPriority = "High";
        String taskStatus = "Finished";


        var newTask = new MBandWtask();

    }


    [TestMethod]
    public void testAddUpdateSameTask()
    {
        int id = 0;
        String taskName = "Task Name is New"; //updated value
        String taskDescription = "Task Description Is Updated"; //updated value
        DateTime dueDate = DateTime.Parse("2025-04-8"); //updated value
        DateTime startDate = DateTime.Parse("2025-03-12");
        DateTime endDate = DateTime.Parse("2025-03-22");
        String taskPriority = "High";
        String taskStatus = "In Progress"; //updated value


        var newTask = new MBandWtask();

    }


    [TestMethod]
    public void testDueDateNotInPast()
    {
        int id = 0;
        String taskName = "Task Name is New";
        String taskDescription = "Task Description for Date in the past";
        DateTime dueDate = DateTime.Parse("2025-03-02"); //Date in Past
        DateTime startDate = DateTime.Parse("2025-03-12");
        DateTime endDate = DateTime.Parse("2025-03-22");
        String taskPriority = "High";
        String taskStatus = "New";


        var newTask = new MBandWtask();

    }


    [TestMethod]
    public void testDateNotHoliday()
    {
        int id = 0;
        String taskName = "Task Name";
        String taskDescription = "Task Description for testing a Holiday due date";
        DateTime dueDate = DateTime.Parse("2025-07-04"); //Test Holiday, July 4th
        DateTime startDate = DateTime.Parse("2025-03-12");
        DateTime endDate = DateTime.Parse("2025-03-22");
        String taskPriority = "High";
        String taskStatus = "In Progress";


        var newTask = new MBandWtask();

    }


    [TestMethod]
    public void testDateNotOnWeekend()
    {
        int id = 0;
        String taskName = "Task Name";
        String taskDescription = "Task Description";
        DateTime dueDate = DateTime.Parse("2025-03-22"); //Date on the weekend
        DateTime startDate = DateTime.Parse("2025-03-12");
        DateTime endDate = DateTime.Parse("2025-03-22");
        String taskPriority = "High";
        String taskStatus = "New";


        var newTask = new MBandWtask();

    }



    [TestMethod]
    public void testHighPriorityCountMax100SameDueDateFinishedStatus()
    {
        int id = 0;
        String taskName = "Task Name";
        String taskDescription = "Task Description";
        DateTime dueDate = DateTime.Parse("2025-03-02");
        DateTime startDate = DateTime.Parse("2025-03-12");
        DateTime endDate = DateTime.Parse("2025-03-22");
        String taskPriority = "High";
        String taskStatus = "Finished";


        var newTask = new MBandWtask();
        //recursively test and can easily test in the SwaggerUI, clicking execute and changing values


        //You can also run in debug mode to see the varying count totals of records versus Finished Status of
        //High priority result set values

    }
}

