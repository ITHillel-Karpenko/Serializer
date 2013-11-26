import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 26.11.13
 * Time: 12:13
 */
public interface Persistable {
    void write (Output output) throws IOException;
    void read (Input input) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;


}
