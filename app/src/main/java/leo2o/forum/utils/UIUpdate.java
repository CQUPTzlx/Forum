package leo2o.forum.utils;

public class UIUpdate {

    public static void run(Runnable runnable) {
        new Thread(runnable).start();
    }
}
