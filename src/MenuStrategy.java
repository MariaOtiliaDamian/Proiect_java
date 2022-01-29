import java.util.HashMap;

public interface MenuStrategy {
    UserAccountType getAccountType();
    HashMap<String, String> getAccountHolderInformation();
    String getAccountMenuOptions(int index, int an);
    void nextMenuOption();
    void previousMenuOption();
}
