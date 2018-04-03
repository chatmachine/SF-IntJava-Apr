package studentscleaned.students;

interface Criterion<E> {
  boolean test(E s);
  default Criterion<E> negate() {
    return s -> !this.test(s);
  }

  default Criterion<E> and(Criterion<E> c2) {
    return s -> this.test(s) && c2.test(s);
  }

  default Criterion<E> or(Criterion<E> c2) {
    return s -> this.test(s) || c2.test(s);
  }
//  static <E> Criterion<E> negate(Criterion<E> crit) {
//    return s -> !crit.test(s);
//  }
//
//  static <E> Criterion<E> and(Criterion<E> c1, Criterion<E> c2) {
//    return s -> c1.test(s) && c2.test(s);
//  }
//
//  static <E> Criterion<E> or(Criterion<E> c1, Criterion<E> c2) {
//    return s -> c1.test(s) || c2.test(s);
//  }
}
