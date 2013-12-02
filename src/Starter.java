import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 26.11.13
 * Time: 20:50
 */
public class Starter {
    public static void main(String[] args) throws Exception {
        User user1 = new User(11, "user1", null);
        User user2 = new User(22, "user2", user1);
        User user3 = new User(33, "user3", user2);
        User user4 = new User(44, "user4", user3);
        user1.setFriend(user4);

        System.out.println("Writing");
        File file = new File("data.txt");
        FileOutputStream fos = new FileOutputStream(file);
        Persister persister = new Persister();
        persister.serialize(user4, fos);
        fos.close();

        user4 = null;
        user3 = null;
        user2 = null;



        System.out.println("Reading");
        FileInputStream fis = new FileInputStream(file);
        user4 = (User) persister.deSerialize(fis);
        user3 = user4.getFriend();
        user2 = user3.getFriend();
        user1 = user2.getFriend();
//       User user = user1.getFriend();
        user1.getFriend().setName("asdaf");

        System.out.println(user4);
        System.out.println(user3);
        System.out.println(user2);
        System.out.println(user1);
//        System.out.println(user);

        fis.close();

    }
}
