using Microsoft.EntityFrameworkCore;
using MBandWtasksAPI.Models;


namespace MBandWtasksAPI.Data
{

	public partial class TasksContext : DbContext
	{
		public TasksContext()
		{
		}

		public TasksContext(DbContextOptions<TasksContext> options)
			:base(options)
		{

		}

		public virtual DbSet<Tasks> Tasks { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            //base.OnConfiguring(optionsBuilder);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
			modelBuilder.Entity<Tasks>(entity =>
			{
				entity.ToTable("tb_tasks");


				entity.Property(e => e.id_task);

                entity.Property(e => e.task_name)
				.HasMaxLength(100)
				.IsFixedLength();

				entity.Property(e => e.task_description)
				.HasMaxLength(3000)
				.IsFixedLength();

				entity.Property(e => e.task_due_date);

                entity.Property(e => e.task_start_date);

                entity.Property(e => e.task_end_date);

                entity.Property(e => e.task_priority)
				.HasMaxLength(45)
				.IsFixedLength();

                entity.Property(e => e.task_status)
				.HasMaxLength(45)
				.IsFixedLength();

            });


            OnModelCreatingPartial(modelBuilder);
        }

		partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}

