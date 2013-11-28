import java.io.IOException;
import java.io.OutputStream;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 26.11.13
 * Time: 12:18
 */
public class Output {
    private OutputStream outputStream;
    private Map<Persistable,Integer> map;
    private int counter = 0;

    public Output(OutputStream outputStream){
        this.outputStream=outputStream;
        map = new IdentityHashMap<Persistable, Integer>();
    }

    public void write (int i) throws IOException {
        outputStream.write(i);
    }

    public void write (String name) throws IOException {
        outputStream.write(name.length());
        outputStream.write(name.getBytes());
    }

    public void write (Persistable persistable) throws IOException{
        if (persistable==null) return;
        if (!map.containsKey(persistable)) {
            map.put(persistable,counter++);
            outputStream.write(persistable.getClass().getName().getBytes().length);
            outputStream.write(persistable.getClass().getName().getBytes());
            persistable.write(this);
        }  else {
       outputStream.write(0);
       outputStream.write(map.get(persistable));

        }
    }
    public void addPersistable(Persistable persistable){
        map.put(persistable,counter++);
    }
}
