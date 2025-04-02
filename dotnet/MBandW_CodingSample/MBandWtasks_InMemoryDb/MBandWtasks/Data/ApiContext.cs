using Microsoft.EntityFrameworkCore;
using MBandWtasks.Models;


namespace MBandWtasks.Data
{
	public class ApiContext : DbContext
	{

		public DbSet<MBandWtask> Tasks { get; set; }

		public ApiContext(DbContextOptions<ApiContext> options)
			:base(options)
		{
		}
	}
}

