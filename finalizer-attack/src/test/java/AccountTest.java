import org.junit.Test;

public class AccountTest {


  @Test
  public void 일반_사람() {
    Account account = new Account("anonymous");
    account.transfer(5000000, "ac2dia");
  }

  @Test
  public void 푸틴() {
    Account account = new Account("푸틴");
    account.transfer(5000000, "ac2dia");
  }

  @Test
  public void 푸틴이_통과하네() throws InterruptedException {
    Account account = null;

    try {
      account = new BrokenAccount("푸틴");
    } catch (Exception e) {
      System.out.println("푸틴은 안되는데??");
    }

    System.gc();
    Thread.sleep(3000L);
  }
}
