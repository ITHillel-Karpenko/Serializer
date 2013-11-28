import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 26.11.13
 * Time: 12:15
 */
public class User implements Persistable {
    private int id;
    private String name;


    private User friend;

    public User(int id, String name, User friend) {
        this.id = id;
        this.name = name;
        this.friend = friend;
    }

    public User() {
    }

    @Override
    public void read(Input input) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        id = input.readInt();
        name = input.readString();
        friend = (User) input.readPersistable();
    }

    @Override
    public void write(Output output) throws IOException {
        output.write(id);
        output.write(name);
        output.write(friend);
    }

    @Override
    public String toString() {
        return name +
                ", friend=" + friend.getName();
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getFriend() {
        return friend;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
