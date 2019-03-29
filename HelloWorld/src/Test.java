public class Test {
    public static void main(String args[]){
        T t = new T();
        t.start();
        try{
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        new Thread(t).start();
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
