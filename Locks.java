package SafeCrackers;

import static SafeCrackers.Console.btn;

public class Locks extends Lock {
    @Override
    public void LockStart() {
        for (int i = 0; i < 12; i++) {
            btn[i].setEnabled(false);
        }
    }
}
