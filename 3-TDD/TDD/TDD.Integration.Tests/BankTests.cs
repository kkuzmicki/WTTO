using TDD.Models;

namespace TDD.Integration.Tests
{
    public class BankTests
    {
        public Bank bank = new();

        [SetUp]
        public void Setup()
        {
            bank.users.Add("0", new User() { Email = "adi@wp.pl", Cash = 500.50M, IsAdmin = true, OperationsList = new List<string> { "Zasilenie konta: 500", "Wydatek: 20" } });
            bank.users.Add("1", new User() { Email = "adiiiii@wp.pl", Cash = 500.50M, IsAdmin = false, OperationsList = new List<string> { "Zasilenie konta: 200", "Wydatek: 30" } });
            bank.users.Add("2", new User() { Email = "adi123@wp.pl", Cash = 500.50M, IsAdmin = false, OperationsList = new List<string> { "Zasilenie konta: 300", "Wydatek: 40" } });
            bank.newUserId = 3;
        }

        [TestCase("test@wp.pl", 200, true)]
        public void CreateUserShouldAddUserToList(string expectedEmail, decimal expectedCash, bool expectedIsAdmin)
        {
            bank.CreateUser(new User() { Email = expectedEmail, Cash = expectedCash, IsAdmin = expectedIsAdmin });

            try
            {
                Assert.Multiple(() =>
                {
                    Assert.That(bank.users["3"].Email, Is.EqualTo(expectedEmail));
                    Assert.That(bank.users["3"].Cash, Is.EqualTo(expectedCash));
                    Assert.That(bank.users["3"].IsAdmin, Is.EqualTo(expectedIsAdmin));
                });
            }
            catch(Exception e)
            {
                Assert.Fail(e.Message);
            }
        }

        [TestCase("1")]
        public void DeleteUserShouldRemoveUserFromList(string id)
        {
            bank.DeleteUser(id);

            Assert.That(bank.users.ContainsKey(id), Is.False);
        }

        [TestCase("2", "aaa@op.pl")]
        public void EditUserShouldEditUserEmailInList(string id, string expectedEmail)
        {
            bank.EditUser(id, expectedEmail);

            Assert.That(bank.users[id].Email == expectedEmail, Is.True);
        }

        [Test]
        public void ShowUserOperationsShouldReturnProperList()
        {
            List<string> expectedList = new List<string> { "Zasilenie konta: 200", "Wydatek: 30" };
            List<string> actualUserOperations = bank.ShowUserOperations("1");

            Assert.Multiple(() =>
            {
                Assert.That(expectedList[0], Is.EqualTo(actualUserOperations[0]));
                Assert.That(expectedList[1], Is.EqualTo(actualUserOperations[1]));
            });
        }

        [TestCase(320, "1")]
        public void AddCashShouldIncreaseUserCash(decimal cashAmount, string id)
        {
            decimal expectedCashAmount = bank.users[id].Cash + cashAmount;

            bank.AddCashForUser(cashAmount, id);

            Assert.That(bank.users[id].Cash, Is.EqualTo(expectedCashAmount));
        }

        [TestCase("1", 1000, 10)]
        public void AddCreditShouldSetProperValues(string id, decimal amount, decimal percentage)
        {
            decimal expectedCredit = amount * (percentage / 100 + 1);
            decimal expectedCash = bank.users[id].Cash + amount;

            bank.GiveCredit(id, amount, percentage);

            Assert.Multiple(() =>
            {
                Assert.That(expectedCredit, Is.EqualTo(bank.users[id].credit));
                Assert.That(expectedCash, Is.EqualTo(bank.users[id].Cash));
            });
        }

        [Test]
        public void ShowUserOperationShouldReturnProperPositionFromOperationsList()
        {
            string expectedOperation = "Wydatek: 30";

            string actualOperation = bank.ShowUserOperation("1", 1);

            Assert.That(expectedOperation, Is.EqualTo(actualOperation));
        }

        [TearDown]
        public void TearDown()
        {
            bank.users.Clear();
        }
    }
}