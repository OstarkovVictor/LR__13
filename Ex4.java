class NewThread implements Runnable {
    String name;
    Thread t;

    NewThread (String threadname) {
        name = threadname;
        t = new Thread(this, name);

        t.start();
    }

    public void run() {
        try {
            System.out.println(name);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(name + " прерван");
        }

    }
}
public class Ex4 {

    public static void main(String[] args) {
        NewThread tr1 = new NewThread("Первый");
        NewThread tr2 = new NewThread("Второй");
        NewThread tr3 = new NewThread("Третий");
        NewThread tr4 = new NewThread("Четвёртый");
        NewThread tr5 = new NewThread("Пятый");
        NewThread tr6 = new NewThread("Шестой");
        NewThread tr7 = new NewThread("Седьмой");
        NewThread tr8 = new NewThread("Восьмой");
        NewThread tr9 = new NewThread("Девятый");
        NewThread tr10 = new NewThread("Десятый");

        try {

            tr1.t.join();
            tr2.t.join();
            tr3.t.join();
            tr4.t.join();
            tr5.t.join();
            tr6.t.join();
            tr7.t.join();
            tr8.t.join();
            tr9.t.join();
            tr10.t.join();
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван");
        }
    }
}