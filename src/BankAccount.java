public class BankAccount {

    private String fullName;
    private String date;
    private long id;
    private double balance;
    private CurrencyType currency;

    public enum CurrencyType {
        EURO, DOLLAR, RUB
    }

    public BankAccount() {
    }

    public BankAccount(String fullName, String date, long id, double balance, String currency)
            throws MinusIdException, ShortFullNameException, InvalidCurrencyException  {

        if (fullName.length() < 5) throw new ShortFullNameException(fullName);
        this.fullName = fullName;
        this.date = date;
        if (id < 0) throw new MinusIdException(id);
        this.id = id;
        this.balance = balance;
        try {
            this.currency = CurrencyType.valueOf(currency);
        } catch (Exception ex) {
            throw new InvalidCurrencyException(currency);
        }
    }

    public void setFullName(String fullName) throws ShortFullNameException {

        if (fullName.length() < 5) throw new ShortFullNameException(fullName);
        this.fullName = fullName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(long id) throws MinusIdException {

        if (id < 0) throw new MinusIdException(id);
        this.id = id;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }

    public void setCurrency(String currency) throws InvalidCurrencyException {

       try {
           this.currency = CurrencyType.valueOf(currency);
       } catch (Exception ex) {
           throw new InvalidCurrencyException(currency);
       }
    }

    public String getFullName() {
        return fullName;
    }

    public String getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public class MinusIdException extends Exception {

        MinusIdException(long id) {

            super("ID счета не может быть отрицательным: " + id);
        }
    }

    public class ShortFullNameException extends Exception {

        ShortFullNameException(String fullName) {

            super("Имя слишком короткое. Минимальное количество символов 5. " + fullName);
        }
    }

    public class InvalidCurrencyException extends Exception {

        InvalidCurrencyException(String currency) {

            super("Неверная валюта счета " + currency);
        }
    }

    public class IncorrectDebitAmount extends Exception {

        IncorrectDebitAmount(double sum) {

            super("Неверная сумма списания, на счете недостаточно средств. Остаток на счету " +
                    BankAccount.this.balance + " Сумма списания - " + sum);
        }
    }

    public double getMoney(double sum) throws IncorrectDebitAmount {

        if ((sum > balance) || (sum < 0)) throw new IncorrectDebitAmount(sum);
        balance -= sum;
        System.out.println("Остаток на счету " + balance);
        return sum;
    }


}
