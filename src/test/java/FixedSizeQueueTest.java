import Model.FixedSizeQueue;
import org.junit.Assert;
import org.junit.Test;

public class FixedSizeQueueTest {

    private FixedSizeQueue<Integer> myQueue;

    @Test
    // Enqueue function simple test
    public void test1() {
        System.out.println("Initializing Queue With Size 2");
        myQueue = new FixedSizeQueue<>(2);
        System.out.println("Inserting 1 and 2");
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        Assert.assertEquals(Integer.valueOf(1), myQueue.get(0));
        Assert.assertEquals(Integer.valueOf(2), myQueue.get(1));
        System.out.println(myQueue);
        System.out.println("Inserting 3");
        myQueue.enqueue(3);
        Assert.assertEquals(Integer.valueOf(2), myQueue.get(0));
        Assert.assertEquals(Integer.valueOf(3), myQueue.get(1));
        System.out.println(myQueue);
        System.out.println("Inserting 4");
        myQueue.enqueue(4);
        Assert.assertEquals(Integer.valueOf(3), myQueue.get(0));
        Assert.assertEquals(Integer.valueOf(4), myQueue.get(1));
        System.out.println(myQueue);
    }

    @Test
    // Enqueue function complex test
    public void test2() {
        myQueue = new FixedSizeQueue<>(10);
        for (int i = 0 ; i < 10 ; i++)
            myQueue.enqueue(i);
        for (int i = 10 ; i < 20 ; i++) {
            System.out.println(myQueue);
            myQueue.enqueue(i);
            for (int j = 0 ; j < 10 ; j++) {
                Assert.assertEquals(Integer.valueOf(i + j - 9), myQueue.get(j));
            }
        }
    }
}
