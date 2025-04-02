using Microsoft.EntityFrameworkCore;
using MBandWtasks.Data;

var builder = WebApplication.CreateBuilder(args);

// Services for the container.

builder.Services.AddDbContext<ApiContext>
    (opt => opt.UseInMemoryDatabase("MBandWtasksDb"));

builder.Services.AddControllers();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();

