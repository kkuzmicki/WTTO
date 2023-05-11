

namespace TDD.Tests
{
    public class ValidatorTests
    {
        [SetUp]
        public void Setup()
        {
        }

        [TestCase("kamikuz081@student.polsl.pl", true)]
        [TestCase("fjkdsl", false)]
        [TestCase("adam@wp.pl", true)]
        public void CheckEmailShouldReturnValidValue(string email, bool expected)
        {
            bool actual = Validator.CheckEmailAddress(email);

            Assert.That(actual, Is.EqualTo(expected));
        }
    }
}