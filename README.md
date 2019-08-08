# IntiveFDVRentABike
Intive Rent A Bike


# Context
A company rents bikes under following options:
1. Rental by hour, charging $5 per hour
2. Rental by day, charging $20 a day
3. Rental by week, changing $60 a week
4. Family Rental, is a promotion that can include from 3 to 5 Rentals (of any type) with a discount
of 30% of the total price
5. The values could change in the future if the client decides at any time
6: Implement a persistence model for the rents information
7. Implement a kind of message queue model to send and receive the information of the rents that the user creates
8. At the moment of the project, no providers for data persistance nor message exchange have been defined

# Assigment:
1. Implement a set of classes to model this domain and logic
2. Add automated tests to ensure a coverage over 85%
3. Use GitHub to store and version your code
4. Apply all the recommended practices you would use in a real project
5. Add a README.md file to the root of your repository to explain: your design, the development
practices you applied and how run the tests.
Note: we don't expect any kind of application, just a set of classes with its automated tests.






# Solution:

Spring boot:
* Spring Boot follows the deployment-principles of cloud applications and is ready for the cloud out of the box. I figured out that I could use this exersice to made some test by my own with spring cloud.
* You can run it on your own server, VMs, Containers or host on Heroku, AWS or alike.
* It is based on Spring framework
* Don't lose time with configuarition or configuartion problems

Rabbit Mq
*I agree with them --> https://www.rabbitmq.com/features.html

H2
It's a memory database, I usually use it with test or when I want to try some stuff.



the development practices and some design considerations
* I want to use a outbox pattern but I could be to much --> http://www.kamilgrzybek.com/design/the-outbox-pattern/ (overthought)
* I should used incremental commits with a little pieze of code but instedad I only made a couple of them. 
* I only considerer  one promotion per contract, but it could be better if I would use a database design that allowed multiple promotion per contract.(I desinged but I believe that was overthought)
* I use only console log. In a real application It will a good practise use a file log sistem too.
* I try to apply clean code practice --> https://www.amazon.es/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882 (worth its price in gold) 
* I didn't test pojo. In my personal experience I don't like to fake test result coverage with pojo's properties test. it has to be worthwhile.
* I considered create methods to return bike but I don't have enhougt time to create.
* The bike clase could be refactor to extend a vehicle class. So in the future you could have the electric skateboard class that extend vehicle too.
 




