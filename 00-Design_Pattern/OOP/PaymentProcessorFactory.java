import java.math.BigDecimal;

interface Payment {
          void initPayment(PaymentRequest paymentRequest);

          void completePayment(PaymentRequest paymentRequest);
}

enum PayType {
          PAYPAL,
          CREDIT_CARD,
          UPI;
}

class PaymentRequest {
          private int paymentId;
          private BigDecimal amount;
          private boolean status;
          private String paymentMethod;

          PaymentRequest(int paymentId, BigDecimal amount) {
                    this.paymentId = paymentId;
                    this.amount = amount;
                    this.status = false;
          }

          public void setStatus(boolean status) {
                    this.status = status;
          }

          public void setPaymentMethod(PayType payType) {
                    this.paymentMethod = payType.name();
          }

          @Override
          public String toString() {
                    return "paymentId :" + paymentId + "; amount: " + amount + "; payment method: " + paymentMethod
                                        + "; payment status: " + status;
          }
}

class CreditCardPaymentProcessor implements Payment {
          @Override
          public void initPayment(PaymentRequest paymentRequest) {
                    System.out.println("CREDIT_CARD");
                    paymentRequest.setPaymentMethod(PayType.CREDIT_CARD);
                    System.out.println(paymentRequest);
          }

          @Override
          public void completePayment(PaymentRequest paymentRequest) {
                    System.out.println("CREDIT_CARD Payment Complete!");
                    paymentRequest.setStatus(true);
                    System.out.println(paymentRequest);
          }
}

class PayPalPaymentProcessor implements Payment {
          @Override
          public void initPayment(PaymentRequest paymentRequest) {
                    System.out.println("PAYPAL");
                    paymentRequest.setPaymentMethod(PayType.PAYPAL);
                    System.out.println(paymentRequest);
          }

          @Override
          public void completePayment(PaymentRequest paymentRequest) {
                    System.out.println("PAYPAL Payment Complete!");
                    paymentRequest.setStatus(true);
                    System.out.println(paymentRequest);
          }
}

class UPIPaymentProcessor implements Payment {
          @Override
          public void initPayment(PaymentRequest paymentRequest) {
                    System.out.println("UPI");
                    paymentRequest.setPaymentMethod(PayType.UPI);
                    System.out.println(paymentRequest);
          }

          @Override
          public void completePayment(PaymentRequest paymentRequest) {
                    System.out.println("UPI Payment Complete!");
                    paymentRequest.setStatus(true);
                    System.out.println(paymentRequest);
          }
}

class PaymentProcessor {
          public static Payment choosePaymentMethod(PayType payType) {
                    switch (payType) {
                              case PAYPAL:
                                        return new PayPalPaymentProcessor();
                              case CREDIT_CARD:
                                        return new CreditCardPaymentProcessor();
                              case UPI:
                                        return new UPIPaymentProcessor();
                              default:
                                        throw new IllegalArgumentException("Invalid Payment type: " + payType);
                    }
          }
}

public class PaymentProcessorFactory {
          public static void main(String[] args) {

                    Payment payment2 = PaymentProcessor.choosePaymentMethod(PayType.CREDIT_CARD);
                    PaymentRequest paymentRequest1 = new PaymentRequest(1, new BigDecimal(100));
                    payment2.initPayment(paymentRequest1);
                    System.out.println("======WAITING PAYMENT PROCESSINNG======");
                    payment2.completePayment(paymentRequest1);

                    Payment payment3 = PaymentProcessor.choosePaymentMethod(PayType.PAYPAL);
                    PaymentRequest paymentRequest2 = new PaymentRequest(2, new BigDecimal(200));
                    payment3.initPayment(paymentRequest2);
                    System.out.println("======WAITING PAYMENT PROCESSINNG======");
                    payment3.completePayment(paymentRequest2);

                    Payment payment4 = PaymentProcessor.choosePaymentMethod(PayType.UPI);
                    PaymentRequest paymentRequest4 = new PaymentRequest(3, new BigDecimal(300));
                    payment4.initPayment(paymentRequest4);
                    System.out.println("======WAITING PAYMENT PROCESSINNG======");
                    payment4.completePayment(paymentRequest4);

          }
}
