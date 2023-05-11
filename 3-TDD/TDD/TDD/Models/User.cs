using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TDD.Models
{
    public class User
    {
        public string? Email { get; set; }
        public decimal Cash { get; set; }
        public bool IsAdmin { get; set; }
        public List<string>? OperationsList { get; set; }
        public decimal credit { get; set; } = 0;
    }
}
