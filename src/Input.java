import java.io.IOException;
import java.io.InputStream;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 26.11.13
 * Time: 17:56
 */
public class Input {
    private InputStream inputStream;
    private Map<Persistable,Number> map;

    public Input(InputStream inputStream){
        this.inputStream=inputStream;
        map = new IdentityHashMap<Persistable, Number>();
    }

    public int readInt() throws IOException {
        return inputStream.read();
    }

    public String readString() throws IOException {
        int size;
        size=inputStream.read();
        byte[] name = new byte[size];
        inputStream.read(name);
        return new String(name);
    }

    public Object readPersistable() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        if (inputStream.available()==0) return null;
        int size;
        size=inputStream.read();
        byte[] className = new byte[size];
        inputStream.read(className);
        Object newObj = Class.forName(new String(className)).newInstance();
        ((Persistable) newObj).read(this);
        return newObj;


//        if (!map.containsKey(persistable)) {
//            map.put(persistable,null);
//
    }

}
