---
name: User Story  with acceptance criteria in format given when then
about: User story, with acceptance criteria in format given when then
title: "[USER STORY]"
labels: enhancement, good first issue, feature development, Refactor, TODO, Epic
assignees: josousa82

---

A user story that describes the webpage search feature:

As a website user
I want to be able to search on the webpage
So that I can find the necessary information

Acceptance criteria for this piece of functionality would be:

Scenario: User searches for an item by its name“Given that I’m in a role of registered or guest user

When I open the “Products” page
Then the system shows me the list of all products
And the system shows the “Search” section in the right top corner of the screen
When I fill in the “Search” field with the name of the existing item in the product list
And I click the “Apply” button OR press the Enter key on the keyboard
Then the system shows products in the Search Results section with product names matching entered product name
And the system shows the number of search results at the top of the Search Results section”
