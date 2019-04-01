public class Test {

    static Test t=new Test();

    class T1 extends Thread{
        public T1(String name){
            super(name);
        }
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //T3线程中要处理的东西
            System.out.println("T1线程执行");
            for(int i=0;i<10;i++){
                System.out.println(this.getName() + ":" + i);
            }
        }
    }

    class T2 extends Thread{
        public T2(String name){
            super(name);
        }
        @Override
        public void run() {
            //T3线程中要处理的东西
            System.out.println("T2线程执行");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<10;i++){
                System.out.println(this.getName() + ":" + i);
            }
        }
    }

    class T3 extends Thread{
        public T3(String name){
            super(name);
        }
        @Override
        public void run() {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //T3线程中要处理的东西
            System.out.println("T3线程执行");
            for(int i=0;i<10;i++){
                System.out.println(this.getName() + ":" + i);
            }
        }
    }


    public static void main(String args[]){
        /*T t = new T();
        t.start();
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        new Thread(t).start();*/

        try {
            T3 t3= t.new T3("T3");
            t3.start();//启动t3线程
            t3.join();//阻塞主线程，执行完t3再返回

            T2 t2= t.new T2("T2");
            t2.start();//启动t3线程
            t2.join();//阻塞主线程，执行完t3再返回

            T1 t1= t.new T1("T1");
            t1.start();//启动t3线程
            t1.join();//阻塞主线程，执行完t3再返回

//            T3线程执行
//            T3:0
//            T3:1
//            T3:2
//            T3:3
//            T3:4
//            T3:5
//            T3:6
//            T3:7
//            T3:8
//            T3:9
//            T2线程执行
//            T2:0
//            T2:1
//            T2:2
//            T2:3
//            T2:4
//            T2:5
//            T2:6
//            T2:7
//            T2:8
//            T2:9
//            T1线程执行
//            T1:0
//            T1:1
//            T1:2
//            T1:3
//            T1:4
//            T1:5
//            T1:6
//            T1:7
//            T1:8
//            T1:9
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class T extends Thread{

    public void run(){
        System.out.println("here");
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
