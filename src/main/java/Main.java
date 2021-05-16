public class Main {
    public static void main(String[] args) {
        ThreadGroup mainGroup = new ThreadGroup("mainGroup");

        Thread thread1 = new Thread(mainGroup, getThread(2000));
        Thread thread2 = new Thread(mainGroup, getThread(2500));
        Thread thread3 = new Thread(mainGroup, getThread(3000));
        Thread thread4 = new Thread(mainGroup, getThread(3500));

        thread1.setName("поток 1");
        thread2.setName("поток 2");
        thread3.setName("поток 3");
        thread4.setName("поток 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Завершаем работу потоков.");
        mainGroup.interrupt();

    }
    /**
     * Возвращает реализацию run() для работы потока
     * @param sleepTime частота работы потока
     * @return Runnable
     */
    public static Runnable getThread(long sleepTime) {
        return () -> {
            String threadName = Thread.currentThread().getName();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(sleepTime);
                    System.out.println("Я " + threadName + ". Всем привет!");
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(threadName + " завершил свою работу.");
            }
        };
    }
}
