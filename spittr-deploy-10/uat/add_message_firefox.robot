*** Settings ***
Library           SeleniumLibrary

*** Test Cases ***
Add Message to Spittr
    Open Browser    http://www.spittr.com/spittr/login    firefox
    Set Focus To Element    //input[@name="username"]
    Press Keys    //input[@name="username"]  tzs919
    Set Focus To Element    //input[@name="password"]
    Press Keys    //input[@name="password"]  123456
    click element  //input[@name="submit"]
    element should be visible   link:Logout
    click element    link:Spittles
    Set Focus To Element    //textarea[@name="message"]
    Press Keys    //textarea[@name="message"]  abcdefghijkl
    click element   xpath://input[@type="submit"]
    page should contain element   //div[@class="spittleMessage"]
    element text should be   //div[@class="spittleMessage"][1]  abcdefghijkl

    capture page screenshot
    capture element screenshot   //div[@class="spittleMessage"][1]

#    log title
    ${newtext} =     get text    //div[@class="spittleMessage"][1]
    log  ${newtext}

    [Teardown]    Close Browser
