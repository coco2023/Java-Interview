import java.math.BigDecimal;
import java.util.Date;

public class BankAccount {
          private int id;
          private String accountCode;
          private Date createAt;
          private Date updateAt;
          private String accountHolderName;
          private BigDecimal balance;

          // Constructor
          public BankAccount(String accountCode, String accountHolderName, BigDecimal initialBalance) {
                    this.accountCode = accountCode;
                    this.accountHolderName = accountHolderName;
                    this.createAt = new Date();
                    this.updateAt = new Date();

                    if (initialBalance.compareTo(BigDecimal.ZERO) >= 0) {
                              this.balance = initialBalance;
                    } else {
                              System.out.println("Balance amount is invalid!");
                    }
          }

          // Getters
          public int getId() {
                    return id;
          }

          public String getAccountCode() {
                    return accountCode;
          }

          public String getAccountHolderName() {
                    return accountHolderName;
          }

          public BigDecimal getBalance() {
                    return balance;
          }

          public Date getCreateDate() {
                    return createAt;
          }

          public Date getUpdateDate() {
                    return updateAt;
          }

          // Setters
          public void setAccountHolderName(String accountHolderName) {
                    this.accountHolderName = accountHolderName;
          }

          public void setAccountCode(String accountCode) {
                    this.accountCode = accountCode;
          }

          public void setUpdateAt(Date updateAt) {
                    this.updateAt = updateAt;
          }

          public void setBalance(BigDecimal balance) {
                    if (balance.compareTo(BigDecimal.ZERO) >= 0) {
                              this.balance = balance;
                    } else {
                              System.out.println("Balance amount is invalid!");
                    }
                    System.out.println("Balance update finished!");
          }

          // Main method to test the class
          public static void main(String[] args) {
                    BankAccount marryBankAccount = new BankAccount("Co20250126ChicagoMarry001", "Marry",
                                        BigDecimal.ZERO);

                    System.out.println("Initial Balance: " + marryBankAccount.getBalance());

                    marryBankAccount.setBalance(new BigDecimal("1500.75"));
                    System.out.println("Updated Balance: " + marryBankAccount.getBalance());
          }
}
