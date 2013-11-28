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
    private Map<Object,Integer> map;
    private int counter = 0;

    public Input(InputStream inputStream){
        this.inputStream=inputStream;
        map = new IdentityHashMap<Object, Integer>();

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

        if (size==0) {
            int linker = inputStream.read();
            for (Map.Entry<Object,Integer> entry : map.entrySet()) {
                if (entry.getValue()==linker) return entry.getKey();
            }
        }

        byte[] className = new byte[size];
        inputStream.read(className);
        Object newObj = Class.forName(new String(className)).newInstance();
        map.put(newObj, counter++);
        ((Persistable) newObj).read(this);

        return newObj;
    }

    public void addObject(Object object) {
        map.put(object, counter++);
    }

}
