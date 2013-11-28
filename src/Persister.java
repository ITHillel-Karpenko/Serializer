import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Vladislav Karpenko
 * Date: 26.11.13
 * Time: 12:26
 */
public class Persister {
    private static final int marker = 123;

    public void serialize(Persistable persistable, OutputStream outputStream) throws IOException {
        if( persistable==null) throw new NullPointerException("Object should not be null");

        Output output = new Output(outputStream);
        outputStream.write(marker);
        outputStream.write(persistable.getClass().getName().getBytes().length);
        outputStream.write(persistable.getClass().getName().getBytes());
        output.addPersistable(persistable);
        persistable.write(output);
    }

    public Object deSerialize(InputStream inputStream) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        Input input = new Input(inputStream);
        //read marker
        if (inputStream.read()!=marker) throw new RuntimeException("Wrong stream");


        //read class
        if (inputStream.available()==0) return null;
        int size;
        size=inputStream.read();
        byte[] className = new byte[size];
        inputStream.read(className);
        Object newObj = Class.forName(new String(className)).newInstance();
        input.addObject(newObj);
        ((Persistable) newObj).read(input);
        return newObj;
    }
}
