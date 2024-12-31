# Результаты тестирования

## Пройденные тесты

# Результаты тестов

## Тесты:

### 1.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatCvcProvided(String) [3] 22`  
**Описание:** Должен выдать ошибку при неправильном формате CVC. Форма: Оплата по карте  
**Status:** passed

### 2.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatCvcProvided(String) [1]`  
**Описание:** Должен выдать ошибку при неправильном формате CVC. Форма: Кредит по данным карты  
**Status:** passed

### 3.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenNonValidMonthProvided`  
**Описание:** Должен выдать ошибку при недопустимом месяце. Форма: Оплата по карте  
**Status:** passed

### 4.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatCvcProvided(String) [2] 1`  
**Описание:** Должен выдать ошибку при неправильном формате CVC. Форма: Кредит по данным карты  
**Status:** passed

### 5.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatCvcProvided(String) [1]`  
**Описание:** Должен выдать ошибку при неправильном формате CVC. Форма: Оплата по карте  
**Status:** passed

### 6.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatMonthProvided(String) [1] 5`  
**Описание:** Должен выдать ошибку при неправильном формате месяца. Форма: Оплата по карте  
**Status:** passed

### 7.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldDeclineCreditWhenInvalidCardProvided`  
**Описание:** Должен отклонить кредит при недействительной карте. Форма: Кредит по данным карты  
**Status:** passed

### 8.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenNonValidMonthProvided`  
**Описание:** Должен выдать ошибку при недопустимом месяце. Форма: Кредит по данным карты  
**Status:** passed

### 9.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldSuccessfulBuyWhenProvideCorrectData`  
**Описание:** Должна пройти успешная покупка при правильных данных. Форма: Оплата по карте  
**Status:** passed

### 10.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenExpiredCardProvided`  
**Описание:** Должен выдать ошибку при истекшей карте. Форма: Оплата по карте  
**Status:** passed

### 11.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatMonthProvided(String) [2]`  
**Описание:** Должен выдать ошибку при неправильном формате месяца. Форма: Оплата по карте  
**Status:** passed

### 12.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenEmptyNameProvided`  
**Описание:** Должен выдать ошибку при пустом имени. Форма: Оплата по карте  
**Status:** passed

### 13.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldSucceedWhenValidCardWithExpireDateThisMonthProvided`  
**Описание:** Должен пройти успешно при карте с датой истечения в этом месяце. Форма: Кредит по данным карты  
**Status:** passed

### 14.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldFailCreditWhenProvideDeclinedCard`  
**Описание:** Должен провалить кредит при отклонённой карте. Форма: Кредит по данным карты  
**Status:** failed

### 15.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatCvcProvided(String) [3] 22`  
**Описание:** Должен выдать ошибку при неправильном формате CVC. Форма: Кредит по данным карты  
**Status:** passed

### 16.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenExpiredCardProvided`  
**Описание:** Должен выдать ошибку при истекшей карте. Форма: Кредит по данным карты  
**Status:** passed

### 17.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldDeclineBuyWhenInvalidCardProvided`  
**Описание:** Должен отклонить покупку при недействительной карте. Форма: Оплата по карте  
**Status:** failed

### 18.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenEmptyNameProvided`  
**Описание:** Должен выдать ошибку при пустом имени. Форма: Кредит по данным карты  
**Status:** passed

### 19.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatMonthProvided(String) [2]`  
**Описание:** Должен выдать ошибку при неправильном формате месяца. Форма: Кредит по данным карты  
**Status:** passed

### 20.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatCvcProvided(String) [2] 1`  
**Описание:** Должен выдать ошибку при неправильном формате CVC. Форма: Оплата по карте  
**Status:** passed

### 21.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenEmptyOwnerNameProvided`  
**Описание:** Должен выдать ошибку при пустом имени владельца. Форма: Кредит по данным карты  
**Status:** passed

### 22.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldRaiseErrorWhenWrongFormatMonthProvided(String) [1] 5`  
**Описание:** Должен выдать ошибку при неправильном формате месяца. Форма: Кредит по данным карты  
**Status:** passed

### 23.
**Test Class:** `test.TestCreditTrip`  
**Test Method:** `shouldSuccessfulCreditWhenProvideCorrectData`  
**Описание:** Должен пройти успешный кредит при правильных данных. Форма: Кредит по данным карты  
**Status:** failed

### 24.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldSucceedWhenValidCardWithExpireDateThisMonthProvided`  
**Описание:** Должен пройти успешно при карте с датой истечения в этом месяце. Форма: Оплата по карте  
**Status:** passed

### 25.
**Test Class:** `test.TestBuyTrip`  
**Test Method:** `shouldFailBuyWhenProvideDeclinedCard`  
**Описание:** Должен провалить покупку при отклонённой карте. Форма: Оплата по карте  
**Status:** failed

---

## Итоги:
- **Passed:** 21
- **Failed:** 4  
![Allure-Report-final-project](https://github.com/user-attachments/assets/57953cfc-2039-44d9-8f0e-b7b2901d8e8f)
