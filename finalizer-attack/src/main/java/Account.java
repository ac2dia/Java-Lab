public class Account {

  private String name;

  public Account(String name) {
    this.name = name;
    if (this.name.equals("푸틴")) {
      throw new IllegalArgumentException("푸틴은 안 돼~");
    }
  }

  public void transfer(int amount, String to) {
    System.out.printf("transfer %d from %s to %s", amount, this.name, to);
  }

  @Override
  protected final void finalize() throws Throwable {

  }
}
