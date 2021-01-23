package pl.wojtokuba.proj.Utils.Net;

public class Communicates {
    public static final byte EHLO = 0x10;
    public static final byte FILE = 0x11;
    public static final byte FIN = 0x12;
    public static final byte PULL = 0x13;
    public static final byte PUSH = 0x14;
}

//client request packets structure:
/*
    1 byte - communicate type
    EHLO:
        2 bytes - (short) clientID
    FILE:
        2 bytes - number of bytes of filename
        xx bytes - byte buffer filename
    PULL:
        4 bytes - (int) - number of chunk
        5 bytes - (char) - 5 chars of file hash uppercase
 */

//server response packets structure:
/*
    1 byte - communicate type
    EHLO:
        2 bytes - (short) clientID
    FILE:
        1 byte - (boolean) 1 - file exists, 0 - file does not exists
        5 bytes - (char) - 5 chars of file hash uppercase
        4 bytes - (int) - amount of chunks to download from servers
    PULL:
        4 bytes - (int) - number of chunk
        5 bytes - (char) - 5 chars of file hash uppercase
        2 bytes - (short) - number of bytes in chunk
        xx bytes - payload of chunk
 */