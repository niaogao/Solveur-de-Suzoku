package coursTBG.src;

public class machineCafe {
    final static int price = 150;
    int state = 0;
    void updateState(int coin) {
        state = state + coin;
        if (state >= price) {
            serve();
            change(state-150);
            state = 0;
        }
     }
     void serve() {
     }
     void change(int amount) {
     }
}
