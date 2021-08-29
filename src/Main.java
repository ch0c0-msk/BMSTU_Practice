import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            BankAccount bankAccount = new BankAccount("Maksim Ershov", "01.01.2001", 123435, -22000, "RUB");
            System.out.println("Остаток на счету " + bankAccount.getBalance());
            System.out.println("Введите сумму снятия или 0 для завершения работы");
            Scanner in = new Scanner(System.in);
            double sum = in.nextDouble();
            while (sum != 0) {

                try {

                    bankAccount.getMoney(sum);

                } catch (BankAccount.IncorrectDebitAmount ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Введите сумму меньшую или равную остатку на счете");

                }
                System.out.println("Введите сумму снятия или 0 для завершения работы");
                sum = in.nextDouble();
            }

        } catch (BankAccount.ShortFullNameException ex) {

            System.out.println(ex.getMessage());

        } catch (BankAccount.MinusIdException ex) {

            System.out.println(ex.getMessage());

        }  catch (BankAccount.InvalidCurrencyException ex) {

            System.out.println(ex.getMessage());

        }
    }
}
