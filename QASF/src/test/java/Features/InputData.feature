Feature: Input Data to SF

  Scenario: Input Data to SF
    Given I navigate to SF
    And I login to SF with role is Admin
    And I input Work Order 63023, Shift 6:00PM - 6:00AM, Test Machine SATA - Init 1
    And I select tab SMT
    And I click to button PASSED at SMT
    And I scan Panel ID at SMTPASSED from excel
    And I scroll to tab PDTEST
    And I select tab PDTEST
    And I click to button Labeling at PDTEST
    And I scan Panel ID and SerialNumber at labeling with number of blocks is 10 from excel
    And I scroll to tab PDTEST
    And I select tab PDTEST
    And I click to button Depanel at PDTEST
    And I scan SerialNumber at Depanel from excel





