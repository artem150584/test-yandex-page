Feature: Successful login

  Scenario Outline: Succesfull log in
    Given open authorization page
    When input login: <login>
    And press 'Войти' button
    And input passwd: <password>
    And press 'Войти' button
    Then should be opened https://passport.yandex.ru/profile page
    Then should be shown the following user's data::
      | first name | Userqa    |
      | last name  | Surnameqa |
      | login      | surnameqa |
    Examples:
      | login                | password  |
      | surnameqa            | Surn@meq@ |
      | surnameqa@yandex.ru  | Surn@meq@ |
      | surnameqa@yandex.com | Surn@meq@ |
      | surnameqa@ya.ru      | Surn@meq@ |
      | surnameqa@yandex.by  | Surn@meq@ |
      | surnameqa@yandex.ua  | Surn@meq@ |
      | surnameqa@yandex.kz  | Surn@meq@ |
#      | <mobile phone>       | <password> |

  Scenario: Log in with wrong password
    Given open authorization page
    When input login: surnameqa
    And press 'Войти' button
    And input passwd: wrong_password
    And press 'Войти' button
    Then should be shown any warning message:
      | Неверный пароль            |
      | Введите символы с картинки |
    Then should be opened https://passport.yandex.ru/auth/welcome page

  Scenario Outline: Log in with wrong login, email or phone
    Given open authorization page
    When input login: <login>
    And press 'Войти' button
    Then should be shown login warning message: <warning>
    Examples:
      | login                        | warning                 |
      | rzandomizedlettersertgertret | Такого аккаунта нет     |
      | forbiddenSign<               | Такой логин не подойдет |
      | 80999999999                  | Такой логин не подойдет |
      | 8911111111                   | Такой логин не подойдет |

  Scenario: Register a new user via the button
    Given open authorization page
    When press 'Зарегистрироваться' button
    Then should be opened https://passport.yandex.ru/registration page

  Scenario: Register a new user via the link
    Given open authorization page
    When click 'Зарегистрироваться' link
    Then should be opened https://passport.yandex.ru/registration page

  Scenario: Forget login and restore
    Given open authorization page
    When click 'Не помню логин' link
    Then should be shown message: 'Для восстановления логина введите номер телефона, который был привязан к аккаунту'
    And input phone: 89111111111
    And press 'Далее' button
    Then should be shown captcha form

  @test
  Scenario: Forget login and recall
    Given open authorization page
    When click 'Не помню логин' link
    Then should be shown message: 'Для восстановления логина введите номер телефона, который был привязан к аккаунту'
    And click 'Я вспомнил логин' link
    And input login: surnameqa
    And press 'Войти' button
    And input passwd: Surn@meq@
    And press 'Войти' button
    Then should be opened https://passport.yandex.ru/profile page

  Scenario: Forget password
    Given open authorization page
    When input login: surnameqa
    And press 'Войти' button
    When click 'Не помню пароль' link
    Then should be opened https://passport.yandex.ru/restoration page

  Scenario: Verify QR for app
    Given open authorization page
    When click QR button
    Then should be opened https://passport.yandex.ru/auth/magic page

  @robot
  Scenario Outline: Verify social networks
    Given open authorization page
    When extend social network icons
    And click <network> network
    Then should be open <url> auth page
    Examples:
      | network | url                                                 |
      | vk      | https://oauth.vk.com/authorize                      |
      | fb      | https://www.facebook.com/login.php                  |
      | gg      | https://accounts.google.com/signin/oauth/identifier |
      | mr      | https://connect.mail.ru/oauth/authorize             |
      | ok      | https://connect.ok.ru/dk                            |
      | tw      | https://api.twitter.com/oauth/authenticate          |