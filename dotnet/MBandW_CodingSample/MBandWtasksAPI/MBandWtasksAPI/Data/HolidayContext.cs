using Microsoft.EntityFrameworkCore;
using MBandWtasksAPI.Models;


namespace MBandWtasksAPI.Data
{

    public partial class HolidayContext : DbContext
    {
        public HolidayContext()
        {
        }

        public HolidayContext(DbContextOptions<HolidayContext> options)
            : base(options)
        {

        }

        public virtual DbSet<Holiday> Holiday { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            //base.OnConfiguring(optionsBuilder);
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Holiday>(entity =>
            {
                entity.ToTable("tb_holidays");

                entity.Property(e => e.Holiday_Id);

                entity.Property(e => e.Holiday_Name)
                .HasMaxLength(100)
                .IsFixedLength();

                entity.Property(e => e.Holiday_Description)
                .HasMaxLength(3000)
                .IsFixedLength();

                entity.Property(e => e.Holiday_Date);

                entity.Property(e => e.Holiday_Host_Country)
                .HasMaxLength(45)
                .IsFixedLength();

                entity.Property(e => e.Holiday_Status)
                .HasMaxLength(45)
                .IsFixedLength();

                entity.Property(e => e.Holiday_Created_Date);

                entity.Property(e => e.Holiday_Modified_Date);

                entity.Property(e => e.Holiday_Created_By)
                .HasMaxLength(45)
                .IsFixedLength();

                entity.Property(e => e.Holiday_Modified_By)
                .HasMaxLength(45)
                .IsFixedLength();

                entity.Property(e => e.Holiday_Active);

            });


            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}

