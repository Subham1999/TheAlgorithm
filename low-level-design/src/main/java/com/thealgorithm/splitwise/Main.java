package com.thealgorithm.splitwise;

import java.util.List;

/**
 * @author: Subham Santra
 */
public class Main {
  public static void main(String[] args)
      throws InvalidSplitException, UserIsNotPartOfGroupException {
    User subham = new User("subham");
    User shyam = new User("Shyam");
    User ram = new User("Ram");
    User babubhaiya = new User("Babubhaiya");
    User biltu = new User("Biltu");
    User hari = new User("Hari");
    User mangal = new User("Mangal");
    User charan = new User("Charan");

    Group picnicWala = new Group("PicnicWala");
    Group officeWala = new Group("OfficeWala");
    Group flatWala = new Group("FlatWala");

    picnicWala.add(subham);
    picnicWala.add(shyam);
    picnicWala.add(ram);

    officeWala.add(babubhaiya);
    officeWala.add(biltu);
    officeWala.add(hari);

    flatWala.add(mangal);
    flatWala.add(hari);
    flatWala.add(charan);
    flatWala.add(subham);

    picnicWala.add(
        Expense.builder()
            .paidBy(subham)
            .totalAmount(100D)
            .userSplits(
                List.of(
                    UserSplit.builder().user(subham).shareAmount(33D).build(),
                    UserSplit.builder().user(shyam).shareAmount(33D).build(),
                    UserSplit.builder().user(ram).shareAmount(34D).build()))
            .build());
  }
}
