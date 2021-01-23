package pl.wojtokuba.proj.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class ByteConverter {

    public static String byteArrayToString(byte[] bytes){
        return new String(bytes);
    }

    public static int readIntFromBuffer(InputStream input) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put((byte)input.read());
        bb.put((byte)input.read());
        bb.put((byte)input.read());
        bb.put((byte)input.read());
        return bb.getInt(0);
    }

    public static byte[] createByteArrayFromInt(int input) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(input);
        return bb.array();
    }
}
