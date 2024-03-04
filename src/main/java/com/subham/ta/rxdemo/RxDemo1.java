package com.subham.ta.rxdemo;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;
import java.math.BigDecimal;
import javax.swing.*;

class CustomerOrder {}

public class RxDemo1 {
  private static Subject<CustomerOrder> customerOrderSubject;
  private static Observable<CustomerOrder> customerOrderObserver;

  public static void main(String[] args) {
    BigDecimal initial = BigDecimal.valueOf(1);

    for(int i = 0; i < 30; i++) {
      initial = initial.add(initial.multiply(BigDecimal.valueOf(1.01)));
    }
    System.out.println(initial);
  }
}
