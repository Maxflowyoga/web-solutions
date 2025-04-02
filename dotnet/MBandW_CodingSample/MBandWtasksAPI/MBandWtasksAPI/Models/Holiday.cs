using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace MBandWtasksAPI.Models
{
	public partial class Holiday
	{
        [Key]
        [Column("id_tb_holidays")]
        public int Holiday_Id { get; set; }

        [Column("holiday_name")]
        [StringLength(100)]
        public string? Holiday_Name { get; set; }

        [Column("holiday_description")]
        [StringLength(3000)]
        public string? Holiday_Description { get; set; }

        [Column("holiday_date")]
        public DateTime? Holiday_Date { get; set; }

        [Column("holiday_host_country")]
        [StringLength(45)]
        public string? Holiday_Host_Country { get; set; }

        [Column("holiday_status")]
        [StringLength(45)]
        public string? Holiday_Status { get; set; }

        [Column("holiday_created_date")]
        public DateTime? Holiday_Created_Date { get; set; }

        [Column("holiday_modified_date")]
        public DateTime? Holiday_Modified_Date { get; set; }

        [Column("holiday_created_by")]
        [StringLength(45)]
        public string? Holiday_Created_By { get; set; }

        [Column("holiday_modified_by")]
        [StringLength(45)]
        public string? Holiday_Modified_By { get; set; }

        [Column("holiday_active")]
        public int? Holiday_Active { get; set; }


		public Holiday()
		{
		}
	}
}

/*
    tb_holidays

       [id_tb_holidays]
      ,[holiday_name]
      ,[holiday_description]
      ,[holiday_date]
      ,[holiday_host_country]
      ,[holiday_status]
      ,[holiday_created_date]
      ,[holiday_modified_date]
      ,[holiday_created_by]
      ,[holiday_modified_by]
      ,[holiday_active]
*/