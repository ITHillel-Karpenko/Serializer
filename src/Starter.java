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
    public static void main(String[] args) throws Exception{
        User user1 = new User(11,"11", null);
        User user2 = new User(22,"22",user1);
        User user3 = new User(33,"33",user2);
        User user4 = new User(44,"44",user3);
        user1.setFriend(user4);

        File file = new File("data.txt");
        FileOutputStream fos = new FileOutputStream(file);
        Persister persister = new Persister();
        persister.serialize(user4,fos);
        fos.close();

        user4 = null;
        FileInputStream fis = new FileInputStream(file);
        user4 = (User) persister.deSerialize(fis);
        System.out.println(user4);
        fis.close();

    }
}
