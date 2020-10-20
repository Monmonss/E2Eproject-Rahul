Feature: Login application

  Scenario Outline: Positive login
    Given Initialize the browser
    And click on site Login page
    When User login to using login "<login>" and password "<password>"
#    przejdzie wszystkie kroki i odpali je jeszcz raz /\
#    Then Verify if user is successfully logged in
    Then close the browser
    Examples:
      | login           | password       |
      | monia@gmail.com | password       |
      | second@onrt.pl  | passwordSecond |


