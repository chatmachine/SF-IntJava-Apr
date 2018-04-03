package taxables;

import java.util.ArrayList;
import java.util.List;

public final class TaxOffice {
  public static void doTaxes(Taxable t) {}

  public static void doTaxes(List<? extends Taxable> lt) {
//    Corporation c = null;
//    lt.add(c);
    for (Taxable t : lt) {
      doTaxes(t);
    }
  }

  public static void addClient(List<? super Taxable> lc) {
//    Taxable t = lc.get(0);
    Corporation c = null;
    Individual i = null;
    lc.add(c);
    lc.add(i);
  }
  public static void main(String[] args) {
    List<Taxable> clients = new ArrayList<>();

    doTaxes(clients);

    List<Individual> li = new ArrayList<>();

    doTaxes(li);
  }
}
