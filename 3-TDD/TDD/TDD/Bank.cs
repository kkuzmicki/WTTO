using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TDD.Models;

namespace TDD
{
    public class Bank
    {
        public Dictionary<string, User> users = new();

        public int newUserId = 3;

        public void CreateUser(User newUser)
        {
            users.Add(newUserId++.ToString(), newUser);
        }

        public bool DeleteUser(string userId)
        {
            return users.Remove(userId);
        }

        public bool EditUser(string id, string newEmail)
        {
            if(users.ContainsKey(id))
            {
                users[id].Email = newEmail;
                return true;
            }
            else
            {
                return false;
            }
        }

        public List<string>? ShowUserOperations(string id)
        {
            if(users.ContainsKey(id))
            {
                return users[id].OperationsList ?? new List<string>() { "BRAK OPERACJI" };
            }
            else
            {
                return null;
            }
        }

        public bool AddCashForUser(decimal cashAmount, string id)
        {
            if (users.ContainsKey(id))
            {
                users[id].Cash += cashAmount;
                return true;
            }
            else return false;
        }

        public bool GiveCredit(string id, decimal amount, decimal percentage)
        {
            if (users.ContainsKey(id))
            {
                users[id].Cash += amount;
                users[id].credit += amount * (1 + percentage / 100);
                return true;
            }
            else return false;
        }

        public string ShowUserOperation(string userId, int operationId)
        {
            if (users.ContainsKey(userId))
            {
                if (users[userId].OperationsList.Count() >= operationId - 1)
                {
                    return users[userId].OperationsList[operationId];
                }
                else return "";
            }
            else return "";
        }
    }
}
